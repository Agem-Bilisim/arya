package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.arya.script.ScriptHelper;

public class AryaButton extends Button implements IAryaComponent {

    public String className;
    public String componentId;
    public String attribute;
    public String value;

    public AryaButton(final Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        this.setBackgroundResource(android.R.drawable.btn_default);

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
        this.setHeight(height != null ? Integer.parseInt(height) : 150);
        // OnClick
        final String onClick = parser.getAttributeValue(null, "onClick");

        if (onClick != null) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScriptHelper.executeScript((IAryaComponent) v, onClick,null,window );
                }
            });
        }
        window.addView(this);
    }


    @Override
    public String validate(){ return null; }

    public String getComponentId() { return componentId; }
    public void setComponentId(String componentId) { this.componentId = componentId;}

    public void setClassName(String className) { this.className=className;}
    public String getClassName() {return className; }

    public void setAttribute(String attribute) { this.attribute=attribute;}
    public String getAttribute() {return attribute; }

    public void setValue(String value) { this.value=value;}
    public String getValue() {return value; }

}
