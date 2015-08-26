package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

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