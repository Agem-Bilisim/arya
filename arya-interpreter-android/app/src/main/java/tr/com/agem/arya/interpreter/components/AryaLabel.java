package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.arya.script.ScriptHelper;

public class AryaLabel extends TextView implements IAryaComponent {

    private String componentId;
    private String className;

    public AryaLabel(Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
        // Class Name
        this.className = parser.getAttributeValue(null,"class");
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

    @Override
    public String validate(){return null; }

    @Override
    public void setComponentId(String componentId) {this.componentId = componentId;}

    @Override
    public String getComponentId(){
        return this.componentId;
    }

    @Override
    public void setClassName(String className) { this.className=className;}

    @Override
    public String getClassName() {return className; }
}
