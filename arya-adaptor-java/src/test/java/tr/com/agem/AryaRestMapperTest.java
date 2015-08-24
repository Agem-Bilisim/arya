package tr.com.agem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import tr.com.agem.core.property.reader.PropertyReader;
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
		String URL = mapper.map("kimlik.list");
		assertEquals("http://91.191.171.127/asya/rest/genel/kimlik/list", URL);
	}

	@Test(expected=Exception.class)
	public void mapShouldThrowException() {
		AryaRestMapper mapper = new AryaRestMapper();
		mapper.map(null);
	}

}
