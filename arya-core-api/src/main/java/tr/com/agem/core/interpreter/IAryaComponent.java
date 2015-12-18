package tr.com.agem.core.interpreter;

public interface IAryaComponent {

	void setComponentId(String componentId);

	String getComponentId();

	void setComponentClassName(String componentClassName);

	String getComponentClassName();

	void setComponentValue(String componentValue);

	String getComponentValue();

	void setComponentAttribute(String componentAttribute);

	String getComponentAttribute();

	String validate();
	
	void setComponentParent(Object parent);
	
	Object getComponentParent();
	
	String getDatabase();
	
	void setDatabase(String database);
	
	String getAttribute();
	
	void setAttribute(String attribute);
	
	String getAttributeValue();
	
	void setAttributeValue(String attributeValue);
	
	String getAttributeLabel();
	
	void setAttributeLabel(String attributeLabel);

	String getComponentTagName();
}
