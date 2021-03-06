package tr.com.agem.arya.adaptor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import tr.com.agem.arya.mapper.AryaAgemUtilsJARMappedRequest;
import tr.com.agem.common.AgemActionMapping;
import tr.com.agem.common.AgemConstant;
import tr.com.agem.common.AgemUtils;
import tr.com.agem.common.UserContext;
import tr.com.agem.common.action.AgemCrudAction;
import tr.com.agem.common.form.AgemForm;
import tr.com.agem.common.form.JsonMessageFormInterface;
import tr.com.agem.common.form.PagedList;
import tr.com.agem.core.adaptor.AryaAdaptorDefaultResponse;
import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.context.AryaThreadLocal;
import tr.com.agem.core.context.AttributeScope;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.core.utils.AryaUtils;
import tr.com.agem.db.connection.DBConnectionFactory;
import tr.com.agem.db.connection.DBConnectionInterface;
import tr.com.agem.db.dbms.PostgreSqlDBMS;
import tr.com.agem.db.operations.BDBase;
import tr.com.agem.struts.AgemModuleConfigImp;
import tr.com.agem.user.User;

public abstract class AryaAgemUtilsJARAdaptor extends AryaApplicationAdaptor {

	private static final Logger logger = Logger.getLogger(AryaAgemUtilsJARAdaptor.class.getName());

	
	@Override
	public IAryaAdaptorResponse processRequest(IAryaRequest aryaRequest) {

		AryaAgemUtilsJARMappedRequest mappedRequest = (AryaAgemUtilsJARMappedRequest) getMapper().map(aryaRequest.getAction());

		logger.log(Level.INFO, "Calling jar method: {0} of service {1} with parameters: {2}", new Object[] {
				mappedRequest.getActionMethodName(), mappedRequest.getServiceName(), aryaRequest.getParams() });

		initDBConnection();

		try {

			// Create form object...
			Class<?> cls = Class.forName(mappedRequest.getFormName());
			AgemForm form = (AgemForm) cls.newInstance();

			// ...and set its fields using request parameters
			if (aryaRequest.getParams() != null && aryaRequest.getParams().size() > 0) {
				for (Entry<String, Object> entry : aryaRequest.getParams().entrySet()) {
					try {
						setFormValue(form, entry.getKey(), entry.getValue());
						logger.log(Level.FINE, "Setting value of {0} to: {1}",
								new Object[] { entry.getKey(), entry.getValue().toString() });
					} catch (Throwable e) {
					}
				}
			}
			
			// Mock UserContext
			UserContext.setContext(AryaThreadLocal.getRequest(), AryaThreadLocal.getResponse(),
					AryaThreadLocal.getServletContext());

			// Create action mapping
			AgemActionMapping mapping = new AgemActionMapping();
			mapping.setType(PropertyReader.property("agem.crud.action"));
			mapping.setScope("request"); // TODO ???
			mapping.setParameter(mappedRequest.getServiceName());
			mapping.setName(mappedRequest.getFormName());
			mapping.setAttribute(mappedRequest.getFormName());
			mapping.setPath(mappedRequest.getPath());
			
			// edit if it is select
			mapping.setMethod(mappedRequest.getActionMethodName());
			mapping.setModuleConfig(new AgemModuleConfigImp(""));

			// Create action dispatcher
			cls = Class.forName(PropertyReader.property("agem.crud.action"));
			AgemCrudAction da = (AgemCrudAction) cls.newInstance();
			((HttpServletRequest)AryaThreadLocal.getRequest()).getSession().setAttribute(AgemConstant.C_PAGE_SIZE, 50);
			// Create request and response mock objects
			addRequestParameters(aryaRequest, (HttpServletRequest) AryaThreadLocal.getRequest());
			
			// Execute action!
			da.execute(mapping, form, (HttpServletRequest) AryaThreadLocal.getRequest(),		//TODO in string parameters looing for 'like' not equality?
					(HttpServletResponse) AryaThreadLocal.getResponse());
			
			
			if (mapping.getMethod().equals("insert") || mapping.getMethod().equals("update")) {
				mapping.setMethod("select");
				da.execute(mapping, form, (HttpServletRequest) AryaThreadLocal.getRequest(), (HttpServletResponse) AryaThreadLocal.getResponse());
			} else 	if (mapping.getMethod().equals("delete")) {
				mapping.setMethod("empty");
				da.execute(mapping, form, (HttpServletRequest) AryaThreadLocal.getRequest(), (HttpServletResponse) AryaThreadLocal.getResponse());
				AryaThreadLocal.getRequest().setAttribute(mappedRequest.getFormName(), form.renew());
			}


			String dataStr = convertToJson(AryaThreadLocal.getRequest(), mappedRequest.getFormName());

			logger.log(Level.INFO, "Action executed successfully: {0} ", dataStr);

			AryaAdaptorDefaultResponse response = new AryaAdaptorDefaultResponse();
			response.setData(dataStr);
			response.setViewName(mappedRequest.getViewName());

			return response;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			AryaUtils.logException(null,e);
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	private void setFormValue(Object form, String key, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		if (key.indexOf(".") > -1) {
			String prop = StringUtils.substringBefore(key, ".");
			Object v = PropertyUtils.getProperty(form, prop);
			if (v == null) {
				Class cls = PropertyUtils.getPropertyType(form, prop);
				v = cls.newInstance();
				PropertyUtils.setProperty(form, prop, v);
			}
			setFormValue(v, StringUtils.substringAfter(key, "."), value);
		}
		else {
			if (AryaUtils.isEmpty(value)) {
				PropertyUtils.setProperty(form, key, null);
			}
			else {
				BeanUtilsBean.getInstance().setProperty(form, key, value);
			}
		}
		
	}

	@Override
	public boolean checkLogin(IAryaRequest aryaRequest) {
		Object obj = AryaThreadLocal.getSession().getAttribute(AgemConstant.C_USER);
		return obj != null && obj instanceof User;
	}

	protected void initDBConnection() {
		try {
			DBConnectionFactory.getInstance().setConnectionInterface(new DBConnectionInterface() {
				@Override
				public Connection getConnection() {
					try {
						return getDataSource().getConnection();
					} catch (SQLException e) {
						AryaUtils.logException(null,e);
					}
					return null;
				}
			});

			BDBase.DBMS_INTERFACE = new PostgreSqlDBMS();
			BDBase.DBMS_INTERFACE.init();			
		} catch(Exception e) {
		}
	}

	private void addRequestParameters(IAryaRequest aryaRequest, ServletRequest httpRequest) {
		httpRequest.setAttribute("json", "1");
		// TODO add other arya request parameters and attributes to HTTP request
		// here! (if exists)
	}

	private String convertToJson(ServletRequest httpRequest, String attributeName) {

		Object obj = httpRequest.getAttribute("collection");
//		Object json = httpRequest.getAttribute("json");
		Object json = httpRequest.getAttribute(attributeName);
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
			result.append("{ \"results\":["+AgemUtils.jsObject(json)+"] }");
		} else {
			result.append("{ \"results\":[] }");
		}

		return result.toString();
	}

	@Override
	public Object getAttributeValue(String attributeName, AttributeScope scope) {
		return null;
	}

}
