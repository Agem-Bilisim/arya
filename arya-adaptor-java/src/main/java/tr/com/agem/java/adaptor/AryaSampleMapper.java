package tr.com.agem.java.adaptor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

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
import tr.com.agem.sndk.genel.ilce.IlceParameterForm;

public class AryaSampleMapper implements IAryaAdaptorMapper {
	private Map<String, Object> actionMap;
	private Map<String, String> actionViewMap;

	@Override
	public Object map(IAryaRequest request, DataSource dataSource,
			IAryaAdaptorConverter converter) {

		/*
		 * Initialize
		 */
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
		AgemCrudService service = new AgemCrudService();
		String data = null;

		String action = (String) getActionMap().get(request.getAction());
		String actionView = getActionViewMap().get(request.getAction());

		/*
		 * Mapping
		 */
		if (action.equalsIgnoreCase("il.list")) {
			IlParameterForm p = new IlParameterForm();
			PagedList list = new PagedList();
			list.setPageSize(-1);
			list.setParameters(p);
			list = service.list(list);
			data = AgemUtils.jsObject(list.getList());
		} else if (action.equalsIgnoreCase("ilce.list")) {
			IlceParameterForm p = new IlceParameterForm();
			p.setIlKoduParam(new Long((String) request.getParams()
					.get("ilKodu")));
			PagedList list = new PagedList();
			list.setPageSize(-1);
			list.setParameters(p);
			list = service.list(list);
			data = AgemUtils.jsObject(list.getList());
		}

		/*
		 * Response
		 */
		AryaAdaptorResponse returnVal = new AryaAdaptorResponse();
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
