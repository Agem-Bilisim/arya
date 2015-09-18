package tr.com.agem.arya.interpreter.components;

import org.xml.sax.Attributes;

import android.widget.TableLayout;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaListBox extends TableLayout implements IAryaComponent {

	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaListBox(Attributes attributes, AryaMain main) {

		super(main.getAryaWindow().getContext());

		if(AryaUtils.isNotEmpty(attributes)){
			this.componentId = attributes.getValue("id");
			this.componentClassName = attributes.getValue("class");
			this.componentValue = attributes.getValue("value");
			this.componentAttribute = attributes.getValue("attribute");
		}
		main.getAryaWindow().addView(this);
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
}
