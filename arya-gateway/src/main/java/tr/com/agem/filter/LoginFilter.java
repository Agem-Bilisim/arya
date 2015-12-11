package tr.com.agem.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.common.AgemUtils;
import tr.com.agem.core.adaptor.IAryaAdaptor;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.utils.AryaUtils;
import tr.com.agem.filter.utils.LoginRequestWrapper;

public class LoginFilter extends OncePerRequestFilter {

	private static final Logger logger = Logger.getLogger(LoginFilter.class.getName());

	@Autowired
	private IAryaAdaptor adaptor;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		
//		@SuppressWarnings("unchecked")
//		Enumeration<String> attrs = session.getAttributeNames();
//		
//		System.out.println("SESSION-SUBE: "+session.getAttribute("SESSION_SUBE_LIST"));
//		
//		AgemUtils.jsObject(session.getAttribute("SESSION_SUBE_LIST"));
//		
//		while (attrs.hasMoreElements()) {
//			String attr = attrs.nextElement();
//		}

		LoginRequestWrapper requestWrapper = new LoginRequestWrapper((HttpServletRequest) request);
		AryaRequest aryaRequest = new ObjectMapper().readValue(requestWrapper.getInputStream(), AryaRequest.class);

		if ("menu".equals(aryaRequest.getAction())|| "login".equals(aryaRequest.getAction())|| RequestTypes.LOGIN.equals(aryaRequest.getRequestType()) || adaptor.checkLogin(aryaRequest)) {
			logger.log(Level.FINE, "User has already logged in!");
			filterChain.doFilter(request, response);
			
		} else {
			logger.log(Level.SEVERE, "User has not logged in: {0}",
					(aryaRequest.getParams() == null ? null : aryaRequest.getParams().get("username")));
			((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), "User must login first!");
		}

	}

}
