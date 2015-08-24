package tr.com.agem.core.adaptor;

import javax.sql.DataSource;

import tr.com.agem.core.gateway.model.IAryaRequest;

public abstract class AryaApplicationAdaptor implements IAryaAdaptor {

	private IAryaMapper mapper;

	private IAryaConverter converter;

	private DataSource dataSource;

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
