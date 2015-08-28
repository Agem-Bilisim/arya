package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.arya.script.ScriptHelper;

public class AryaLabel extends TextView implements IAryaComponent {

    private String componentId;
    private String componentClassName;
    private String componentAttribute;
    private String componentValue;

    public AryaLabel(Context context, XmlPullParser parser, final LinearLayout window) {
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
        this.setText(parser.getAttributeValue(null, "value"));
        // Height
        String height = parser.getAttributeValue(null, "height");
        this.setHeight(height != null ? Integer.parseInt(height) : 100);
        // OnClick
        final String onClick = parser.getAttributeValue(null, "onClick");
        if (onClick != null) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScriptHelper.executeScript((IAryaComponent) v, onClick);
                }
            });
        }
        window.addView(this);
    }

    public String validate(){return null; }

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
