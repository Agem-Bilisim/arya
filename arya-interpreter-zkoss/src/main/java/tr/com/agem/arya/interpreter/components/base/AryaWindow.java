package tr.com.agem.arya.interpreter.components.base;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Div;

import tr.com.agem.arya.interpreter.utils.BaseController;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaWindow extends BaseController {

	private static final long serialVersionUID = 8576119465618112119L;
	private List<IAryaComponent> components;
	private Div componentContainer;
	
	public AryaWindow(Div componentContainer) {
		super();
		components = new ArrayList<>();
		this.componentContainer=componentContainer;
	}
	
	public List<IAryaComponent> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponent> components) {
		this.components = components;
	}

	public Div getComponentContainer() {
		return componentContainer;
	}


	public void setComponentContainer(Div componentContainer) {
		this.componentContainer = componentContainer;
	}

}
