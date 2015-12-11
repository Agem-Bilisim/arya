package tr.com.agem.arya.interpreter.components;

/**
 * Created by bcelenk on 10/13/15.
 */


import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;


public class AryaVlayout extends LinearLayout implements IAryaComponent {


    private String componentClassName;
    private String componentId;
    private String componentAttribute;
    private String componentValue;

    public AryaVlayout(Attributes attributes, final AryaMain main) {
        super(main.getAryaWindow().getContext());

        if(AryaUtils.isNotEmpty(attributes)){
            this.setOrientation(LinearLayout.VERTICAL);
            this.componentId = attributes.getValue("id");
            this.componentClassName = attributes.getValue("class");
            this.componentValue = attributes.getValue("value");
            this.componentAttribute = attributes.getValue("attribute");
            this.setVerticalScrollBarEnabled(true);
            this.setHorizontalScrollBarEnabled(true);


        }
        ScrollView ScrollViewParent = new ScrollView(main.getAryaWindow().getContext());
        main.getAryaWindow().addView(ScrollViewParent);
        ScrollViewParent.addView(this);
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
    public String getComponentTagName() {
        return "vlayout";
    }


}
