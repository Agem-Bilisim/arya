package tr.com.agem.arya.interpreter.components;

import org.xml.sax.Attributes;

import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaAttribute implements IAryaComponent {

    private String componentClassName;
    private String componentAttribute;
    private String id;

    private String name;
    private String value;
    private String comp;

    public AryaAttribute(Attributes attributes) {
        super();

        if (AryaUtils.isNotEmpty(attributes)) {
            if (AryaUtils.isNotEmpty(attributes)) {
                this.componentClassName = attributes.getValue("class");
                this.componentAttribute = attributes.getValue("attribute");

                if(attributes.getValue("id") != null){
                    this.id = attributes.getValue("id");
                }

                this.name = attributes.getValue("name");
                this.value = attributes.getValue("value");
                this.comp = attributes.getValue("comp");
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setComponentClassName(String componentClassName) {
        this.componentClassName = componentClassName;
    }

    @Override
    public String getComponentClassName() {
        return componentClassName;
    }

    @Override
    public void setComponentValue(String componentValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getComponentValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }

    @Override
    public String getComponentAttribute() {
        return componentAttribute;
    }

    @Override
    public String validate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setComponentParent(Object parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object getComponentParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDatabase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDatabase(String database) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getComponentTagName() {
        return "attribute";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    @Override
    public void setComponentId(String componentId) {
        setId(componentId);
    }

    @Override
    public String getComponentId() {
        return getId();
    }
}
