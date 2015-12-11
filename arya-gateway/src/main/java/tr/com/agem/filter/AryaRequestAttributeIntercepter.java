package tr.com.agem.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.core.gateway.model.AryaRequest;



public class AryaRequestAttributeIntercepter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		
		AryaRequest aryaRequest = new ObjectMapper().readValue(request.getInputStream(), AryaRequest.class);
		
		HttpSession session = request.getSession();
		String jsonStr = (String) session.getAttribute(aryaRequest.getAttributeName());
		
		System.out.println("intercepter ----- ");
		
		filterChain.doFilter(request, response);
		
	}
}
