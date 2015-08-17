package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class AryaLabel extends TextView implements IAryaComponent {
    public AryaLabel(Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        this.setText(parser.getAttributeValue(null, "value"));
        this.setHeight(100);
        window.addView(this);
    }
}
