package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;

import tr.com.agem.arya.R;

public class AryaWindow extends LinearLayout {

    private static final String TAG = "LinearLayout";

    public AryaWindow(Context context, LinearLayout parent) {
        super(context);
        parent.addView(this);
        this.setOrientation(VERTICAL);
        this.setMinimumWidth(LayoutParams.MATCH_PARENT);
        this.setMinimumHeight(LayoutParams.MATCH_PARENT);
        TextView label = new TextView(context);
        label.setText("ARYA");
        label.setTextColor(0xFFFFFFFF);
        label.setTextSize(20);
        label.setTypeface(null, Typeface.BOLD);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0xFF6485CF);
        label.setHeight(150);
        label.setWidth(400);
        this.addView(label);
    }
}
