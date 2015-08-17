package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

public class AryaDatebox  extends DatePicker implements IAryaComponent {
    public AryaDatebox(Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        window.addView(this);
    }
}
