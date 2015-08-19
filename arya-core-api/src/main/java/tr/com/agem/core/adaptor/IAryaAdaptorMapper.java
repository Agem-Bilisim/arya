package tr.com.agem.core.adaptor;

import java.util.Map;

import javax.sql.DataSource;

import tr.com.agem.core.gateway.model.IAryaRequest;

public interface IAryaAdaptorMapper {
	Object map(IAryaRequest request, DataSource dataSource,
			IAryaAdaptorConverter converter);

	Map<String, Object> getActionMap();

	Map<String, String> getActionViewMap();
}
