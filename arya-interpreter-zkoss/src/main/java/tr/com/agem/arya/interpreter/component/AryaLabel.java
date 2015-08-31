package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Label;

import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.utils.AryaUtils;

public class AryaLabel extends Label implements IAryaComponentProperty {

	private static final long serialVersionUID = 1507265134096459571L;
	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaLabel(Object parent, final AryaWindow aryaWindow, Attributes attributes) {
		super();
		final String functionName;

		this.setParent((Component) parent);
		this.componentId = attributes.getValue("id");
		this.componentClassName = attributes.getValue("class");
		this.componentValue = attributes.getValue("value");
		this.componentAttribute = attributes.getValue("attribute");
		
		this.setId(attributes.getValue("id"));
		this.setClass(attributes.getValue("class"));
		this.setValue(attributes.getValue("value"));
		
		this.setWidth(attributes.getValue("width"));
		this.setHeight(attributes.getValue("height"));
		
		if (AryaUtils.isNotEmpty(attributes.getValue("onClick"))) {
			functionName = attributes.getValue("onClick");
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