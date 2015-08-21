package tr.com.agem.arya.interpreter.components;

public interface IAryaComponent {

    void setComponentId(String componentId);
    String getComponentId();

    void setClassName(String className);
    String getClassName();

    String validate();
}
