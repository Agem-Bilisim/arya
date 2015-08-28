package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.arya.script.ScriptHelper;


public class AryaCheckbox extends CheckBox implements IAryaComponent {

    private String componentId;
    private String componentClassName;
    private boolean mandatory;
    private String componentAttribute;
    private String componentValue;

    public AryaCheckbox(final Context context, XmlPullParser parser, LinearLayout window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
        // Class Name
        this.componentClassName = parser.getAttributeValue(null,"class");
        //Value
        this.componentValue=parser.getAttributeValue(null,"value");
        //Attribute
        this.componentAttribute=parser.getAttributeValue(null,"attribute");
        // Label
        this.setText(parser.getAttributeValue(null, "label"));
        // Height
        String height = parser.getAttributeValue(null, "height");
        this.setHeight(height != null ? Integer.parseInt(height) : 100);
        // Mandatory
        String mandatory = parser.getAttributeValue(null, "mandatory");
        this.mandatory = mandatory != null && Boolean.parseBoolean(mandatory);
        // Readonly
        String readonly = parser.getAttributeValue(null, "readonly");
        this.setEnabled(readonly != null && Boolean.parseBoolean(readonly));
        // OnCheck
        final String onCheck = parser.getAttributeValue(null, "onCheck");
        if (onCheck != null) {
            this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ScriptHelper.executeScript((IAryaComponent) buttonView, onCheck);
                }
            });
        }
        window.addView(this);
    }

    @Override
    public String validate(){ return null;}

    public boolean isMandatory() { return mandatory;}

    public void setMandatory(boolean mandatory) {this.mandatory = mandatory;}

    public String getComponentId() {return componentId;}
    public void setComponentId(String componentId) {this.componentId = componentId;}

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
