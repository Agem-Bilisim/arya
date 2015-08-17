package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.arya.interpreter.AlertController;


public class AryaCheckbox extends CheckBox implements IAryaComponent {

    public AryaCheckbox(final Context context, XmlPullParser parser, LinearLayout window) {
        super(context);
        this.setText(parser.getAttributeValue(null, "label"));
        this.setHeight(100);
        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    AlertController.setAndShowPrimerAlert(context, "Arya", "Deneme", "Tamam");
                }
            }
        });
        window.addView(this);
    }


}
