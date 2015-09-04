package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.DatePicker;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;
import java.util.HashMap;

import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaDatebox  extends DatePicker implements IAryaComponent {

    private String componentId;
    private String componentClassName;
    private boolean mandatory;
    private String componentAttribute;
    private String componentValue;

    public AryaDatebox(Context context, XmlPullParser parser, final AryaWindow window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
        // Class Name
        this.componentClassName = parser.getAttributeValue(null,"class");
        //Value
        this.componentValue=parser.getAttributeValue(null,"value");
        //Attribute
        this.componentAttribute=parser.getAttributeValue(null,"attribute");
        // Mandatory
        String mandatory = parser.getAttributeValue(null, "mandatory");
        this.mandatory = mandatory != null && Boolean.parseBoolean(mandatory);
        // Readonly
        String readonly = parser.getAttributeValue(null, "readonly");
        this.setEnabled(readonly != null && Boolean.parseBoolean(readonly));
        // Date and onChange
        String date = parser.getAttributeValue(null, "value");
        int[] dateParts;
        if (date != null) {
            String tmp[] = date.split("-");
            dateParts = new int[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                dateParts[i] = new Integer(tmp[i]).intValue();
            }
        } else {
            Calendar c = Calendar.getInstance();
            dateParts = new int[]{c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)};
        }
        final String onChange = parser.getAttributeValue(null, "onChange");
        this.init(dateParts[0], dateParts[1], dateParts[2], onChange != null ? new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                HashMap<Object, Object> map = new HashMap<Object, Object>();
                map.put("year", year);
                map.put("monthOfYear", monthOfYear);
                map.put("dayOfMonth", dayOfMonth);
                ScriptHelper.executeScript(onChange, map, window);
            }
        } : null);
        window.addView(this);
    }

    @Override
    public String validate(){ return null;  }

    @Override
    public void setComponentParent(Object o) {

    }

    public boolean isMandatory() {return mandatory; }
    public void setMandatory(boolean mandatory) {this.mandatory = mandatory;}

    public String getComponentId() {return componentId; }
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
        return componentValue;
    }

    @Override
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }
}
