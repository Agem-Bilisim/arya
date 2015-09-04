package tr.com.agem.arya.interpreter.utils;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;

@SuppressWarnings("rawtypes")
public class BaseController extends GenericForwardComposer {

	private static final long serialVersionUID = 8866650311533378984L;
	private Div componentContainer; // works as a parent component

	public BaseController() {
		super();
	}

	public Div getComponentContainer() {
		return componentContainer;
	}

	public void setComponentContainer(Div componentContainer) {
		this.componentContainer = componentContainer;
	}

}
