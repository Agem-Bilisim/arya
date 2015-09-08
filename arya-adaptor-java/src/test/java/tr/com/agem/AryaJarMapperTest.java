package tr.com.agem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.java.mapper.AryaJarMappedRequest;
import tr.com.agem.java.mapper.AryaJarMapper;

@RunWith(JUnit4.class)
public class AryaJarMapperTest extends TestCase {
	
	@Test
	public void packagePrefixShouldExists() {
		String packagePrefix = PropertyReader.property("");
		assertNotNull("Package name prefix cannot be null!", packagePrefix);
	}
	
	@Test
	public void mapShouldReturnURI() {
		AryaJarMapper mapper = new AryaJarMapper();
		AryaJarMappedRequest mr = (AryaJarMappedRequest) mapper.map("genel.kimlik.list");
		assertEquals("tr.com.agem.sndk.genel.kimlik.KimlikService", mr.getServiceName());
		assertEquals("tr.com.agem.sndk.genel.kimlik.KimlikParameterForm", mr.getFormName());
		assertEquals("list", mr.getActionMethodName());
		assertEquals("/genel/kimlik/list", mr.getPath());
	}
	
	@Test(expected=Exception.class)
	public void mapShouldThrowException() {
		AryaJarMapper mapper = new AryaJarMapper();
		mapper.map(null);
	}

}
