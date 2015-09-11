package tr.com.agem.core.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AryaThreadLocal {

	private static InheritableThreadLocal<AryaContext> threadLocal = new InheritableThreadLocal<AryaContext>();

	public static void set(ServletRequest request, ServletResponse response, ServletContext servletContext) {
		AryaSession session = null;
		if (request instanceof HttpServletRequest) {
			session = new AryaSession(((HttpServletRequest) request).getSession());
		}
		AryaContext context = new AryaContext(request, response, session, servletContext);
		threadLocal.set(context);
	}
	
	public static void set(ServletRequest request, ServletResponse response) {
		set(request, response, null);
	}
	
	public static void unset() {
		threadLocal.get().clear();
		threadLocal.remove();
	}
	
	public static ServletRequest getRequest() {
		AryaContext c = threadLocal.get();
		return (c == null) ? null : c.getRequest();
	}
	
	public static ServletResponse getResponse() {
		AryaContext c = threadLocal.get();
		return (c == null) ? null : c.getResponse();
	}
	
	public static AryaSession getSession() {
		AryaContext c = threadLocal.get();
		return (c == null) ? null : c.getSession();
	}
	
	public static ServletContext getServletContext() {
		AryaContext c = threadLocal.get();
		return (c == null) ? null : c.getServletContext();
	}
	
}
