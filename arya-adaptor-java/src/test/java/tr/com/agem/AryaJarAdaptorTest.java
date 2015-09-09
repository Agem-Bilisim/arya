package tr.com.agem;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.metadata.exception.AryaLoginFailedException;
import tr.com.agem.java.adaptor.AryaJarAdaptor;
import tr.com.agem.java.mapper.AryaJarMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml", "classpath:**/datasource-config.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
@Transactional
public class AryaJarAdaptorTest extends TestCase {

	@Autowired
	DriverManagerDataSource dataSource;

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

	@Test
	public void loginRequestShouldSucceed() {

		AryaJarAdaptor adaptor = new AryaJarAdaptor();
		adaptor.setMapper(new AryaJarMapper());
		adaptor.setDataSource(dataSource);

		// Prepare request
		AryaRequest request = new AryaRequest();
		request.setRequestType(RequestTypes.LOGIN);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "admin");
		params.put("password", "a93m5466");
		request.setParams(params);

		IAryaAdaptorResponse response = adaptor.handleRequest(request);
		assertNotNull("Response object cannot be null!", response);
		assertNotNull("Response data cannot be null", response.getData());
	}

	@Test(expected = AryaLoginFailedException.class)
	public void loginRequestShouldThrowException() {
		AryaJarAdaptor adaptor = new AryaJarAdaptor();
		adaptor.setMapper(new AryaJarMapper());
		adaptor.setDataSource(dataSource);

		// Prepare request
		AryaRequest request = new AryaRequest();
		request.setRequestType(RequestTypes.LOGIN);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "admin");
		request.setParams(params);

		adaptor.handleRequest(request);
	}

	@Test
	public void userShouldLoginAndLogout() {

		//
		// Login Request
		//
		AryaJarAdaptor adaptor = new AryaJarAdaptor();
		adaptor.setMapper(new AryaJarMapper());
		adaptor.setDataSource(dataSource);

		// Prepare request
		AryaRequest request = new AryaRequest();
		request.setRequestType(RequestTypes.LOGIN);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "admin");
		params.put("password", "a93m5466");
		request.setParams(params);

		adaptor.handleRequest(request);

		//
		// Logout Request
		//
		request = new AryaRequest();
		request.setRequestType(RequestTypes.LOGOUT);

		adaptor.handleRequest(request);
	}

}
