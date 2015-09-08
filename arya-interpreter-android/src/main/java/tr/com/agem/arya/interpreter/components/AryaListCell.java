package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.TextView;

import org.xml.sax.Attributes;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaListCell extends TextView implements IAryaComponent {

    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;


    public AryaListCell(Context context, Attributes attributes, AryaWindow window) {


        super(context);
        this.componentId = attributes.getValue("id");
        this.componentClassName = attributes.getValue("class");
        this.componentValue = attributes.getValue("value");
        this.componentAttribute = attributes.getValue("attribute");

        this.setText(attributes.getValue("label"));

    }

    @Override
    protected void onDraw(Canvas canvas) {//TODO size dynamic yapılmalı renk,font,.. vs attr ile et edilmeli
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
