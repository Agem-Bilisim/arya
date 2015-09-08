package tr.com.agem.java.adaptor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import tr.com.agem.common.AgemActionMapping;
import tr.com.agem.common.AgemUtils;
import tr.com.agem.common.action.AgemCrudAction;
import tr.com.agem.common.form.AgemForm;
import tr.com.agem.common.form.JsonMessageFormInterface;
import tr.com.agem.common.form.PagedList;
import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.db.connection.DBConnectionFactory;
import tr.com.agem.db.connection.DBConnectionInterface;
import tr.com.agem.db.operations.BDBase;
import tr.com.agem.java.mapper.AryaJarMappedRequest;
import tr.com.agem.startup.db.PostgreSqlDBMS;
import tr.com.agem.struts.AgemModuleConfigImp;

public class AryaJarAdaptor extends AryaApplicationAdaptor {

	private static final Logger logger = Logger.getLogger(AryaJarAdaptor.class.getName());

	@Override
	public IAryaAdaptorResponse processRequest(IAryaRequest aryaRequest) {

		AryaJarMappedRequest mappedRequest = (AryaJarMappedRequest) getMapper().map(aryaRequest.getAction());
		
		logger.log(Level.INFO, "Calling jar method: {0} of service {1} with parameters: {2}",
				new Object[] { mappedRequest.getActionMethodName(), mappedRequest.getServiceName(), aryaRequest.getParams() });

		initDBConnection();

		try {

			// Create form object...
			Class<?> cls = Class.forName(mappedRequest.getFormName());
			AgemForm form = (AgemForm) cls.newInstance();

			// ...and set its fields using request parameters
			if (aryaRequest.getParams() != null && aryaRequest.getParams().size() > 0) {

				for (Entry<String, Object> entry : aryaRequest.getParams().entrySet()) {

					Field field = null;
					try {
						field = cls.getField(entry.getKey());
					} catch (NoSuchFieldException e) {
					}

					if (field != null) {

						Method setter = null;
						try {
							setter = cls.getMethod(findSetterName(field.getName()), entry.getValue().getClass());
						} catch (NoSuchMethodException e) {
						}

						if (setter != null) {

							setter.invoke(form, entry.getValue());

							logger.log(Level.FINE, "Setting value of {0} to: {1}",
									new Object[] { field.getName(), entry.getValue() });
						}

					}

				}

			}

			// Create action mapping
			AgemActionMapping mapping = new AgemActionMapping();
			mapping.setType(PropertyReader.property("agem.crud.action"));
			mapping.setScope("request"); // TODO ???
			mapping.setParameter(mappedRequest.getServiceName());
			mapping.setName("genelKisiParameterForm");
			mapping.setAttribute("genelKisiParameterForm");
			mapping.setPath(mappedRequest.getPath());
			mapping.setMethod(mappedRequest.getActionMethodName());
			mapping.setModuleConfig(new AgemModuleConfigImp(""));

			// Create action dispatcher
			cls = Class.forName(PropertyReader.property("agem.crud.action"));
			AgemCrudAction da = (AgemCrudAction) cls.newInstance();

			// Create request and response mock objects
			MockHttpServletRequest httpRequest = new MockHttpServletRequest();
			addRequestParameters(aryaRequest, httpRequest);
			MockHttpServletResponse httpResponse = new MockHttpServletResponse();

			// Execute action!
			da.execute(mapping, form, httpRequest, httpResponse);

			String dataStr = convertToJson(httpRequest);

			logger.log(Level.INFO, "Action executed successfully: {0} ", dataStr);

			AryaAdaptorResponse response = new AryaAdaptorResponse();
			response.setData(dataStr);

			return response;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void initDBConnection() {
		try {
			final Connection conn = getDataSource().getConnection();
			DBConnectionFactory.getInstance().setConnectionInterface(new DBConnectionInterface() {
				@Override
				public Connection getConnection() {
					return conn;
				}
			});

			BDBase.DBMS_INTERFACE = new PostgreSqlDBMS();
			BDBase.DBMS_INTERFACE.init();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addRequestParameters(IAryaRequest aryaRequest, MockHttpServletRequest httpRequest) {
		httpRequest.setParameter("json", "1");
		// TODO add other arya request parameters and attributes to HTTP request here!
	}

	private String convertToJson(MockHttpServletRequest httpRequest) {

		Object obj = httpRequest.getAttribute("collection");
		Object json = httpRequest.getAttribute("json");
		StringBuilder result = new StringBuilder("");

		if (obj != null) {
			result.append("{ \"results\":[");
			Collection<?> col = null;
			if (obj instanceof Collection) {
				col = (Collection<?>) obj;
			} else if (obj instanceof PagedList) {
				col = ((PagedList) obj).getList();
			}
			if (col != null) {
				Iterator<?> it = col.iterator();
				while (it.hasNext()) {
					result.append(AgemUtils.jsObject(it.next()));
					if (it.hasNext()) {
						result.append(",");
					}
				}
			}
			result.append("]");
			if (obj instanceof PagedList) {
				result.append(", \"fullListSize\": ");
				result.append(((PagedList) obj).getFullListSize());
				result.append(", \"pageNumber\": ");
				result.append(((PagedList) obj).getPageNumber());
			}
			if (json != null && json instanceof JsonMessageFormInterface) {
				Object v = ((JsonMessageFormInterface) json).getMessage();
				if (v != null) {
					v = StringEscapeUtils.escapeJavaScript(v.toString());
					result.append(",\"@message\":\"");
					result.append(v);
					result.append("\"");
				}
				v = ((JsonMessageFormInterface) json).getError();
				if (v != null) {
					v = StringEscapeUtils.escapeJavaScript(v.toString());
					result.append(",\"@hata\": true");
				}
			}
			result.append(" }");
		} else if (json instanceof AgemForm) {
			result.append(AgemUtils.jsObject(json));
		} else {
			result.append("{ \"results\":[] }");
		}

		return result.toString();
	}

	private String findSetterName(String name) {
		Matcher matcher = Pattern.compile("_(\\w)").matcher(name);
		while (matcher.find()) {
			name = name.replaceFirst(matcher.group(0), matcher.group(1).toUpperCase());
		}
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}
