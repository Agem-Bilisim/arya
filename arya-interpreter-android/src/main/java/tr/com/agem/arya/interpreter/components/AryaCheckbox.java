package tr.com.agem.arya.interpreter.components;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.main.components.AryaWindow;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaCheckbox extends CheckBox implements IAryaComponent {

    private String componentId;
    private String componentClassName;
    private boolean mandatory;
    private String componentAttribute;
    private String componentValue;

    public AryaCheckbox(Attributes attributes, final AryaWindow window) {
        super(window.getContext());

        String height=null;
        String mandatory=null;
        String readonly=null;

        if(AryaUtils.isNotEmpty(attributes)){

            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");


            this.setText(attributes.getValue("label"));
            height =attributes.getValue("height");
            mandatory = attributes.getValue("mandatory");
            readonly = attributes.getValue("readonly");


            final String onCheck =attributes.getValue("onCheck");
            if (onCheck != null) {
                this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ScriptHelper.executeScript(onCheck, null, window);
                    }
                });
            }
        }

        this.setHeight(height != null ? Integer.parseInt(height) : 100);
        this.mandatory = mandatory != null && Boolean.parseBoolean(mandatory);
        this.setEnabled(AryaUtils.isNotEmpty(readonly)?Boolean.parseBoolean(readonly):true);

        window.addView(this);
    }

    @Override
    public String validate(){ return null;}

    @Override
    public void setComponentParent(Object o) {

    }

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
