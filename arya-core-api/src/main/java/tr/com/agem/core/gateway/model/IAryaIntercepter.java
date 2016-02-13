package tr.com.agem.core.gateway.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IAryaIntercepter {
	
	public AryaResponse intercept(String applicationName, IMetadata metadata, 
			AryaResponse response, HttpServletRequest servletRequest, HttpServletResponse servletResponse);

}
