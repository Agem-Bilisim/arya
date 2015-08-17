package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

public class AryaTextbox extends EditText implements IAryaComponent {
    public AryaTextbox(Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        this.setHint(parser.getAttributeValue(null, "value"));
        this.setHintTextColor(Color.parseColor("#FF999999"));
        this.setBackgroundResource(android.R.drawable.edit_text);
        this.setTextColor(Color.parseColor("#FF000000"));
        this.setHeight(150);
        window.addView(this);
    }
}
