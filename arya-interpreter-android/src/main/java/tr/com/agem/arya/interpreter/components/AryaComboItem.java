package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import org.xml.sax.Attributes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import tr.com.agem.core.interpreter.IAryaComponent;

/**
 * Created by volkan on 28.08.2015.
 */
public class AryaComboItem extends View implements IAryaComponent {
    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;

    private String label;

    public AryaComboItem(final Context context, Attributes attributes, final AryaWindow window) {
        super(context);
        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

        this.setLabel(attributes.getValue("label"));
        window.addView(this);
    }

    public String toString(){
        return this.label;
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
        AryaComboBox ac = (AryaComboBox) o;
        List<AryaComboItem> list = new ArrayList<AryaComboItem>();

        if(ac.getAdapter()!=null)
            ((ArrayAdapter<AryaComboItem>) ac.getAdapter()).add(this);
        else{
            list.add(this);
            ArrayAdapter<AryaComboItem> dataAdapter = new ArrayAdapter<AryaComboItem>(ac.getContext(), android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ac.setAdapter(dataAdapter);
        }


    }

    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }
}
