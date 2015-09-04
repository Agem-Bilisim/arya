package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaComboBox extends Spinner implements IAryaComponent {

    private static final String TAG = "AryaComboBox";

    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;
    private boolean spinnerInit =false;

    public AryaComboBox(Context context,XmlPullParser parser, final AryaWindow window) {
        super(context);

        this.componentId= parser.getAttributeValue(null, "id");
        this.componentClassName= parser.getAttributeValue(null, "class");
        this.componentAttribute= parser.getAttributeValue(null, "attribute");
        this.componentValue= parser.getAttributeValue(null, "value");

        final String onChange = parser.getAttributeValue(null, "onChange");

        this.setAdapter(getAdapter(context, parser, window));

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

        window.addView(this);
    }

    private boolean isInit() {
        if(spinnerInit)
            return true;
        else{
            spinnerInit=true;
            return false;
        }
    }


    private AryaComboBox getMyself(){
        return  this;
    }

    private SpinnerAdapter getAdapter(Context context,XmlPullParser parser, LinearLayout window){

        List<AryaItem> list = new ArrayList<AryaItem>();

        try {
            while(parser.nextTag() != XmlPullParser.END_DOCUMENT)
            {
                if(parser.getEventType() == XmlPullParser.START_TAG){
                    String tagName = parser.getName();

                    if(tagName.equals("comboitem")) {
                        Log.d("",parser.getAttributeValue(null, "id")+"");
                        AryaItem item =new AryaItem(context,parser.getAttributeValue(null, "id"), parser.getAttributeValue(null, "value"), parser.getAttributeValue(null, "label"));
                        window.addView(item);

                        list.add(item);
                    }
                }
            }
        } catch (XmlPullParserException e) {
            Log.e(TAG, "XmlPullParserException: unexpected type", e);
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<AryaItem> dataAdapter = new ArrayAdapter<AryaItem>(context, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapter;
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
