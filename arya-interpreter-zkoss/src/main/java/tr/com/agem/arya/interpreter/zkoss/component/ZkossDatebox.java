package tr.com.agem.arya.interpreter.zkoss.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;

import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossDatebox extends ZkossComponent implements IAryaComponent {

	@Override
	public IAryaComponent create(Object object, Object parent) {
		setComponent(new Datebox());
		Datebox component = (Datebox) getComponent();
		component.setParent((Component) parent);
		return this;
	}
}