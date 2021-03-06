package tr.com.agem.arya.metadata.component.base;

import java.util.HashSet;
import java.util.Set;

import tr.com.agem.arya.metadata.interpreter.IAryaComponent;

public class AryaGatewayMain {
	
	private Set<IAryaComponent> aryaSessionComponents;
	
	public AryaGatewayMain() {
		super();
		this.aryaSessionComponents = new HashSet<IAryaComponent>();
	}

	public Set<IAryaComponent> getAryaSessionComponents() {
		return aryaSessionComponents;
	}

	public void setAryaSessionComponents(Set<IAryaComponent> aryaSessionComponents) {
		this.aryaSessionComponents = aryaSessionComponents;
	}

}
