package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Combobox;

import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.utils.AryaUtils;

public class AryaCombobox extends Combobox implements IAryaComponentProperty {

	private static final long serialVersionUID = -1829374522609555406L;

	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaCombobox(final AryaWindow aryaWindow, Attributes attributes) {
		super();
		this.setParent(aryaWindow.getComponentContainer());

		this.componentId = attributes.getValue("id");
		this.componentClassName = attributes.getValue("class");
		this.componentValue = attributes.getValue("value");
		this.componentAttribute = attributes.getValue("attribute");

		this.setId(attributes.getValue("id"));
		this.setClass(attributes.getValue("class"));
		this.setHeight(attributes.getValue("height"));

		for (int i = 0; i < attributes.getLength(); i++) {
			// System.out.println(attributes.);
		}

		this.appendItem("asd");
		this.appendItem("asd");
		this.appendItem("asd");

		if (AryaUtils.isNotEmpty(attributes.getValue("onClick"))) {
			final String functionName = attributes.getValue("onClick");
			this.addEventListener("onClick", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					ScriptHelper.executeScript(functionName, null, aryaWindow);
				}
			});
		}
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