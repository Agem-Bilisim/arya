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
		String packagePrefix = PropertyReader.property("package.name.prefix");
		assertNotNull("Package name prefix cannot be null!", packagePrefix);
	}
	
	@Test
	public void mapShouldReturnServiceAndFormNames() {
		AryaJarMapper mapper = new AryaJarMapper();
		AryaJarMappedRequest mr = (AryaJarMappedRequest) mapper.map("genel.kimlik.list");
		assertEquals("tr.com.agem.sndk.genel.kimlik.KimlikService", mr.getServiceName());
		assertEquals("tr.com.agem.sndk.genel.kimlik.KimlikParameterForm", mr.getFormName());
	}
	
	@Test
	public void mapShouldReturnActionMethodName() {
		AryaJarMapper mapper = new AryaJarMapper();
		AryaJarMappedRequest mr = (AryaJarMappedRequest) mapper.map("genel.kimlik.list");
		assertEquals("list", mr.getActionMethodName());
	}
	
	@Test
	public void mapShouldReturnPath() {
		AryaJarMapper mapper = new AryaJarMapper();
		AryaJarMappedRequest mr = (AryaJarMappedRequest) mapper.map("genel.kimlik.list");
		assertEquals("/genel/kimlik/list", mr.getPath());
	}
	
	@Test
	public void mapShouldReturnViewName() {
		AryaJarMapper mapper = new AryaJarMapper();
		AryaJarMappedRequest mr = (AryaJarMappedRequest) mapper.map("genel.kimlik.list");
		assertEquals("listKimlik", mr.getViewName());
	}
	
	@Test(expected=Exception.class)
	public void mapShouldThrowException() {
		AryaJarMapper mapper = new AryaJarMapper();
		mapper.map(null);
	}

}
