package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.TableRow;

import org.xml.sax.Attributes;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaListItem extends TableRow implements IAryaComponent {
    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;


    public AryaListItem(Context context, Attributes attributes, AryaWindow window) {

        super(context);
        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

    }


    @Override
    public void setComponentParent(Object o) {
        AryaListBox lb = (AryaListBox) o;
        lb.addView(this);
    }

    @Override
    public String getComponentId() {
        return componentId;
    }

    @Override
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @Override
    public String getComponentValue() {
        return componentValue;
    }

    @Override
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    @Override
    public String getComponentClassName() {
        return componentClassName;
    }

    @Override
    public void setComponentClassName(String componentClassName) {
        this.componentClassName = componentClassName;
    }

    @Override
    public String getComponentAttribute() {
        return componentAttribute;
    }

    @Override
    public String validate() {
        return null;
    }


    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }
}
