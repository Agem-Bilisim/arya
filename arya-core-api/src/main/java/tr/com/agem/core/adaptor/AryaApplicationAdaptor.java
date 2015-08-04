package tr.com.agem.core.adaptor;

import tr.com.agem.core.gateway.model.IAryaRequest;

public abstract class AryaApplicationAdaptor 
{
	public IAryaAdaptorMapper mapper;
	
	public IAryaAdaptorConverter converter;
	
	public abstract IAryaAdaptorResponse processRequest(IAryaRequest request);

	public IAryaAdaptorMapper getMapper() {
		return mapper;
	}

	public void setMapper(IAryaAdaptorMapper mapper) {
		this.mapper = mapper;
	}

	public IAryaAdaptorConverter getConverter() {
		return converter;
	}

	public void setConverter(IAryaAdaptorConverter converter) {
		this.converter = converter;
	}
}
