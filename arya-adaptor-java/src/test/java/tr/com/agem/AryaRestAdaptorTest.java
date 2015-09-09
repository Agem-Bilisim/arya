package tr.com.agem;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.java.adaptor.AryaRestAdaptor;
import tr.com.agem.java.converter.AryaRestConverter;
import tr.com.agem.java.mapper.AryaRestMapper;

@RunWith(JUnit4.class)
public class AryaRestAdaptorTest extends TestCase {
	
	@Test
	public void processRequestShouldReturnResponse() {
		
		AryaRestAdaptor adaptor = new AryaRestAdaptor();
		adaptor.setMapper(new AryaRestMapper());
		adaptor.setConverter(new AryaRestConverter());
		
		// Prepare request
		AryaRequest request = new AryaRequest();
		request.setAction("genel.kisi.list");
		request.setRequestType(RequestTypes.DATA_ONLY);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("json", 1);
		params.put("adParam", "mehmet");
		request.setParams(params);
		
		IAryaAdaptorResponse response = adaptor.handleRequest(request);
		assertNotNull("Response object cannot be null!", response);
		assertNotNull("Response data cannot be null!", response.getData());
	}

}
