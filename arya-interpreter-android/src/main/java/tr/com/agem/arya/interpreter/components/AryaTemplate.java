package tr.com.agem.arya.interpreter.components;


import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaTemplate implements IAryaComponent{

    private String componentClassName;
    private String componentAttribute;

    private IAryaComponent parent;
    private List<IAryaComponent> children = new ArrayList<IAryaComponent>();

    private String name;
    private String var;


    public AryaTemplate(Attributes attributes, final AryaMain main ) {
        super();

        if (AryaUtils.isNotEmpty(attributes)) {
            if (AryaUtils.isNotEmpty(attributes)) {
                this.componentClassName = attributes.getValue("class");
                this.componentAttribute = attributes.getValue("attribute");

                this.name = attributes.getValue("name");
                this.var = attributes.getValue("var");

            }
        }
    }

    @Override
    public String validate() {
        // TODO Auto-generated method stub
        return null;
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
    public void setComponentAttribute(String componentAttribute) {
        this.componentAttribute = componentAttribute;
    }

    @Override
    public String getComponentValue() {
		/*
		 * There is no componentValue variable for this component. This function
		 * was created for IAryaComponent interface.
		 */
        return null;
    }

    @Override
    public void setComponentValue(String componentValue) {
		/*
		 * There is no componentValue variable for this component. This function
		 * was created for IAryaComponent interface.
		 */
    }

    @Override
    public void setComponentId(String componentId) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getComponentId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setComponentParent(Object parent) {
        this.setParent((IAryaComponent)parent);
    }


    public IAryaComponent getParent() {
        return parent;
    }

    public void setParent(IAryaComponent parent) {
        this.parent = parent;
    }

    public List<IAryaComponent> getChildren() {
        return children;
    }

    public void setChildren(List<IAryaComponent> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

   @Override
    public String getComponentTagName() {
        return "template";
    }

}
