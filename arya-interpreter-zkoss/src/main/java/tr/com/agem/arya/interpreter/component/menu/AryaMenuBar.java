package tr.com.agem.arya.interpreter.component.menu;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Menubar;

import tr.com.agem.arya.interpreter.base.components.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaMenuBar extends Menubar implements IAryaComponent,IAryaMenu {

	private static final long serialVersionUID = 8605492923881165335L;
	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;
	
	public AryaMenuBar(AryaMain main, Attributes attributes) {
		super();
		
		if(AryaUtils.isNotEmpty(attributes)){
			this.componentId = attributes.getValue("id");
			this.componentClassName = attributes.getValue("class");
			this.componentValue = attributes.getValue("value");
			this.componentAttribute = attributes.getValue("attribute");

			this.setId(attributes.getValue("id"));
			this.setClass(attributes.getValue("class"));
			this.setHeight(attributes.getValue("height"));
		}
	}

	@Override
	public void setComponentParent(Object parent) {
		this.setParent((Component) parent);
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

	@Override
	public String validate() {
		// TODO Auto-generated method stub
		return null;
	}




}
