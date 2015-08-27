package tr.com.agem.java.adaptor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tr.com.agem.common.AgemCrudService;
import tr.com.agem.common.AgemService;
import tr.com.agem.common.AgemServiceExclude;
import tr.com.agem.common.AgemUtils;
import tr.com.agem.common.exception.AgemServiceException;
import tr.com.agem.common.form.AgemForm;
import tr.com.agem.common.form.PagedList;
import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.db.connection.DBConnectionFactory;
import tr.com.agem.db.connection.DBConnectionInterface;
import tr.com.agem.db.operations.BDBase;
import tr.com.agem.startup.db.PostgreSqlDBMS;

public class AryaJarAdaptor extends AryaApplicationAdaptor {

	private static final Logger logger = Logger.getLogger(AryaJarAdaptor.class.getName());

	@Override
	public IAryaAdaptorResponse processRequest(IAryaRequest request) {

		String actionURI = getMapper().map(request.getAction());
		String serviceMethodName = findServiceMethodName(request.getAction());
		String serviceName = actionURI + "Service";
		boolean isListAction = "list".equalsIgnoreCase(serviceMethodName);
		boolean isSelectAction = "select".equalsIgnoreCase(serviceMethodName);
		String formName = actionURI + (isListAction ? "ParameterForm" : "Form");

		logger.log(Level.INFO, "Calling jar method: {0} of service {1} with parameters: {2}",
				new Object[] { serviceMethodName, serviceName, request.getParams() });

		initDBConnection();

		try {

			// Create form object...
			Class<?> cls = Class.forName(formName);
			AgemForm form = (AgemForm) cls.newInstance();

			// ...and set its fields using request parameters
			if (request.getParams() != null && request.getParams().size() > 0) {

				for (Entry<String, Object> entry : request.getParams().entrySet()) {

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

			// Create service object
			AgemService service = getServiceInstance(serviceName, serviceMethodName);

			// Invoke service method using newly created form object
			Method serviceMethod = AgemService.class.getMethod(serviceMethodName,
					isListAction ? PagedList.class : AgemForm.class);
			Object arg = form;
			if (isListAction) {
				PagedList pl = new PagedList();
				pl.setParameters(form);
				// Set page size
				Object tmpParam = request.getParams().get("pageSize");
				String pageSize = tmpParam != null ? tmpParam.toString()
						: PropertyReader.property("paged.list.page.size");
				pl.setPageSize(pageSize != null ? Integer.parseInt(pageSize) : -1);
				// Set page number
				tmpParam = request.getParams().get("pageNumber");
				String pageNumber = tmpParam != null ? tmpParam.toString() : null;
				pl.setPageNumber(pageNumber != null ? Integer.parseInt(pageNumber) : 1);
				arg = pl;
			}

			Object returnVal = serviceMethod.invoke(service, arg);

			logger.log(Level.INFO, "Response successful: {0}", returnVal != null
					? ((isListAction || isSelectAction) ? AgemUtils.jsObject(returnVal) : returnVal.toString()) : "");

			AryaAdaptorResponse response = new AryaAdaptorResponse();
			response.setData((isListAction || isSelectAction) ? AgemUtils.jsObject(returnVal) : "{ }");
			return response;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
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

	private String findServiceMethodName(String action) {
		String[] split = action.split("\\.");
		return split[split.length - 1];
	}

	private String findSetterName(String name) {
		Matcher matcher = Pattern.compile("_(\\w)").matcher(name);
		while (matcher.find()) {
			name = name.replaceFirst(matcher.group(0), matcher.group(1).toUpperCase());
		}
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	private AgemService getServiceInstance(String serviceName, String serviceMethodName)
			throws InstantiationException, IllegalAccessException {
		try {
			Class<?> clazz = Class.forName(serviceName);

			if (serviceMethodName != null && clazz.isAnnotationPresent(AgemServiceExclude.class)) {
				AgemServiceExclude s = clazz.getAnnotation(AgemServiceExclude.class);
				if (Arrays.asList(s.excludeServices()).contains(serviceMethodName)) {
					throw new AgemServiceException("No service is defined for this request...");
				}
			}
			return (AgemService) clazz.newInstance();

		} catch (ClassNotFoundException e) {
			return new AgemCrudService();
		}
	}

}
