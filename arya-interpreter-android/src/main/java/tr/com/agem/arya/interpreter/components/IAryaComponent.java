package tr.com.agem.arya.interpreter.components;

public interface IAryaComponent {

    void setComponentId(String componentId);
    String getComponentId();

    void setComponentClassName(String componentClassName);
    String getComponentClassName();

    void setComponentValue(String componentValue);
    String getComponentValue();

    void setComponentAttribute(String componentAttribute);
    String getComponentAttribute();

    String validate();
}
