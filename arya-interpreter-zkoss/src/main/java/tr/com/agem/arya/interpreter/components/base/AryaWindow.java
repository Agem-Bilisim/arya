package tr.com.agem.arya.interpreter.components.base;

import java.util.ArrayList;
import java.util.List;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaWindow{

	private List<IAryaComponent> components;
	
	public AryaWindow() {
		components = new ArrayList<>();
	}
	
	public List<IAryaComponent> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponent> components) {
		this.components = components;
	}
}
