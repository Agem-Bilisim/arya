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
    private String className;
    public String attribute;
    public String value;

    public AryaLabel(Context context, XmlPullParser parser, final LinearLayout window) {
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

    public void setClassName(String className) { this.className=className;}
    public String getClassName() {return className; }

    public void setAttribute(String attribute) { this.attribute=attribute;}
    public String getAttribute() {return attribute; }

    public void setValue(String value) { this.value=value;}
    public String getValue() {return value; }

    @JsonIgnore
    @Override
    public int getImportantForAccessibility() {
        return super.getImportantForAccessibility();
    }
}
