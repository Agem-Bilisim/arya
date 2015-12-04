package tr.com.agem.arya.interpreter.components;

import android.graphics.Color;
import android.view.View;
import android.widget.TableRow;

import org.json.JSONObject;
import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ElementFunctions;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListItem extends TableRow implements IAryaComponent {
    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;

    private AryaMain main;

    public AryaListItem(Attributes attributes, final AryaMain main) {

        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");
        }
        this.main = main;

    }


    @Override
    public void setComponentParent(Object o) {
        AryaListBox lb = (AryaListBox) o;

        this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 10.0f));
        this.setPadding(1, 1, 1, 1);

        lb.addView(this);

        //onClick is defined to listbox in arya files,
        // so before clicklistener, listitem must know its parent
        final String onSelect = lb.getOnSelect();

        this.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                v.setDrawingCacheBackgroundColor(Color.LTGRAY);

                JSONObject j = new JSONObject(((AryaListItem)v).getComponentValue());
                ElementFunctions.setJsonObj(j);

                ScriptHelper.executeScript(onSelect, null, getMain());
            }
        });

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
    public String getComponentValue() {
        return componentValue;
    }

    @Override
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
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
    public String getComponentAttribute() {
        return componentAttribute;
    }

    @Override
    public String validate() {
        return null;
    }


    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }

    public AryaMain getMain() {
        return main;
    }

    public void setMain(AryaMain main) {
        this.main = main;
    }

    @Override
    public Object getComponentParent() {
        return this.getComponentParent();
    }

    @Override
    public String getComponentTagName() {
        return "listitem";
    }

}
