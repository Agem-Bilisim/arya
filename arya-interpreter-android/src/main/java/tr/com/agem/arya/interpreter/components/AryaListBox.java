package tr.com.agem.arya.interpreter.components;

import android.widget.HorizontalScrollView;
import android.widget.TableLayout;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaTemplate;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListBox extends TableLayout implements IAryaComponent, IAryaTemplate {

	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;
	private String database;
	private String attribute;
	private String attributeValue;
	private String attributeLabel;

	private AryaTemplate template;

	private String onSelect;

	public AryaListBox(Attributes attributes, AryaMain main) {

		super(main.getAryaWindow().getContext());

		if(AryaUtils.isNotEmpty(attributes)){
			this.componentId = attributes.getValue("id");
			this.componentClassName = attributes.getValue("class");
			this.componentValue = attributes.getValue("value");
			this.componentAttribute = attributes.getValue("attribute");
			this.database = attributes.getValue("database");

			this.attribute = attributes.getValue("attribute");
			this.attributeValue = attributes.getValue("attributeValue");
			this.attributeLabel = attributes.getValue("attributeLabel");


			onSelect =  attributes.getValue("onSelect");
		}


		//main.getAryaWindow().addView(this);
		this.setMinimumWidth(LayoutParams.MATCH_PARENT);
		HorizontalScrollView HorizontalScrollViewParent = new HorizontalScrollView(main.getAryaWindow().getContext());
		main.getAryaWindow().addView(HorizontalScrollViewParent);
		HorizontalScrollViewParent.addView(this);

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
		return componentId;
	}

	@Override
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Override
	public String getComponentAttribute() {
		return componentAttribute;
	}

	@Override
	public String validate() {
		return null;
	}

	@Override
	public void setComponentParent(Object o) {
	}

	@Override
	public void setComponentAttribute(String componentAttribute) {
		this.componentAttribute = componentAttribute;
	}

	@Override
	public String getComponentValue() {
		return componentValue;
	}

	@Override
	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	@Override
	public Object getAryaTemplate() {
		return template;
	}

	@Override
	public void setAryaTemplate(Object template) {
		this.template = (AryaTemplate) template;
	}

	public String getOnSelect() {
		return onSelect;
	}

	@Override
	public Object getComponentParent() {
		return this.getComponentParent();
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
	public String getAttribute() {
		return attribute;
	}

	@Override
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public String getAttributeLabel() {
		return attributeLabel;
	}

	@Override
	public void setAttributeLabel(String attributeLabel) {
		this.attributeLabel = attributeLabel;
	}

	@Override
	public String getComponentTagName() {
		return "listbox";
	}


}
