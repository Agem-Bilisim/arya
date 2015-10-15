package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Selectbox;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaSelectbox extends Selectbox implements IAryaComponent {


	private static final long serialVersionUID = 2051047315627309416L;
	private String componentClassName;
	private String componentAttribute;

	public AryaSelectbox(final AryaMain main, Attributes attributes) {

		super();

		if(AryaUtils.isNotEmpty(attributes)){
			this.setId(attributes.getValue("id"));
			this.componentClassName = attributes.getValue("class");
			this.componentAttribute = attributes.getValue("attribute");
	
			this.setClass(attributes.getValue("class"));
			this.setVisible(Boolean.parseBoolean(attributes.getValue("visible")));
			this.setDisabled(Boolean.parseBoolean(attributes.getValue("disabled")));
			this.setTooltiptext(attributes.getValue("tooltiptext"));
			this.setDraggable(attributes.getValue("draggable"));
			this.setDroppable(attributes.getValue("droppable"));
			this.setFocus(Boolean.parseBoolean(attributes.getValue("focus")));
			
			/*if the dimension input format of .arya files does NOT contains the unit like -height="200px" */
			
			if(attributes.getValue("height") != null && attributes.getValue("height").contains("px"))
				this.setHeight(attributes.getValue("height"));
			else
				this.setHeight(attributes.getValue("height")+"px");
			
			if(attributes.getValue("width")!=null && attributes.getValue("width").contains("px"))
				this.setWidth(attributes.getValue("width"));
			else
				this.setWidth(attributes.getValue("width")+"px");

			
		
			
			if (AryaUtils.isNotEmpty(attributes.getValue("onDrop"))) {
				final String functionName = attributes.getValue("onDrop");
				this.addEventListener("onDrop", new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						ScriptHelper.executeScript(functionName, null, main);
					}
				});
			}
			
			if (AryaUtils.isNotEmpty(attributes.getValue("onCreate"))) {
				final String functionName = attributes.getValue("onCreate");
				this.addEventListener("onCreate", new EventListener<Event>() {
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
		/*
		 * There is no componentValue variable for this component.
		 * This function was created for IAryaComponent interface.
		 */
		return null;
	}

	public void setComponentValue(String componentValue) {
		/*
		 * There is no componentValue variable for this component.
		 * This function was created for IAryaComponent interface.
		 */
	}

}