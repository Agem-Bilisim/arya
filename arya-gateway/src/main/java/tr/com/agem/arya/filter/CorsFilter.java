package tr.com.agem.arya.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Adds Access-Control headers to each response that allows CORS capabilities.
 * This class is only used for AngularJS interpreter and might be removed in the future. 
 */
public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Content-Type");
		((HttpServletResponse) response).addHeader("Access-Control-Max-Age", "1800");
		filterChain.doFilter(request, response);
	}
}
