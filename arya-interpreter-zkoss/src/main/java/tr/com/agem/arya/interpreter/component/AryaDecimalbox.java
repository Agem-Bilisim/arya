package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Decimalbox;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaDecimalbox extends Decimalbox implements IAryaComponent {

	private static final long serialVersionUID = 59210392819274201L;
	
	private String componentClassName;
	private String componentAttribute;

	public AryaDecimalbox(final AryaMain main, Attributes attributes) {

		super();

		this.setId(attributes.getValue("id"));
		this.componentClassName = attributes.getValue("class");
		this.componentAttribute = attributes.getValue("attribute");
			
		this.setClass(attributes.getValue("class"));
		if(attributes.getValue("visible") != null)
		this.setVisible(Boolean.parseBoolean(attributes.getValue("visible")));
		if(attributes.getValue("disabled") != null)
		this.setDisabled(Boolean.parseBoolean(attributes.getValue("disabled")));
		if(attributes.getValue("readonly") != null)
		this.setReadonly(Boolean.parseBoolean(attributes.getValue("readonly")));
		if(attributes.getValue("maxlength") != null)
		this.setMaxlength(Integer.parseInt(attributes.getValue("maxlength")));
		if(attributes.getValue("tabindex") != null)
		this.setTabindex(Integer.parseInt(attributes.getValue("tabindex")));
		this.setTooltip(attributes.getValue("tooltip"));
		this.setTooltiptext(attributes.getValue("tooltiptext"));
		this.setDraggable(attributes.getValue("draggable"));
		this.setDroppable(attributes.getValue("droppable"));
		this.setFocus(Boolean.parseBoolean(attributes.getValue("focus")));
		this.setStyle(attributes.getValue("style"));
		this.setZclass(attributes.getValue("zlass"));
		this.setSclass(attributes.getValue("sclass"));
		this.setLeft(attributes.getValue("left"));
		this.setTop(attributes.getValue("top"));
		if(attributes.getValue("zindex") != null)
		this.setZIndex(Integer.parseInt(attributes.getValue("zindex")));
		if(attributes.getValue("renderdefer") != null)
		this.setRenderdefer(Integer.parseInt(attributes.getValue("renderdefer")));
		this.setAction(attributes.getValue("action"));
		this.setHflex(attributes.getValue("hflex"));
		this.setVflex(attributes.getValue("vflex"));

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
		
	
		if (AryaUtils.isNotEmpty(attributes.getValue("onChange"))) {
			final String functionName = attributes.getValue("onChange");
			this.addEventListener("onChange", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					ScriptHelper.executeScript(functionName, null, main);
				}
			});
		}
		if (AryaUtils.isNotEmpty(attributes.getValue("onChanging"))) {
			final String functionName = attributes.getValue("onChanging");
			this.addEventListener("onChanging", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					ScriptHelper.executeScript(functionName, null, main);
				}
			});
		}
		if (AryaUtils.isNotEmpty(attributes.getValue("onFocus"))) {
			final String functionName = attributes.getValue("onFocus");
			this.addEventListener("onFocus", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					ScriptHelper.executeScript(functionName, null, main);
				}
			});
		}
		if (AryaUtils.isNotEmpty(attributes.getValue("onBlur"))) {
			final String functionName = attributes.getValue("onBlur");
			this.addEventListener("onBlur", new EventListener<Event>() {
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