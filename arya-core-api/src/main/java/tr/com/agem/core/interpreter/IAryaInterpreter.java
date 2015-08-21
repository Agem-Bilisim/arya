package tr.com.agem.core.interpreter;

import java.util.List;

import tr.com.agem.core.gateway.model.AryaResponse;

public interface IAryaInterpreter {
	void interpretAryaResponse(String responseStr, Object parent, Object masterWindow);

	void interpretAryaResponse(AryaResponse response, Object parent, Object masterWindow);

	void createComponents(Object parent, List<Object> components);

	public Object findComponentById(String id);
}
