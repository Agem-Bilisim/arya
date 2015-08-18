package tr.com.agem.java.adaptor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;

import tr.com.agem.common.AgemCrudService;
import tr.com.agem.common.AgemUtils;
import tr.com.agem.common.form.PagedList;
import tr.com.agem.core.adaptor.IAryaAdaptorConverter;
import tr.com.agem.core.adaptor.IAryaAdaptorMapper;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.db.connection.DBConnectionFactory;
import tr.com.agem.db.connection.DBConnectionInterface;
import tr.com.agem.db.dbms.PostgreSqlDBMS;
import tr.com.agem.db.operations.BDBase;
import tr.com.agem.sndk.genel.il.IlParameterForm;

public class AryaSampleMapper implements IAryaAdaptorMapper {
	private Map<String, Object> actionMap;
	private Map<String, String> actionViewMap;

	@Override
	public Object map(IAryaRequest request, DataSource dataSource,
			IAryaAdaptorConverter converter) {
		Object action = getActionMap().get(request.getAction());
		String actionView = getActionViewMap().get(request.getAction());

		try {
			final Connection conn = dataSource.getConnection();
			DBConnectionFactory.getInstance().setConnectionInterface(
					new DBConnectionInterface() {

						@Override
						public Connection getConnection() {
							return conn;
						}
					});

			BDBase.DBMS_INTERFACE = new PostgreSqlDBMS();
			BDBase.DBMS_INTERFACE.init();

			System.out.println(conn.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AryaAdaptorResponse returnVal = new AryaAdaptorResponse();

		IlParameterForm p = new IlParameterForm();

		// parametreler set edilecek.

		// il service var mi? // yoksa AgemCrudService kullanilacak

		AgemCrudService service = new AgemCrudService();

		PagedList list = new PagedList();
		list.setPageSize(-1);
		list.setParameters(p);
		
		
		list  = service.list(list);

		String data = AgemUtils.jsObject(list.getList());
		
//		ObjectMapper mapper = new ObjectMapper();
//		
//		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		try {
//			data = mapper.writeValueAsString(list.getList());
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}

		returnVal.setData(data);

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
