package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Comboitem;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaComboItem extends Comboitem implements IAryaComponent {

	private static final long serialVersionUID = -7348740222197906039L;

	private String componentClassName;
	private String componentAttribute;

	public AryaComboItem(AryaMain main, Attributes attributes) {
		super();

		if (AryaUtils.isNotEmpty(attributes)){
			this.setId(attributes.getValue("id"));
			this.componentClassName = attributes.getValue("class");
			this.setValue(attributes.getValue("value"));
			this.componentAttribute = attributes.getValue("attribute");
			
			this.setClass(attributes.getValue("class"));
			this.setLabel(attributes.getValue("label"));
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
		return this.getId();
	}

	public void setComponentId(String componentId) {
		this.setId(componentId);
	}

	public String getComponentAttribute() {
		return componentAttribute;
	}

	public void setComponentAttribute(String componentAttribute) {
		this.componentAttribute = componentAttribute;
	}

	public String getComponentValue() {
		return this.getValue();
	}

	public void setComponentValue(String componentValue) {
		this.setValue(componentValue);
	}

}
