package tr.com.agem.arya.interpreter.components;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.xml.sax.Attributes;

import java.util.ArrayList;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaComboBox extends Spinner implements IAryaComponent {

    private static final String TAG = "AryaComboBox";

    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;
    private boolean spinnerInit =false;

    public AryaComboBox(Attributes attributes , final AryaMain main) {
        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            final String tooltiptext = attributes.getValue("tooltiptext");
            this.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if(tooltiptext!=null) {
                        Toast.makeText(v.getContext(), tooltiptext, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            this.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(), tooltiptext, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            if (attributes.getValue("visible") != null) {
                if(attributes.getValue("visible").equals("true")){
                    this.setVisibility(VISIBLE);
                }
                else{
                    this.setVisibility(INVISIBLE);
                }
            }
            if (attributes.getValue("disabled") != null) {
                this.setEnabled(!Boolean.parseBoolean(attributes.getValue("disabled")));
            }

            final String onChange =attributes.getValue("onChange");

            this.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(isInit()){
                        ScriptHelper.executeScript(onChange, null, main);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        }

        createAdapter();

        main.getAryaWindow().addView(this);
    }

    private void createAdapter() {
        ArrayAdapter<AryaComboItem> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, new ArrayList<AryaComboItem>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);
    }

    private boolean isInit() {
        if(spinnerInit)
            return true;
        else{
            spinnerInit=true;
            return false;
        }
    }


    @Override
    public String getComponentClassName() {
        return componentClassName;
    }

    @Override
    public void setComponentClassName(String componentClassName) {
        this.componentClassName = componentClassName;
    }

    @Override
    public String getComponentId() {
        return componentId;
    }

    @Override
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @Override
    public String getComponentAttribute() {
        return componentAttribute;
    }

    @Override
    public String validate() {
        return null;
    }

    @Override
    public void setComponentParent(Object o) {
        ((ViewGroup)o).addView(this);
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

    @Override
    public String getComponentTagName() {
        return "combobox";
    }

}
