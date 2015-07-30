package tr.com.agem.core.interpreter;

import java.util.List;

public interface IAryaInterpreter {
	void createComponents(Object parent, List<Object> components);

	public Object findComponentById(String id);
}
