package tr.com.agem.core.adaptor;

import javax.sql.DataSource;

import tr.com.agem.core.gateway.model.IAryaRequest;

public abstract class AryaApplicationAdaptor {
	private IAryaAdaptorMapper mapper;

	private IAryaAdaptorConverter converter;

	private DataSource dataSource;

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

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
