package tr.com.agem.arya.gateway;

import java.util.Map;

public interface IAryaRequest {
    Map<String, Object> getParams();
    RequestTypes getRequestType();
    String getAction();
    String toJSON();
}
