package tr.com.agem.arya.interpreter.components;

import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;


public class AryaHlayout extends LinearLayout implements IAryaComponent {


    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;

    public AryaHlayout(Attributes attributes, final AryaMain main) {
        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.setOrientation(LinearLayout.HORIZONTAL);
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");
            this.setVerticalScrollBarEnabled(true);
            this.setHorizontalScrollBarEnabled(true);
            this.setMinimumWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        }

        HorizontalScrollView HorizontalScrollViewParent = new HorizontalScrollView(main.getAryaWindow().getContext());
        main.getAryaWindow().addView(HorizontalScrollViewParent);
        HorizontalScrollViewParent.addView(this);
    }

    @Override
    public String validate(){ return null; }

    @Override
    public void setComponentParent(Object o) {
        ((ViewGroup)o).addView(this);
    }

    public String getComponentId() { return componentId; }
    public void setComponentId(String componentId) { this.componentId = componentId;}

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
    public Object getComponentParent() {
        return this.getComponentParent();
    }

    @Override
    public String getDatabase() {
        return null;
    }

    @Override
    public void setDatabase(String s) {

    }

    @Override
    public String getComponentTagName() {
        return "hlayout";
    }


}
