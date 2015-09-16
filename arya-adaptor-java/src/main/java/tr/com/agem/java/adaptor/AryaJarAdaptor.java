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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import tr.com.agem.common.AgemActionMapping;
import tr.com.agem.common.AgemConstant;
import tr.com.agem.common.AgemUtils;
import tr.com.agem.common.UserContext;
import tr.com.agem.common.action.AgemCrudAction;
import tr.com.agem.common.form.AgemForm;
import tr.com.agem.common.form.JsonMessageFormInterface;
import tr.com.agem.common.form.PagedList;
import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.context.AryaThreadLocal;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.metadata.exception.AryaLoginFailedException;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.db.connection.DBConnectionFactory;
import tr.com.agem.db.connection.DBConnectionInterface;
import tr.com.agem.db.operations.BDBase;
import tr.com.agem.java.mapper.AryaJarMappedRequest;
import tr.com.agem.startup.db.PostgreSqlDBMS;
import tr.com.agem.startup.kullanici.KullaniciForm;
import tr.com.agem.startup.login.LoginAction;
import tr.com.agem.startup.login.LoginForm;
import tr.com.agem.startup.logout.LogoutAction;
import tr.com.agem.struts.AgemModuleConfigImp;
import tr.com.agem.user.User;

public class AryaJarAdaptor extends AryaApplicationAdaptor {

	private static final Logger logger = Logger.getLogger(AryaJarAdaptor.class.getName());

