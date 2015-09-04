package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaWindow extends LinearLayout {

    private static final String TAG = "LinearLayout";

    private List<IAryaComponent> components;

    public AryaWindow(Context context, LinearLayout parent) {
        super(context);

        parent.removeAllViews();
        parent.addView(this);
        components = new ArrayList<IAryaComponent>();

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

    public List<IAryaComponent> getComponents() {
        return components;
    }

    public void setComponents(List<IAryaComponent> components) {
        this.components = components;
    }

}
