package tr.com.agem.arya.interpreter.components.base;

import java.util.HashSet;
import java.util.Set;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaWindow{

	private Set<IAryaComponent> components;
	
	public AryaWindow() {
		components = new HashSet<IAryaComponent>();
	}
	
	public Set<IAryaComponent> getComponents() {
		return components;
	}

	public void setComponents(Set<IAryaComponent> components) {
		this.components = components;
	}
}