	@Override
	public IAryaAdaptorResponse processRequest(IAryaRequest aryaRequest) {

		AryaJarMappedRequest mappedRequest = (AryaJarMappedRequest) getMapper().map(aryaRequest.getAction());

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

					Field field = null;
					try {
						field = cls.getDeclaredField(entry.getKey());	//access private fields
								//getField(entry.getKey());				//finds just public fields
					
					} catch (NoSuchFieldException e) {
						logger.log(Level.WARNING, "No such a field: {0}",
								new Object[] { entry.getKey()});
					}

					if (field != null) {

						Method setter = null;
						try {
							setter = cls.getMethod(findSetterName(field.getName()), field.getType());//we can get parameter type via field.type not entry
						} catch (NoSuchMethodException e) {
						}

						if (setter != null) {

							setter.invoke(form, returnValidType(field.getType(),entry.getValue().toString()));

							logger.log(Level.FINE, "Setting value of {0} to: {1}",
									new Object[] { field.getName(), entry.getValue().toString() });
						}

					}

				}

			}

			// Create action mapping
			AgemActionMapping mapping = new AgemActionMapping();
			mapping.setType(PropertyReader.property("agem.crud.action"));
			mapping.setScope("request"); // TODO ???
			mapping.setParameter(mappedRequest.getServiceName());
			mapping.setName(mappedRequest.getFormName());
			mapping.setAttribute(mappedRequest.getFormName());
			mapping.setPath(mappedRequest.getPath());
			mapping.setMethod(mappedRequest.getActionMethodName());
			mapping.setModuleConfig(new AgemModuleConfigImp(""));

			// Create action dispatcher
			cls = Class.forName(PropertyReader.property("agem.crud.action"));
			AgemCrudAction da = (AgemCrudAction) cls.newInstance();

			// Create request and response mock objects
			addRequestParameters(aryaRequest, (HttpServletRequest) AryaThreadLocal.getRequest());

			// Execute action!
			da.execute(mapping, form, (HttpServletRequest) AryaThreadLocal.getRequest(),		//TODO in string parameters looing for 'like' not equality?
					(HttpServletResponse) AryaThreadLocal.getResponse());

			String dataStr = convertToJson(AryaThreadLocal.getRequest());

			logger.log(Level.INFO, "Action executed successfully: {0} ", dataStr);

			AryaAdaptorResponse response = new AryaAdaptorResponse();
			response.setData(dataStr);
			response.setViewName(mappedRequest.getViewName());

			return response;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Object returnValidType(Class<?> clazz, String value) {//TODO complex types?
																//is returning null valid?
		 if (clazz == null){
			 logger.log(Level.WARNING, "Class is null ");
			 return null;
		 }
		 
		   try {

		        if(Integer.class.equals(clazz)) {
		            return Integer.valueOf(value);
		        }
		        else if(Double.class.equals(clazz)) {
		            return Double.valueOf(value);
		        }
		        else if(Long.class.equals(clazz)) {
		            return Long.valueOf(value);
		        }
		        else if(Float.class.equals(clazz)) {
		            return Float.valueOf(value);
		        }
		        else if(String.class.equals(clazz)) {
		            return String.valueOf(value);
		        }
		        //so on
		   }
		    catch(NumberFormatException|NullPointerException ex) {
		         logger.log(Level.WARNING, "Invalid or Undefined Type  : {0}",new Object[] { clazz.getName()});
		    }
		    
		return null;
	}

	@Override
	public IAryaAdaptorResponse processLogin(IAryaRequest request) {

		initDBConnection();

		String username = (String) request.getParams().get("username");
		String password = (String) request.getParams().get("password");

		logger.log(Level.INFO, "Calling login action with parameters username: {0} and password: {1}",
				new Object[] { username, password });

		// Create login form
		LoginForm loginForm = new LoginForm();
		loginForm.setKullaniciKodu(username);
		loginForm.setParola(password);

		// To overcome wrong captcha value!
		loginForm.setGuvenlikKodu("dummytext");
		AryaThreadLocal.getRequest().setAttribute(AgemConstant.C_CONNECTION_IP, "localhost");

		// Mock UserContext
		UserContext.setContext(AryaThreadLocal.getRequest(), AryaThreadLocal.getResponse(),
				AryaThreadLocal.getServletContext());

		// Create action mapping
		AgemActionMapping mapping = new AgemActionMapping();
		mapping.setType("tr.com.agem.startup.login.LoginAction");
		mapping.setScope("request");
		mapping.setParameter(null);
		mapping.setName("loginForm");
		mapping.setAttribute("loginForm");
		mapping.setPath("/login");
		mapping.setMethod("login");
		mapping.setModuleConfig(new AgemModuleConfigImp(""));

		LoginAction loginAction = new LoginAction();
		try {
			loginAction.login(mapping, loginForm, (HttpServletRequest) AryaThreadLocal.getRequest(),
					(HttpServletResponse) AryaThreadLocal.getResponse());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

		String err = (String) AryaThreadLocal.getRequest().getAttribute(AgemConstant.AGEM_HATA);
		if (err != null) { // Login failed!
			String msg = (String) AryaThreadLocal.getRequest().getAttribute(AgemConstant.AGEM_MESSAGE);
			if (msg != null) {
				logger.log(Level.SEVERE, "AgemUtils error message: {0}", msg);
			}
			throw new AryaLoginFailedException();
		}

		KullaniciForm user = (KullaniciForm) UserContext.getContext().get(AgemConstant.C_USER);
		String dataStr = AgemUtils.jsObject(user);

		logger.log(Level.INFO, "Login action executed successfully: {0} ", dataStr);

		AryaAdaptorResponse response = new AryaAdaptorResponse();
		response.setData(dataStr);

		return response;
	}

	@Override
	public IAryaAdaptorResponse processLogout(IAryaRequest request) {

		logger.log(Level.INFO, "Calling logout action.");

		// Create action mapping
		AgemActionMapping mapping = new AgemActionMapping();
		mapping.setType("tr.com.agem.startup.logout.LogoutAction");
		mapping.setScope("request");
		mapping.setName("logoutForm");
		mapping.setPath("/logout");
		mapping.setMethod("logout");
		mapping.setModuleConfig(new AgemModuleConfigImp(""));

		LogoutAction logoutAction = new LogoutAction();
		try {
			logoutAction.logout(mapping, null, (HttpServletRequest) AryaThreadLocal.getRequest(),
					(HttpServletResponse) AryaThreadLocal.getResponse());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

		logger.log(Level.INFO, "Logout action executed successfully.");

		return null;
	}

	@Override
	public boolean checkLogin(IAryaRequest aryaRequest) {
		Object obj = AryaThreadLocal.getSession().getAttribute(AgemConstant.C_USER);
		return obj != null && obj instanceof User;
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

	private void addRequestParameters(IAryaRequest aryaRequest, ServletRequest httpRequest) {
		httpRequest.setAttribute("json", "1");
		// TODO add other arya request parameters and attributes to HTTP request
		// here!
	}

	private String convertToJson(ServletRequest httpRequest) {

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
