package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import org.xml.sax.Attributes;

import tr.com.agem.arya.MainActivity;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaButton extends Button implements IAryaComponent {

    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;

    public AryaButton(final Context context, Attributes attributes, final AryaWindow window) {
        super(context);
        this.setBackgroundResource(android.R.drawable.btn_default);

        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

        this.setText(attributes.getValue("label"));
        String height =attributes.getValue("height");
        this.setHeight(height != null ? Integer.parseInt(height) : 150);
        final String onClick =  attributes.getValue("onClick");

        if (onClick != null) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScriptHelper.executeScript(onClick, null, window);
                }
            });
        }
        window.addView(this);
    }

    @Override
    public String validate(){ return null; }

    @Override
    public void setComponentParent(Object o) {

    }

    public String getComponentId() { return componentId; }
    public void setComponentId(String componentId) { this.componentId = componentId;}

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
