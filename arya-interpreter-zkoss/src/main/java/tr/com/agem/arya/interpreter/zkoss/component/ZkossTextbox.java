package tr.com.agem.arya.interpreter.zkoss.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Textbox;

import tr.com.agem.arya.metadata.arya.impl.TextboxType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossTextbox extends ZkossComponent implements IAryaComponent {

	@Override
	public IAryaComponent create(Object object, Object parent) {
		setComponent(new Textbox());
		Textbox component = (Textbox) getComponent();
		component.setValue(((TextboxType) object).getValue());
		component.setParent((Component) parent);
		return this;
	}
}