package tr.com.agem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.java.mapper.AryaRestMappedRequest;
import tr.com.agem.java.mapper.AryaRestMapper;

@RunWith(JUnit4.class)
public class AryaRestMapperTest extends TestCase {
	
	@Test
	public void baseURLShouldExists() {
		String baseURL = PropertyReader.property("rest.base.url");
		assertNotNull("Base URL cannot be null!", baseURL);
	}
	
	@Test
	public void mapShouldReturnURL() {
		AryaRestMapper mapper = new AryaRestMapper();
		AryaRestMappedRequest mr = (AryaRestMappedRequest) mapper.map("genel.kimlik.list");
		assertEquals("http://91.191.171.127/asya/rest/genel/kimlik/list", mr.getActionURL());
	}

	@Test(expected=Exception.class)
	public void mapShouldThrowException() {
		AryaRestMapper mapper = new AryaRestMapper();
		mapper.map(null);
	}

}
