package tr.com.agem.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import tr.com.agem.core.context.AryaThreadLocal;

/**
 * AryaContextFilter sets HttpServletRequest, HttpServletResponse and
 * ServletContext instances on the AryaThreadLocal before the request is
 * forwarded to the target and unsets/clear them before returning the response
 * to the interpreter.
 * 
 */
public class AryaContextFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		AryaThreadLocal.set(request, response, getServletContext());
		filterChain.doFilter(request, response);
		AryaThreadLocal.unset();
	}

}
