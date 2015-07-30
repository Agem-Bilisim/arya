package tr.com.agem.arya.interpreter.zkoss.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;

import tr.com.agem.arya.interpreter.zkoss.OnClickEventListener;
import tr.com.agem.arya.metadata.zul.impl.ButtonType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossButton extends ZkossComponent implements IAryaComponent {

	@SuppressWarnings("unchecked")
	@Override
	public IAryaComponent create(Object object, Object parent) {
		setComponent(new Button());
		Button component = (Button) getComponent();
		component.setLabel(((ButtonType) object).getLabel());
		component.setParent((Component) parent);
		if (((ButtonType) object).getOnClick() != null) {
			component.addEventListener(Events.ON_CLICK,
					new OnClickEventListener((Component) parent,
							((ButtonType) object).getOnClick()));
		}
		return this;
	}
}