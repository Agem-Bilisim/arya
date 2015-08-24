package tr.com.agem;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.java.adaptor.AryaJarAdaptor;
import tr.com.agem.java.mapper.AryaJarMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ AryaDataSourceConfig.class })
//@Transactional
public class AryaJarAdaptorTest extends TestCase {
	
	@Autowired
	AryaDataSourceConfig dataSource;
	
	@Test
	public void processRequestShouldReturnResponse() {
		
		AryaJarAdaptor adaptor = new AryaJarAdaptor();
		adaptor.setMapper(new AryaJarMapper());
		adaptor.setDataSource(dataSource);
		
		// Prepare request
		AryaRequest request = new AryaRequest();
		request.setAction("genel.kisi.list");
		request.setRequestType(RequestTypes.DATA_ONLY);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("json", 1);
		params.put("adParam", "mehmet");
		request.setParams(params);
		
		IAryaAdaptorResponse response = adaptor.processRequest(request);
		assertNotNull("Response object cannot be null!", response);
		assertNotNull("Response data cannot be null!", response.getData());
	}

}
