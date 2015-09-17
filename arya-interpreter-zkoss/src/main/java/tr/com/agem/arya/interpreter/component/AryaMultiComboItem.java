package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import tr.com.agem.arya.interpreter.base.components.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaMultiComboItem extends Listitem implements IAryaComponent {

	private static final long serialVersionUID = -5868397529900372434L;
	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaMultiComboItem(AryaWindow aryaWindow, Attributes attributes) {
		super();

		if (AryaUtils.isNotEmpty(attributes)){
			this.componentId = attributes.getValue("id");
			this.componentClassName = attributes.getValue("class");
			this.componentValue = attributes.getValue("value");
			this.componentAttribute = attributes.getValue("attribute");

			this.setId(attributes.getValue("id"));
			this.setClass(attributes.getValue("class"));
			this.setLabel(attributes.getValue("label"));
			this.setValue(attributes.getValue("value"));
		}
		
	}

	@Override
	public void setComponentParent(Object parent) {
		this.setParent((Component) parent);
	}

	@Override
	public String validate() {
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
