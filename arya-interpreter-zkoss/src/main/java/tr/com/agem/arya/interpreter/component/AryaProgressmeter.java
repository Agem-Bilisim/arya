package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Progressmeter;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaProgressmeter extends Progressmeter implements IAryaComponent {

	private static final long serialVersionUID = 532549011859946618L;
	
	private String componentClassName;
	private String componentAttribute;

	public AryaProgressmeter(final AryaMain main, Attributes attributes) {

		super();

		if(AryaUtils.isNotEmpty(attributes)){
			this.setId(attributes.getValue("id"));
			this.componentClassName = attributes.getValue("class");
			if(attributes.getValue("value") == null)
				this.setValue(0);
			else
				this.setValue(Integer.parseInt(attributes.getValue("value")));
			
			this.setClass(attributes.getValue("class"));
			
			/*if the dimension input format of .arya files does NOT contains the unit like -height="200px" */
			if(attributes.getValue("height") != null && attributes.getValue("height").contains("px"))
				this.setHeight(attributes.getValue("height"));
			else
				this.setHeight(attributes.getValue("height")+"px");
			
			if(attributes.getValue("width")!=null && attributes.getValue("width").contains("px"))
				this.setWidth(attributes.getValue("width"));
			else
				this.setWidth(attributes.getValue("width")+"px");

			
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
		return String.valueOf(this.getValue());
	}

	public void setComponentValue(String componentValue) {
		this.setValue(Integer.parseInt(componentValue));
	}

}