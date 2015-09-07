package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;

import tr.com.agem.core.interpreter.IAryaComponent;

/**
 * Created by volkan on 28.08.2015.
 */
public class AryaItem extends View implements IAryaComponent {
    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;

    private String label;

    public AryaItem(Context context,String componentId, String componentValue, String label) {
        super(context);
        this.componentId = componentId;
        this.componentValue = componentValue;
        this.label = label;
    }

    public String toString(){
        return this.getLabel();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
    public void setComponentParent(Object o) {

    }

    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }
}
