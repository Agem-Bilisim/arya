package tr.com.agem;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import tr.com.agem.core.context.AryaThreadLocal;

public class AryaAdaptorTestHelper {

	public static void mockAryaThreadLocal() {
		AryaThreadLocal.set(new MockHttpServletRequest(), new MockHttpServletResponse(), new MockServletContext());
	}
	
	public static void clearAryaThreadLocal() {
		AryaThreadLocal.unset();
	}
	
}
