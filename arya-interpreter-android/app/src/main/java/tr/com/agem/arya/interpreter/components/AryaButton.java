package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import tr.com.agem.arya.gateway.*;
import tr.com.agem.arya.interpreter.AlertController;
import tr.com.agem.arya.script.ScriptHelper;

public class AryaButton extends Button implements IAryaComponent {

    private String componentId;
    private String className;

    public AryaButton(final Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        this.setBackgroundResource(android.R.drawable.btn_default);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
        // Class Name
        this.className = parser.getAttributeValue(null,"class");
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

    @Override
    public String getComponentId() { return componentId; }

    @Override
    public void setComponentId(String componentId) { this.componentId = componentId;}

    @Override
    public void setClassName(String className) { this.className=className;}

    @Override
    public String getClassName() {return className; }
}
