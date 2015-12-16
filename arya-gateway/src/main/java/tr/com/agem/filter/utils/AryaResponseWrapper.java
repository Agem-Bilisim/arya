package tr.com.agem.filter.utils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class AryaResponseWrapper extends HttpServletResponseWrapper {
	
	private String responseBody;

	public AryaResponseWrapper(HttpServletResponse response) {
		super(response);
	}
	
	public void test() {
		System.out.println("responsewrapper----------"+responseBody);
	}
}
