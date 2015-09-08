package tr.com.agem.core.adaptor;

import javax.sql.DataSource;

import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;

public abstract class AryaApplicationAdaptor implements IAryaAdaptor {

	private IAryaMapper mapper;

	private IAryaConverter converter;

	private DataSource dataSource;
	
	public IAryaAdaptorResponse handleRequest(IAryaRequest request) {
		if (RequestTypes.LOGIN.equals(request.getRequestType())) {
			return login(request);
		} else if (RequestTypes.LOGOUT.equals(request.getRequestType())) {
			return logout(request);
		}
		return processRequest(request);
	}

	public abstract IAryaAdaptorResponse processRequest(IAryaRequest request);

	public IAryaMapper getMapper() {
		return mapper;
	}

	public void setMapper(IAryaMapper mapper) {
		this.mapper = mapper;
	}

	public IAryaConverter getConverter() {
		return converter;
	}

	public void setConverter(IAryaConverter converter) {
		this.converter = converter;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
