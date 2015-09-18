package tr.com.agem.arya.interpreter.components;

import android.widget.DatePicker;

import org.xml.sax.Attributes;

import java.util.Calendar;
import java.util.HashMap;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaDatebox  extends DatePicker implements IAryaComponent {

    private String componentId;
    private String componentClassName;
    private boolean mandatory;
    private String componentAttribute;
    private String componentValue;

    public AryaDatebox(Attributes attributes, final AryaMain main) {
        super(main.getAryaWindow().getContext());

        String mandatory=null;
        String readonly=null;
        String date=null;

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            mandatory = attributes.getValue("mandatory");
            readonly =attributes.getValue("readonly");

            date =attributes.getValue("value");
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
            final String onChange =attributes.getValue("onChange");
            this.init(dateParts[0], dateParts[1], dateParts[2], onChange != null ? new OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    HashMap<Object, Object> map = new HashMap<Object, Object>();
                    map.put("year", year);
                    map.put("monthOfYear", monthOfYear);
                    map.put("dayOfMonth", dayOfMonth);
                    ScriptHelper.executeScript(onChange, map, main);
                }
            } : null);
        }

        this.mandatory = mandatory != null && Boolean.parseBoolean(mandatory);
        this.setEnabled(readonly != null && Boolean.parseBoolean(readonly));

        main.getAryaWindow().addView(this);
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
