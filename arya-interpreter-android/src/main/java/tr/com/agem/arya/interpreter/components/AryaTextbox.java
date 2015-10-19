package tr.com.agem.arya.interpreter.components;

import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.xml.sax.Attributes;

import java.util.HashMap;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaTextbox extends EditText implements IAryaComponent {

    private String componentClassName;
    private String componentId;
    private boolean mandatory;
    private String componentAttribute;
    private String componentValue;

    public AryaTextbox(Attributes attributes, final AryaMain main, String tag) {
        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");


            // Placeholder
            this.setHint(attributes.getValue("placeholder"));
            this.setHintTextColor(Color.parseColor("#FF999999"));
            this.setBackgroundResource(android.R.drawable.edit_text);
            this.setTextColor(Color.parseColor("#FF000000"));
            // Password EditText
            if ("password".equalsIgnoreCase(attributes.getValue("type"))) {
                this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            // Mandatory
            String mandatory = attributes.getValue("mandatory");
            this.mandatory = mandatory != null && Boolean.parseBoolean(mandatory);
            // Height
            String height = attributes.getValue("height") ;
            this.setHeight(height != null ? Integer.parseInt(height) : 150);
            // Value
            String value =attributes.getValue("value") ;
            if (value != null) {
                this.setText(value);
            }
            // Readonly
            //String readonly =attributes.getValue("readonly") ;
            //this.setEnabled(readonly != null && Boolean.parseBoolean(readonly));
            // Max length
            String maxLength =attributes.getValue("maxlength");
            if (maxLength != null) {
                InputFilter[] filterArr = new InputFilter[1];
                filterArr[0] = new InputFilter.LengthFilter(Integer.parseInt(maxLength));
                this.setFilters(filterArr);
            }
            // Visible
            String visible = attributes.getValue("visible");
            if (visible != null && !Boolean.parseBoolean(visible)) {
                this.setVisibility(View.INVISIBLE);
            }
            // onChange
            final String onChange = attributes.getValue("onChange");
            if (onChange != null) {
                this.setOnKeyListener(new OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        HashMap<Object, Object> params = new HashMap<Object, Object>();
                        params.put("keyCode", new Integer(keyCode));
                        ScriptHelper.executeScript(onChange, params, main);
                        return true;
                    }
                });
            }
            // Multiline
            String rowsStr =attributes.getValue("placeholder");
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

            if("doublebox".equalsIgnoreCase(tag) | "decimalbox".equalsIgnoreCase(tag)){
                this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }else if("intbox".equalsIgnoreCase(tag)||"longbox".equalsIgnoreCase(tag)){
                this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            }else if("timebox".equalsIgnoreCase(tag)) {
                this.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME | InputType.TYPE_CLASS_DATETIME);
            }

        }

        main.getAryaWindow().addView(this);
    }

    @Override
    public String validate() {
        // TODO impl
        return null;
    }

    @Override
    public void setComponentParent(Object o) {

    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getComponentId() { return componentId; }

    public void setComponentId(String componentId) {this.componentId = componentId; }

    @Override
    public String getComponentClassName() {
        return componentClassName;
    }

    @Override
    public void setComponentClassName(String componentClassName) {
        this.componentClassName = componentClassName;
    }

    @Override
    public String getComponentAttribute() {
        return componentAttribute;
    }

    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }

    @Override
    public String getComponentValue() {
        return this.getText().toString();
    }

    @Override
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }
}
