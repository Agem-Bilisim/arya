package tr.com.agem.core.interpreter;

public interface IAryaComponent {
	public IAryaComponent create(Object object, Object parent, Object masterWindow);

	public void setProperty();

	public Object getProperty();

	public Object getComponent();

	public void setComponent(Object object);
}
