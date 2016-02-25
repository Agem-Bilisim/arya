package tr.com.agem.arya.metadata.interpreter;

public interface IAryaComponent {

	void setComponentId(String componentId);

	String getComponentId();

	void setComponentClassName(String componentClassName);

	String getComponentClassName();

	void setComponentValue(String componentValue);

	String getComponentValue();

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
