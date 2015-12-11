package tr.com.agem.arya.interpreter.components;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import org.xml.sax.Attributes;


import java.util.ArrayList;
import java.util.List;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.components.base.AryaNavBar;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListCell extends TextView implements IAryaComponent {

    private String componentId;
    private String componentValue;
    private String componentClassName;
    private String componentAttribute;
    private String tag;

    public AryaListCell(Attributes attributes,AryaMain main, String tag) {
        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.tag = tag;
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");

            this.setText(attributes.getValue("label"));
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailView(new AlertDialog.Builder(AryaNavBar.context));


                }
            });

           // this.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            //this.setTextSize(15, TypedValue.COMPLEX_UNIT_DIP);
        }

        if("listheader".equalsIgnoreCase(tag)){
            this.setTextColor(Color.BLACK);
            this.setBackgroundColor(Color.LTGRAY);
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
        li.addView(this);
        AryaListItem parent = (AryaListItem)this.getParent();
// Arguments here: width, height, weight

        if(li.getChildCount()-1==((AryaListItem)(((AryaListBox)li.getParent()).getChildAt(0))).getMasterCol()){ //If current cell is element of master column
            WindowManager wm = (WindowManager) AryaNavBar.context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size= new Point();
            display.getSize(size);
            int width = size.x;
            this.setLayoutParams(new TableRow.LayoutParams(width-100, TableRow.LayoutParams.WRAP_CONTENT, 1f)); //MATCH_PARENT is not working here, thats why I used pixels
        }
        else{
            this.setLayoutParams(new TableRow.LayoutParams(0, 0, 0f));
        }
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
    public Object getComponentParent() {
        return this.getComponentParent();
    }

    @Override
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }


    @Override
    public String getComponentTagName() {
        return "listcell";
    }
    private void detailView(AlertDialog.Builder alertDialog){
        AryaListItem parent = (AryaListItem)this.getParent();
        alertDialog.setTitle("Bilgiler");
        //alertDialog.setMessage("");
        LinearLayout layout = new LinearLayout(AryaNavBar.context);
        layout.setVerticalScrollBarEnabled(true);
        layout.setHorizontalScrollBarEnabled(true);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText input = new EditText(AryaNavBar.context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i=0;i<parent.getChildCount();i++){
            LinearLayout ele_layout = new LinearLayout(AryaNavBar.context);
            LinearLayout.LayoutParams ele_lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            ele_layout.setOrientation(LinearLayout.HORIZONTAL);
            final TextView columnName= new TextView(AryaNavBar.context);
            columnName.setText(((TextView) ((AryaListItem) ((AryaListBox) parent.getParent()).getChildAt(0)).getChildAt(i)).getText() + ":\t");
            final TextView info= new TextView(AryaNavBar.context);
            info.setText(((TextView)parent.getChildAt(i)).getText());
            columnName.setLayoutParams(lp);
            info.setLayoutParams(lp);
            ele_layout.addView(columnName,ele_lp);
            ele_layout.addView(info,ele_lp);
            layout.addView(ele_layout,lp);
        }
        alertDialog.setView(layout);


        alertDialog.setPositiveButton("Geri",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.setIcon(android.R.drawable.arrow_down_float);
        alertDialog.setIconAttribute(android.R.attr.alertDialogIcon);
        alertDialog.show();

    }
}
