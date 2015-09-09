package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import tr.com.agem.core.interpreter.IAryaComponent;


public class AryaMultipleComboBox extends ListView implements IAryaComponent {

    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;


    public AryaMultipleComboBox(Context context, Attributes attributes, AryaWindow window) {

        super(context);

        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

        createAdapter();

        window.addView(this);
    }

    private void createAdapter() {
        ArrayAdapter<AryaMultipleComboItem> listAdapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_list_item_checked , new ArrayList<AryaMultipleComboItem>());
        listAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        this.setAdapter(listAdapter);
        this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.setChoiceMode(this.CHOICE_MODE_MULTIPLE);

        setListViewHeightBasedOnChildren(this);
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

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount()-1; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;// + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

        Log.d("----------",params.height+"**"+(listView.getDividerHeight() * (listAdapter.getCount() - 1))+"**"+(listView.getDividerHeight() * (listAdapter.getCount() - 2)));
    }
}
