package tr.com.agem.core.gateway.model;

import java.util.Map;

public interface IAryaRequest 
{
	public static class RequestTypes 
	{
		public static final String VIEW_ONLY = "V";
		public static final String DATA_ONLY = "D";
		public static final String ALL = "A";
	}
	
	Map<String, Object> getParams();
	String getRequestType();
	String getAction();
	String toJSON();

}
