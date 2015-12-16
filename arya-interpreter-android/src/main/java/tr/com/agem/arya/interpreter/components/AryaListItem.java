package tr.com.agem.arya.interpreter.components;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
    private String database;

    private int masterCol=0;
    private AryaMain main;

    public AryaListItem(Attributes attributes, final AryaMain main) {

        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");
            this.database = attributes.getValue("database");

            if(attributes.getValue("masterCol")!=null){
                this.masterCol=Integer.parseInt(attributes.getValue("masterCol"));
            }
        }
        this.main = main;
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {Color.parseColor("#C0C0C0"), Color.parseColor("#505050")});
        gd.setGradientCenter(0.f, 1.f);
        gd.setLevel(2);
        this.setBackground(gd);
    }


    @Override
    public void setComponentParent(Object o) {
        AryaListBox lb = (AryaListBox) o;

        this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 10.0f));
        this.setPadding(1, 1, 1, 1);

        lb.addView(this);

        //onClick is defined to listbox in arya files,
        // so before clicklistener, listitem must know its parent
        final String onSelect = lb.getOnSelect();

        this.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                v.setDrawingCacheBackgroundColor(Color.LTGRAY);

                if((((AryaListItem)v).getComponentValue()) != null) {
                    JSONObject j = new JSONObject(((AryaListItem) v).getComponentValue());
                    ElementFunctions.setJsonObj(j);

                    ScriptHelper.executeScript(onSelect, null, getMain());
                }
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
    public String getDatabase() {
        return database;
    }

    @Override
    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String getComponentTagName() {
        return "listitem";
    }
    public int getMasterCol() {
        return masterCol;
    }

    public void setMasterCol(int masterCol) {
        this.masterCol = masterCol;
    }

}
