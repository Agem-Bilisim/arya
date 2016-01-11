package tr.com.agem.component;

import org.xml.sax.Attributes;

import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaAttribute implements IAryaComponent {
	
	private String componentClassName;
	private IAryaComponent componentParent;
	private String name;
	private String value;
	private String comp;
	
	public AryaAttribute(Attributes attributes) {
		super();
		
		if (AryaUtils.isNotEmpty(attributes)) {
			if (AryaUtils.isNotEmpty(attributes)) {
				this.componentClassName = attributes.getValue("class");
				
				this.name = attributes.getValue("name");
				this.value = attributes.getValue("value");
				this.comp = attributes.getValue("comp");
			}
		}
	}

	@Override
	public void setComponentId(String componentId) {
	}

	@Override
	public String getComponentId() {
		return null;
	}

	@Override
	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	@Override
	public String getComponentClassName() {
		return componentClassName;
	}

	@Override
	public void setComponentValue(String componentValue) {
	}

	@Override
	public String getComponentValue() {
		return null;
	}

	@Override
	public String validate() {
		return null;
	}

	@Override
	public void setComponentParent(Object parent) {
		this.componentParent = (IAryaComponent) parent;
	}

	@Override
	public Object getComponentParent() {
		return componentParent;
	}

	@Override
	public String getDatabase() {
		return null;
	}

	@Override
	public void setDatabase(String database) {
	}

	@Override
	public String getComponentTagName() {
		return "attribute";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	@Override
	public String getAttribute() {
		return null;
	}

	@Override
	public void setAttribute(String attribute) {
	}

	@Override
	public String getAttributeValue() {
		return null;
	}

	@Override
	public void setAttributeValue(String attributeValue) {
	}

	@Override
	public String getAttributeLabel() {
		return null;
	}

	@Override
	public void setAttributeLabel(String attributeLabel) {
	}

}
