package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Spinner;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaSpinner extends Spinner implements IAryaComponent{

	private static final long serialVersionUID = -4019284059182059962L;
	/*
	private static final long serialVersionUID = UUID.randomUUID(); 
	Wouldn't it be more useful ?
	*/
	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaSpinner(final AryaMain main, Attributes attributes) {
		super();

		this.componentId = attributes.getValue("id");
		this.componentClassName = attributes.getValue("class");
		this.componentValue = attributes.getValue("value");
		this.componentAttribute = attributes.getValue("attribute");
		
		this.setId(attributes.getValue("id"));
		this.setClass(attributes.getValue("class"));
		this.setHeight(attributes.getValue("height"));

	}

	@Override
	public void setComponentParent(Object parent) {
		this.setParent((Component) parent);
	}

	@Override
	public String validate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getComponentClassName() {
		return componentClassName;
	}

	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentAttribute() {
		return componentAttribute;
	}

	public void setComponentAttribute(String componentAttribute) {
		this.componentAttribute = componentAttribute;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

}