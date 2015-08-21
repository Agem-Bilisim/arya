package tr.com.agem.arya.interpreter.zkoss.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;

import tr.com.agem.arya.metadata.arya.impl.DateboxType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossDatebox extends ZkossComponent implements IAryaComponent {

	@Override
	public IAryaComponent create(Object object, Object parent, Object masterWindow) {
		setComponent(new Datebox());
		Datebox component = (Datebox) getComponent();
		component.setId(((DateboxType) object).getId());
		component.setParent((Component) parent);
		return this;
	}
}