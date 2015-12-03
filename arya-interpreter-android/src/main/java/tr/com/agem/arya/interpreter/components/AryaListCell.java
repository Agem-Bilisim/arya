package tr.com.agem.arya.interpreter.components;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListCell extends TextView implements IAryaComponent {

    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;
    private boolean stretched =false;

    public AryaListCell(Attributes attributes,AryaMain main, String tag) {
        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){

            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            this.setText(attributes.getValue("label"));
           // this.setMinimumWidth(ActionBar.LayoutParams.WRAP_CONTENT);
           // this.setMinimumHeight(ActionBar.LayoutParams.WRAP_CONTENT);
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!stretched){
                        setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, 1f));
                        stretched=true;
                    }
                    else{
                        setLayoutParams(new TableRow.LayoutParams(120, 120, 1f));
                        stretched=false;
                    }

                }
            });
            this.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            //this.setTextSize(15, TypedValue.COMPLEX_UNIT_DIP);
        }

        if("listheader".equalsIgnoreCase(tag)){
            this.setTextColor(Color.BLACK);
            this.setBackgroundColor(Color.GRAY);
        }
    }


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
// Arguments here: width, height, weight
        this.setLayoutParams(new TableRow.LayoutParams(120, 120, 1f));
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

    @Override
    public String getComponentTagName() {
        return "listcell";
    }

}
