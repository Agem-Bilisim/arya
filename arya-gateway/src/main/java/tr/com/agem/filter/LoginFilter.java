package tr.com.agem.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.core.adaptor.IAryaAdaptor;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.filter.utils.CopyPrintWriter;

public class LoginFilter implements Filter {
	private static final Logger logger = Logger.getLogger(LoginFilter.class.getName());

	private IAryaAdaptor adapter = null;
	private ApplicationContext appContext = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		appContext = new ClassPathXmlApplicationContext(config.getInitParameter("adaptorConfigLocation"));
		adapter = (IAryaAdaptor) appContext.getBean("applicationAdaptor");
		logger.log(Level.INFO, "Found adapter instance!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.log(Level.INFO, "TEST1");
		final CopyPrintWriter writer = new CopyPrintWriter(response.getWriter());

		logger.log(Level.INFO, "Created copy writer");
		
		String jsonStr = writer.getCopy();
		ObjectMapper mapper = new ObjectMapper();
		AryaRequest aryaRequest = mapper.readValue(jsonStr, AryaRequest.class);

		if ("login".equals(aryaRequest.getAction()) || adapter.checkLogin(request, response, aryaRequest)) {
			logger.log(Level.INFO, "Passing request to chain!");
			chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
				@Override
				public PrintWriter getWriter() {
					return writer;
				}
			});
		} else {
			logger.log(Level.INFO, "User must login!");
			((HttpServletResponse) response).sendError(HttpStatus.BAD_REQUEST.value(), "User must login first!");
		}

		logger.log(Level.INFO, "TEST2");
	}

	@Override
	public void destroy() {
		appContext = null;
		adapter = null;
	}

}
