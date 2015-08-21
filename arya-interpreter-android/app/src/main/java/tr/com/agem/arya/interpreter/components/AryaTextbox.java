package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Objects;

import tr.com.agem.arya.script.ScriptHelper;

public class AryaTextbox extends EditText implements IAryaComponent {

    private String className;
    private String componentId;
    private boolean mandatory;

    public AryaTextbox(Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
        // Class Name
        this.className = parser.getAttributeValue(null,"class");
        // Placeholder
        this.setHint(parser.getAttributeValue(null, "placeholder"));
        this.setHintTextColor(Color.parseColor("#FF999999"));
        this.setBackgroundResource(android.R.drawable.edit_text);
        this.setTextColor(Color.parseColor("#FF000000"));
        // Password EditText
        if ("password".equalsIgnoreCase(parser.getAttributeValue(null, "type"))) {
            this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        // Mandatory
        String mandatory = parser.getAttributeValue(null, "mandatory");
        this.mandatory = mandatory != null && Boolean.parseBoolean(mandatory);
        // Height
        String height = parser.getAttributeValue(null, "height");
        this.setHeight(height != null ? Integer.parseInt(height) : 150);
        // Value
        String value = parser.getAttributeValue(null, "value");
        if (value != null) {
            this.setText(value);
        }
        // Readonly
        String readonly = parser.getAttributeValue(null, "readonly");
        this.setEnabled(readonly != null && Boolean.parseBoolean(readonly));
        // Max length
        String maxLength = parser.getAttributeValue(null, "maxlength");
        if (maxLength != null) {
            InputFilter[] filterArr = new InputFilter[1];
            filterArr[0] = new InputFilter.LengthFilter(Integer.parseInt(maxLength));
            this.setFilters(filterArr);
        }
        // Visible
        String visible = parser.getAttributeValue(null, "visible");
        if (visible != null && !Boolean.parseBoolean(visible)) {
            this.setVisibility(View.INVISIBLE);
        }
        // onChange
        final String onChange = parser.getAttributeValue(null, "onChange");
        if (onChange != null) {
            this.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    HashMap<Object, Object> params = new HashMap<Object, Object>();
                    params.put("keyCode", new Integer(keyCode));
                    ScriptHelper.executeScript((IAryaComponent) v, onChange, params,null);
                    return true;
                }
            });
        }
        // Multiline
        String rowsStr = parser.getAttributeValue(null, "rows");
        int rows;
        if (rowsStr != null && (rows = Integer.parseInt(rowsStr)) > 0) {
            this.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            this.setLines(rows);
            this.setMinLines(rows);
            this.setMaxLines(rows + 2);
            this.setGravity(Gravity.TOP | Gravity.LEFT);
            this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            this.setVerticalScrollBarEnabled(true);
        }
        // Doublebox
        if ("doublebox".equalsIgnoreCase(parser.getName())) {
            this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        // Intbox
        if ("intbox".equalsIgnoreCase(parser.getName())) {
            this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        }
        window.addView(this);
    }

    @Override
    public String validate() {
        // TODO impl
        return null;
    }

    @Override
    public String getComponentId() {
        return componentId;
    }

    @Override
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public void setClassName(String className) { this.className=className;}

    @Override
    public String getClassName() {return className; }
}
