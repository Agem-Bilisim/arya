package tr.com.agem.arya.interpreter.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.TableRow;
import android.widget.TextView;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.main.components.AryaWindow;
import tr.com.agem.arya.interpreter.parser.AryaParserAttributes;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListCell extends TextView implements IAryaComponent {

    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;


    public AryaListCell(Attributes attributes,AryaWindow window, String tag) {
        super(window.getContext());

        if(AryaUtils.isNotEmpty(attributes)){

            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            this.setText(attributes.getValue("label"));
        }

        if("listheader".equalsIgnoreCase(tag)){ //some header property
            this.setTextColor(Color.BLACK);
            this.setBackgroundColor(Color.GRAY);
        }
    }




    /*public AryaListCell(AryaWindow window,String id,String label) {


        super(window.getContext());
        this.componentId = id;
        this.setText(label);

    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);

        getLocalVisibleRect(rect);
        canvas.drawRect(rect, paint);
    }
    @Override
    public void setComponentParent(Object o) {
        AryaListItem li = (AryaListItem) o;

        this.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 10.0f));
        li.addView(this);
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
}
