package tr.com.agem.arya.interpreter.components;

import org.xml.sax.Attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import android.view.View;
import android.widget.TextView;

import tr.com.agem.arya.interpreter.main.components.AryaWindow;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaLabel extends TextView implements IAryaComponent {

    private String componentId;
    private String componentClassName;
    private String componentAttribute;
    private String componentValue;

    public AryaLabel(Attributes attributes, final AryaWindow window) {
        super(window.getContext());

        String height=null;

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            this.setText(attributes.getValue("value"));
            height = attributes.getValue("height");

            final String onClick = attributes.getValue("onClick");
            if (onClick != null) {
                this.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ScriptHelper.executeScript(onClick, null, window);
                    }
                });
            }
        }

        this.setHeight(height != null ? Integer.parseInt(height) : 100);
        window.addView(this);
    }

    public String validate(){return null; }

    @Override
    public void setComponentParent(Object o) {

    }

    public void setComponentId(String componentId) {this.componentId = componentId;}
    public String getComponentId(){
        return this.componentId;
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

    @JsonIgnore
    @Override
    public int getImportantForAccessibility() {
        return super.getImportantForAccessibility();
    }
}
