package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.West;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaWest extends West implements IAryaComponent {

	private static final long serialVersionUID = -207082899880288071L;
	
	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaWest(final AryaMain main, Attributes attributes) {

		super();

		this.componentId = attributes.getValue("id");
		this.componentClassName = attributes.getValue("class");
		this.componentValue = attributes.getValue("value");
		this.componentAttribute = attributes.getValue("attribute");

		this.setId(attributes.getValue("id"));
		this.setTitle(attributes.getValue("title"));
		this.setClass(attributes.getValue("class"));
		this.setSize(attributes.getValue("size"));
		this.setMaxsize(Integer.parseInt(attributes.getValue("maxsize")));
		
		this.setId(attributes.getValue("id"));
		this.setTitle(attributes.getValue("title"));
		this.setClass(attributes.getValue("class"));
		this.setSize(attributes.getValue("size"));
		this.setMaxsize(Integer.parseInt(attributes.getValue("maxsize")));
		
		if(attributes.getValue("collapsible").equals("true"))
			this.setCollapsible(true);
		else
			this.setCollapsible(false);
		
		if(attributes.getValue("splittable").equals("true"))
			this.setSplittable(true);
		else
			this.setSplittable(false);

		if (AryaUtils.isNotEmpty(attributes.getValue("onClick"))) {
			final String functionName = attributes.getValue("onClick");
			this.addEventListener("onClick", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					ScriptHelper.executeScript(functionName, null, main);
				}
			});
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
	}

}