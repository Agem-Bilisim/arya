package tr.com.agem.arya.interpreter.components;

public interface IAryaComponent {


    void setComponentId(String componentId);
    String getComponentId();

    void setClassName(String className);
    String getClassName();

    void setValue(String value);
    String getValue();

    void setAttribute(String attribute);
    String getAttribute();

    String validate();
}
