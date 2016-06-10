package tr.com.agem.arya.adaptor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.agem.common.AgemActionMapping;
import tr.com.agem.common.AgemConstant;
import tr.com.agem.common.AgemUtils;
import tr.com.agem.common.UserContext;
import tr.com.agem.core.adaptor.AryaAdaptorDefaultResponse;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.context.AryaThreadLocal;
import tr.com.agem.core.exception.AryaLoginFailedException;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.startup.kullanici.KullaniciForm;
import tr.com.agem.startup.login.LoginAction;
import tr.com.agem.startup.login.LoginForm;
import tr.com.agem.startup.logout.LogoutAction;
import tr.com.agem.struts.AgemModuleConfigImp;

public class AryaAsyaJarAdaptor extends AryaAgemUtilsJARAdaptor {

	private static final Logger logger = Logger.getLogger(AryaAsyaJarAdaptor.class.getName());

	
	@Override
	public IAryaAdaptorResponse processLogin(IAryaRequest request) {

		super.initDBConnection();
		
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

		AryaAdaptorDefaultResponse response = new AryaAdaptorDefaultResponse();
		response.setData(dataStr);
		
		UserContext.clear();
		
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
			((HttpServletRequest) AryaThreadLocal.getRequest()).getSession().invalidate();
			logoutAction.logout(mapping, null, (HttpServletRequest) AryaThreadLocal.getRequest(),
					(HttpServletResponse) AryaThreadLocal.getResponse());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

		logger.log(Level.INFO, "Logout action executed successfully.");

		return null;
	}
}
