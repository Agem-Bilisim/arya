package tr.com.agem.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.core.adaptor.IAryaAdaptor;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.filter.utils.LoginRequestWrapper;

public class LoginFilter extends OncePerRequestFilter {

	private static final Logger logger = Logger.getLogger(LoginFilter.class.getName());

	@Autowired
	private IAryaAdaptor adapter;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		LoginRequestWrapper requestWrapper = new LoginRequestWrapper((HttpServletRequest) request);
		AryaRequest aryaRequest = new ObjectMapper().readValue(requestWrapper.getInputStream(), AryaRequest.class);

		if ("login".equals(aryaRequest.getAction())|| RequestTypes.LOGIN.equals(aryaRequest.getRequestType()) || adapter.checkLogin(aryaRequest)) {
			logger.log(Level.FINE, "User has already logged in!");
			filterChain.doFilter(requestWrapper, response);
		} else {
			logger.log(Level.SEVERE, "User has not logged in: {0}",
					(aryaRequest.getParams() == null ? null : aryaRequest.getParams().get("username")));
			((HttpServletResponse) response).sendError(HttpStatus.BAD_REQUEST.value(), "User must login first!");
		}

	}

}
