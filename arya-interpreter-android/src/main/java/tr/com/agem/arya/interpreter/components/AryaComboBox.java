package tr.com.agem.arya.interpreter.components;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.xml.sax.Attributes;

import java.util.ArrayList;

import tr.com.agem.arya.interpreter.main.components.AryaWindow;
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

    public AryaComboBox(Attributes attributes , final AryaWindow window) {
        super(window.getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            final String onChange =attributes.getValue("onChange");

            this.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(isInit()){
                        ScriptHelper.executeScript(onChange, null, window);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        }

        createAdapter();

        window.addView(this);
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
