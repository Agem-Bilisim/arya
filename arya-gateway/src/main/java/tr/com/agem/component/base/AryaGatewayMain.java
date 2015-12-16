package tr.com.agem.component.base;

import java.util.HashSet;
import java.util.Set;

import tr.com.agem.core.interpreter.IAryaComponent;

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
