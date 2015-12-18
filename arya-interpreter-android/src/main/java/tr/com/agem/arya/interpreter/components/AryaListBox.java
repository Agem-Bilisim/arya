package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.components.base.AryaNavBar;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaTemplate;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListBox extends TableLayout implements IAryaComponent, IAryaTemplate {

    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;
    private String database;
    private String attribute;
    private String attributeValue;
    private String attributeLabel;
    private Spinner listBoxSpinner = new Spinner(AryaNavBar.context);
    private List<String> spinnerItems = new ArrayList<>();

    private AryaTemplate template;

    private ArrayAdapter<String> adapter;
    private String onSelect;

    public AryaListBox(Attributes attributes, AryaMain main) {

        super(main.getAryaWindow().getContext());

        if (AryaUtils.isNotEmpty(attributes)) {
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");
            this.database = attributes.getValue("database");

            this.attribute = attributes.getValue("attribute");
            this.attributeValue = attributes.getValue("attributeValue");
            this.attributeLabel = attributes.getValue("attributeLabel");


            onSelect = attributes.getValue("onSelect");
        }


        //main.getAryaWindow().addView(this);
        this.setMinimumWidth(LayoutParams.MATCH_PARENT);
        HorizontalScrollView HorizontalScrollViewParent = new HorizontalScrollView(main.getAryaWindow().getContext());
        main.getAryaWindow().addView(HorizontalScrollViewParent);
        HorizontalScrollViewParent.addView(this);
        this.addView(listBoxSpinner);
        listBoxSpinner.setVisibility(INVISIBLE);
        adapter = new ArrayAdapter<>(AryaNavBar.context, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listBoxSpinner.setAdapter(adapter);


        listBoxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                listBoxSpinner.post(new Runnable() {
                    @Override
                    public void run() {
                        listBoxSpinner.setSelection(((AryaListItem) getChildAt(1)).getMasterCol(), false);
                    }
                });
                ((AryaListItem) getChildAt(1)).setMasterCol(position);   //change master column according to spinner\

                for (int i = 1; i < getChildCount(); i++) {
                    for (int j = 0; j < ((AryaListItem) getChildAt(i)).getChildCount(); j++) {

                        if (j == position) {
                            WindowManager wm = (WindowManager) AryaNavBar.context.getSystemService(Context.WINDOW_SERVICE);
                            Display display = wm.getDefaultDisplay();
                            Point size = new Point();
                            display.getSize(size);
                            int width = size.x;
                            if (!(((AryaListCell) ((AryaListItem) getChildAt(i)).getChildAt(j)).getText().toString().equals(""))) {
                                (((AryaListItem) getChildAt(i)).getChildAt(j)).setVisibility(VISIBLE);
                                (((AryaListItem) getChildAt(i)).getChildAt(j)).setLayoutParams(new TableRow.LayoutParams(width - 100, TableRow.LayoutParams.WRAP_CONTENT, 1f)); //MATCH_PARENT is not working here, thats why I used pixels
                            } else {
                                (((AryaListItem) getChildAt(i)).getChildAt(j)).setVisibility(INVISIBLE);
                                (((AryaListItem) getChildAt(i)).getChildAt(j)).setLayoutParams(new TableRow.LayoutParams(0, 0, 0f));
                            }
                        } else {
                            (((AryaListItem) getChildAt(i)).getChildAt(j)).setVisibility(INVISIBLE);
                            (((AryaListItem) getChildAt(i)).getChildAt(j)).setLayoutParams(new TableRow.LayoutParams(0, 0, 0f));
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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

    @Override
    public Object getAryaTemplate() {
        return template;
    }

    @Override
    public void setAryaTemplate(Object template) {
        this.template = (AryaTemplate) template;
    }

    public String getOnSelect() {
        return onSelect;
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
    public String getAttribute() {
        return attribute;
    }

    @Override
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public String getAttributeLabel() {
        return attributeLabel;
    }

    @Override
    public void setAttributeLabel(String attributeLabel) {
        this.attributeLabel = attributeLabel;
    }

    @Override
    public String getComponentTagName() {
        return "listbox";
    }

    public List<String> getSpinnerItems() {
        return spinnerItems;
    }

    public void setSpinnerItems(List<String> spinnerItems) {
        this.spinnerItems = spinnerItems;
    }

    public Spinner getListBoxSpinner() {
        return listBoxSpinner;
    }

    public void setListBoxSpinner(Spinner listBoxSpinner) {
        this.listBoxSpinner = listBoxSpinner;
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<String> adapter) {
        this.adapter = adapter;
    }
}



