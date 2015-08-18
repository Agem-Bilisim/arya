package tr.com.agem.arya.interpreter.components;

public interface IAryaComponent {
    void setComponentId(String componentId);
    String getComponentId();
    String validate();
}
