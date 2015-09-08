package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

import tr.com.agem.core.interpreter.IAryaComponent;

/**
 * Created by volkan on 07.09.2015.
 */
public class AryaMultipleComboItem extends View implements IAryaComponent {


    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;

    public AryaMultipleComboItem(Context context, Attributes attributes, AryaWindow aryaWindow) {
        super(context);
        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

        aryaWindow.addView(this);
    }

    public String toString(){
        return this.componentValue;
    }
    public String getComponentId() { return componentId; }

    public void setComponentId(String componentId) {this.componentId = componentId; }

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

        List<AryaMultipleComboItem> list = new ArrayList<AryaMultipleComboItem>();
        AryaMultipleComboBox amc = (AryaMultipleComboBox) o;

        list.add(this);

        ArrayAdapter<AryaMultipleComboItem> dataAdapter = new ArrayAdapter<AryaMultipleComboItem>(amc.getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        amc.setAdapter(dataAdapter);



    }

    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }

    @Override
    public String getComponentValue() {
        return componentValue;
    }

    @Override
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }
}
