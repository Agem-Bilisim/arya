package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import org.xml.sax.Attributes;
import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.core.interpreter.IAryaComponent;

/**
 * Created by volkan on 07.09.2015.
 */
public class AryaMultipleComboBox extends ExpandableListView implements IAryaComponent {

    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;

    public AryaMultipleComboBox(Context context, Attributes attributes, AryaWindow aryaWindow) {

        super(context);

        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

        aryaWindow.addView(this);
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
    public String getComponentId() {
        return componentId;
    }

    @Override
    public void setComponentId(String componentId) {
        this.componentId = componentId;
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

    @Override
    public String getComponentValue() {
        return componentValue;
    }

    @Override
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }
}
