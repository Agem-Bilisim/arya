package tr.com.agem.core.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AryaContext {

	private ServletRequest request;
	private ServletResponse response;
	private AryaSession session;
	private ServletContext servletContext;

	protected AryaContext(ServletRequest request, ServletResponse response, AryaSession session,
			ServletContext servletContext) {
		super();
		this.request = request;
		this.response = response;
		this.session = session;
		this.servletContext = servletContext;
	}

	public void clear() {
		this.session = null;
		this.request = null;
		this.response = null;
	}

	public ServletRequest getRequest() {
		return request;
	}

	public ServletResponse getResponse() {
		return response;
	}

	public AryaSession getSession() {
		return session;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

}
