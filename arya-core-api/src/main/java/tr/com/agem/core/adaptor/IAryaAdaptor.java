package tr.com.agem.core.adaptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import tr.com.agem.core.gateway.model.IAryaRequest;

public interface IAryaAdaptor {
	public IAryaMapper getMapper();
	public IAryaConverter getConverter();
	public IAryaAdaptorResponse handleRequest(IAryaRequest request);
	public IAryaAdaptorResponse processRequest(IAryaRequest request);
	public IAryaAdaptorResponse processLogin(IAryaRequest request);
	public IAryaAdaptorResponse processLogout(IAryaRequest request);
	public boolean checkLogin(ServletRequest request, ServletResponse response, IAryaRequest aryaRequest);
}
