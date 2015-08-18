package tr.com.agem.arya.interpreter.zkoss.component;

import tr.com.agem.core.interpreter.IAryaComponent;

public abstract class ZkossComponent implements IAryaComponent {
	private Object component;

	@Override
	public Object getComponent() {
		return component;
	}

	@Override
	public void setComponent(Object component) {
		this.component = component;
	}

	@Override
	public void setProperty() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getProperty() {
		// TODO Auto-generated method stub
		return null;
	}

}
