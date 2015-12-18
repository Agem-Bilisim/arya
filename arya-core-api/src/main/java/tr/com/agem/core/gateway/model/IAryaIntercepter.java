package tr.com.agem.core.gateway.model;

import javax.servlet.http.HttpServletRequest;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IAryaIntercepter {
	
	public AryaResponse setAttributeData(String applicationName, IMetadata metadata, 
			AryaResponse response, HttpServletRequest servletRequest);

}
