package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listheader;

import tr.com.agem.arya.interpreter.base.components.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListHeader extends Listheader implements IAryaComponent {

	private static final long serialVersionUID = -9059892322277407469L;

	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaListHeader(final AryaMain main, Attributes attributes) {
		super();

		if (AryaUtils.isNotEmpty(attributes)){
			this.componentId = attributes.getValue("id");
			this.componentClassName = attributes.getValue("class");
			this.componentValue = attributes.getValue("value");
			this.componentAttribute = attributes.getValue("attribute");

			this.setId(attributes.getValue("id"));
			this.setClass(attributes.getValue("class"));
			this.setHeight(attributes.getValue("height"));
			this.setLabel(attributes.getValue("label"));

			final String functionName = attributes.getValue("onClick");
			if (AryaUtils.isNotEmpty(functionName)) {
				this.addEventListener("onClick", new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						ScriptHelper.executeScript(functionName, null, main);
					}
				});
			}
		}
		
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
		this.setLabel(componentValue);
	}

}
