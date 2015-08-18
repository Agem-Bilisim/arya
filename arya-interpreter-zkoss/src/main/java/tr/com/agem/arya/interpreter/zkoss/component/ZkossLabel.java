package tr.com.agem.arya.interpreter.zkoss.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;

import tr.com.agem.arya.metadata.arya.impl.LabelType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossLabel extends ZkossComponent implements IAryaComponent {

	@Override
	public IAryaComponent create(Object object, Object parent) {
		setComponent(new Label());
		Label component = (Label) getComponent();
		component.setValue(((LabelType) object).getValue());
		component.setParent((Component) parent);
		return this;
	}
}