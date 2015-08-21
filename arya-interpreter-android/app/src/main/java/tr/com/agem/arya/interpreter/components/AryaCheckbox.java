package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.arya.interpreter.AlertController;
import tr.com.agem.arya.script.ScriptHelper;


public class AryaCheckbox extends CheckBox implements IAryaComponent {

    private String componentId;
    private String className;
    private boolean mandatory;
    public String attribute;
    public String value;

    public AryaCheckbox(final Context context, XmlPullParser parser, LinearLayout window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
        // Class Name
        this.className = parser.getAttributeValue(null,"class");
        //Value
        this.value=parser.getAttributeValue(null,"value");
        //Attribute
        this.attribute=parser.getAttributeValue(null,"attribute");
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

    public void setClassName(String className) { this.className=className;}
    public String getClassName() {return className; }

    public void setAttribute(String attribute) { this.attribute=attribute;}
    public String getAttribute() {return attribute; }

    public void setValue(String value) { this.value=value;}
    public String getValue() {return value; }
}
