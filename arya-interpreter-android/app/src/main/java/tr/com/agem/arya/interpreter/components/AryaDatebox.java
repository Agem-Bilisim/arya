package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;
import java.util.HashMap;

import tr.com.agem.arya.script.ScriptHelper;

public class AryaDatebox  extends DatePicker implements IAryaComponent {

    private String componentId;
    private boolean mandatory;

    public AryaDatebox(Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        // Component ID
        this.componentId = parser.getAttributeValue(null, "id");
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
                ScriptHelper.executeScript((IAryaComponent) view, onChange, map);
            }
        } : null);
        window.addView(this);
    }

    @Override
    public String validate(){
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
}
