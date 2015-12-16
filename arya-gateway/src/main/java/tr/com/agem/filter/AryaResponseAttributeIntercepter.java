package tr.com.agem.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import tr.com.agem.filter.utils.AryaResponseWrapper;

public class AryaResponseAttributeIntercepter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		AryaResponseWrapper responseWrapper = new AryaResponseWrapper(response);
		filterChain.doFilter(request, responseWrapper);
		responseWrapper.test();
		
	}
	
}
