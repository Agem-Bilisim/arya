package tr.com.agem.java.adaptor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import tr.com.agem.core.adaptor.IAryaAdaptorConverter;
import tr.com.agem.core.adaptor.IAryaAdaptorMapper;
import tr.com.agem.core.gateway.model.IAryaRequest;

public class AryaSampleMapper implements IAryaAdaptorMapper {
	private Map<String, Object> actionMap;
	private Map<String, String> actionViewMap;

	@Override
	public Object map(IAryaRequest request, DataSource dataSource,
			IAryaAdaptorConverter converter) {
		Object action = getActionMap().get(request.getAction());
		String actionView = getActionViewMap().get(request.getAction());

		AryaAdaptorResponse returnVal = new AryaAdaptorResponse();
		/*
		 * IlParameterForm p = new IlParameterForm();
		 * 
		 * // parametreler set edilecek.
		 * 
		 * // il service var mi? // yoksa AgemCrudService kullanilacak
		 * 
		 * AgemCrudService service = new AgemCrudService(); List<?> col =
		 * service.list(p);
		 * 
		 * String data = AgemUtils.jsObject(col);
		 */
		
		try {
			Connection conn = dataSource.getConnection();
			System.out.println(conn.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		returnVal.setData("");

		returnVal.setViewName(actionView);

		return returnVal;
	}

	@Override
	public Map<String, Object> getActionMap() {
		return actionMap;
	}

	public void setActionMap(Map<String, Object> actionMap) {
		this.actionMap = actionMap;
	}

	@Override
	public Map<String, String> getActionViewMap() {
		return actionViewMap;
	}

	public void setActionViewMap(Map<String, String> actionViewMap) {
		this.actionViewMap = actionViewMap;
	}

}
