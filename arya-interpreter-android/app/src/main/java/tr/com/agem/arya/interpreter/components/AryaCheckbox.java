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
    private boolean mandatory;

    public AryaCheckbox(final Context context, XmlPullParser parser, LinearLayout window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
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
    public String validate(){
        // TODO valite component
        return null;
    }

    @Override
    public String getComponentId() {
        return componentId;
    }

    @Override
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}
