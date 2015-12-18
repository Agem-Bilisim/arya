package tr.com.agem.arya.interpreter.component;

import java.util.List;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Chart;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.arya.metadata.arya.impl.ChartsType;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaChart extends Chart implements IAryaComponent {

	private static final long serialVersionUID = -8101533101584382438L;

	private String componentClassName;
	private String componentAttribute;
	private String database;

	public AryaChart(final AryaMain main, Attributes attributes) {

		super();

		if (AryaUtils.isNotEmpty(attributes)) {
			this.setType(attributes.getValue("type")); //chart type
			this.setId(attributes.getValue("id"));
			this.componentClassName = attributes.getValue("class");
			this.componentAttribute = attributes.getValue("attribute");
			if (attributes.getValue("paneColor") != null) {
			this.setPaneColor(attributes.getValue("paneColor"));
			}
			this.setFgAlpha(Integer.parseInt(attributes.getValue("fgAlpha")));
			this.setClass(attributes.getValue("class"));
			
			if (attributes.getValue("visible") != null) {
				this.setVisible(Boolean.parseBoolean(attributes.getValue("visible")));
			}
		
					
			this.setTooltip(attributes.getValue("tooltip"));
			this.setTooltiptext(attributes.getValue("tooltiptext"));
			this.setDraggable(attributes.getValue("draggable"));
			this.setDroppable(attributes.getValue("droppable"));
			if (attributes.getValue("focus") != null) {
				this.setFocus(Boolean.parseBoolean(attributes.getValue("focus")));
			}
			this.setStyle(attributes.getValue("style"));
			this.setZclass(attributes.getValue("zlass"));
			this.setSclass(attributes.getValue("sclass"));
			this.setLeft(attributes.getValue("left"));
			this.setTop(attributes.getValue("top"));
			if (attributes.getValue("zindex") != null) {
				this.setZIndex(Integer.parseInt(attributes.getValue("zindex")));
			}
			if (attributes.getValue("renderdefer") != null) {
				this.setRenderdefer(Integer.parseInt(attributes.getValue("renderdefer")));
			}
			this.setAction(attributes.getValue("action"));
			this.setHflex(attributes.getValue("hflex"));
			this.setVflex(attributes.getValue("vflex"));
			
			
			
			if (attributes.getValue("type") != null) 
				this.setType(attributes.getValue("type"));
			
			if (attributes.getValue("style") != null) 
				this.setStyle(attributes.getValue("style"));
			
			this.database = attributes.getValue("database");
			
			/*
			 * if the dimension input format of .arya files does NOT contains
			 * the unit like -height="200px"
			 */

			if ((attributes.getValue("height") != null) && attributes.getValue("height").contains("px")) {
				this.setHeight(attributes.getValue("height"));
			} else {
				this.setHeight(attributes.getValue("height") + "px");
			}

			if ((attributes.getValue("width") != null) && attributes.getValue("width").contains("px")) {
				this.setWidth(attributes.getValue("width"));
			} else {
				this.setWidth(attributes.getValue("width") + "px");
			}

			if (AryaUtils.isNotEmpty(attributes.getValue("onClick"))) {
				final String functionName = attributes.getValue("onClick");
				this.addEventListener("onClick", new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						ScriptHelper.executeScript(functionName, null, main);
					}
				});
			}

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

			if (AryaUtils.isNotEmpty(attributes.getValue("onDoubleClick"))) {
				final String functionName = attributes.getValue("onDoubleClick");
				this.addEventListener("onDoubleClick", new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						ScriptHelper.executeScript(functionName, null, main);
					}
				});
			}
			if (AryaUtils.isNotEmpty(attributes.getValue("onRightClick"))) {
				final String functionName = attributes.getValue("onRightClick");
				this.addEventListener("onRightClick", new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						ScriptHelper.executeScript(functionName, null, main);
					}
				});
			}

		}

	}
	
	public AryaChart getSelf() {
		return this;
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

	@Override
	public String getComponentClassName() {
		return componentClassName;
	}

	@Override
	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	@Override
	public String getComponentId() {
		return this.getId();
	}

	@Override
	public void setComponentId(String componentId) {
		this.setId(componentId);
	}

	@Override
	public String getComponentAttribute() {
		return componentAttribute;
	}

	@Override
	public void setComponentAttribute(String componentAttribute) {
		this.componentAttribute = componentAttribute;
	}

	

	
	
	@Override
	public Object getComponentParent() {
		return this.getComponentParent();
	}

	@Override
	public String getComponentTagName() {
		return "button";
	}

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public void setDatabase(String database) {
		this.database = database;
	}

	@Override
	public void setComponentValue(String componentValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getComponentValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String attribute) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAttributeValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttributeValue(String attributeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAttributeLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttributeLabel(String attributeLabel) {
		// TODO Auto-generated method stub
		
	}

}