package tr.com.agem.arya.interpreter.zkoss.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;

import tr.com.agem.arya.interpreter.zkoss.OnCheckEventListener;
import tr.com.agem.arya.metadata.arya.impl.CheckboxType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossCheckbox extends ZkossComponent implements IAryaComponent {
	@SuppressWarnings("unchecked")
	@Override
	public IAryaComponent create(Object object, Object parent, Object masterWindow) {
		setComponent(new Checkbox());
		Checkbox component = (Checkbox) getComponent();
		component.setId(((CheckboxType) object).getId());
		component.setLabel(((CheckboxType) object).getLabel());
		component.setParent((Component) parent);
		if (((CheckboxType) object).getOnCheck() != null) {
			component.addEventListener(Events.ON_CHECK,
					new OnCheckEventListener(component, (Component) parent, ((CheckboxType) object).getOnCheck()));
		}
		return this;
	}
}