package tr.com.agem.arya.interpreter.component;

import java.util.Map;

import org.xml.sax.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ScriptHelper;
import tr.com.agem.arya.interpreter.utils.AryaInterpreterHelper;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListbox extends Listbox implements IAryaComponent {

	private static final long serialVersionUID = 5336801586396485340L;

	private String componentClassName;
	private String componentAttribute;
	
	public AryaListbox(final AryaMain main, Attributes attributes) {
		super();

		if (AryaUtils.isNotEmpty(attributes)) {
			this.setId(attributes.getValue("id"));
			this.componentClassName = attributes.getValue("class");
			this.componentAttribute = attributes.getValue("attribute");

			this.setClass(attributes.getValue("class"));
			if (attributes.getValue("visible") != null) {
				this.setVisible(Boolean.parseBoolean(attributes.getValue("visible")));
			}
			if (attributes.getValue("disabled") != null) {
				this.setDisabled(Boolean.parseBoolean(attributes.getValue("disabled")));
			}

			/*if (attributes.getValue("selectedItem") != null) {
				Listitem listItem = new Listitem(attributes.getValue("selectedItem"));
				this.setSelectedItem(listItem);
			}*/

			if (attributes.getValue("maxlength") != null) {
				this.setMaxlength(Integer.parseInt(attributes.getValue("maxlength")));
			}
			if (attributes.getValue("tabindex") != null) {
				this.setTabindex(Integer.parseInt(attributes.getValue("tabindex")));
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
			// TODO unit checking will be fixed
			this.setHeight(attributes.getValue("height"));
			this.setWidth(attributes.getValue("width"));
			
			this.setMold(attributes.getValue("mold"));
			
			if(attributes.getValue("rows") != null) //TODO number value exception
				this.setRows(new Integer (attributes.getValue("rows")));
			
			if(attributes.getValue("name") != null)
				this.setName(attributes.getValue("name"));
			
			if(attributes.getValue("pagesize") != null) //TODO number value exception
				this.setPageSize(new Integer (attributes.getValue("pagesize")));
			
			if (attributes.getValue("checkmark") != null)
				this.setCheckmark(Boolean.parseBoolean(attributes.getValue("checkmark")));
			
			

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

			if (AryaUtils.isNotEmpty(attributes.getValue("onSelect"))) {
				final String functionName = attributes.getValue("onSelect");
				this.addEventListener("onSelect", new EventListener<Event>() {
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
	public String getComponentValue() {
		/*
		 * There is no componentValue variable for this component. This function
		 * was created for IAryaComponent interface.
		 */
		return null;
	}

	@Override
	public void setComponentValue(String componentValue) {
		/*
		 * There is no componentValue variable for this component. This function
		 * was created for IAryaComponent interface.
		 */
	}

}
