package tr.com.agem.arya.metadata.builder;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;
import tr.com.agem.arya.metadata.util.PropertyReader;
import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.arya.metadata.xml.XMLElementFactory;
import tr.com.agem.tag.anotations.FormAttribute;

public class MetadataBuilder {

	private static final Logger logger = Logger.getLogger(MetadataBuilder.class.getName());
	private static final String SRC_PLACEHOLDER = "#SRC#";
	
	private StringBuilder xml = null;
	private boolean isScriptTagOpened = false;
	private boolean isViewTagOpened = false;
	
	public MetadataBuilder() {
		super();
		xml = new StringBuilder();
	}

	public void beginFile() {
		xml.append("<?page title=\"DOCSYS\" ?>\n");
		xml.append("<arya xmlns=\"aryaportal.org\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		xml.append("xsi:schemaLocation=\"aryaportal.org arya.xsd\">\n");
	}

	public void beginViewMetadata(String actionName, Class<?> cls) {
		isViewTagOpened = true;
		xml.append("\t<window id=\"")
				.append(MetadataGeneratorUtil.getInstance().generateWindowId(actionName, cls.getName()))
				.append("\">\n");
	}
	
	public void beginScriptMetadata() {
		isScriptTagOpened = true;
		xml.append("\t<script>\n");
	}

	public void appendViewBody(Class<?> cls, String[] properties, String actionName) {

		boolean generateRandomVal = Boolean.parseBoolean(PropertyReader.property("generate.random.values"));

		try {

			logger.log(Level.INFO, "Class: {0}", cls.getName());

			for (String property : properties) {

				if (!MetadataGeneratorUtil.getInstance().isEmpty(property)) {

					logger.log(Level.INFO, "Property: {0}", property);
					Field field = null;

					// Property belongs to another field (inner class)
					// such as KisiParameterForm > kisiParam.tckNoParam
					try {

						if (property.contains(".")) {

							String[] innerFields = property.split("\\.");
							Class<?> innerCls = cls;

							for (int i = 0; i < innerFields.length - 1; i++) {
								Field f = innerCls.getDeclaredField(innerFields[i]);
								innerCls = f.getType();
							}
							field = innerCls.getDeclaredField(innerFields[innerFields.length - 1]);

						} else {
							field = cls.getDeclaredField(property);
						}

					} catch (NoSuchFieldException e) {
						logger.log(Level.SEVERE, e.toString(), e);
					}

					if (field != null) {
						FormAttribute formAttrAnn = field.getAnnotation(FormAttribute.class);
						if (formAttrAnn != null) {
							XMLElement element = XMLElementFactory.getInstance(formAttrAnn.htmlType());
							if (element != null) {
								String elementStr = element.xml(property, formAttrAnn, generateRandomVal);
								if (elementStr != null) {
									xml.append(elementStr);
								}
							}
						}
					}

				}

			}

		} catch (SecurityException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

	}
	
	public void appendScriptBody(String script) {
		xml.append(script);
	}
	
	public void appendScriptSrc(String scriptSrc) {
		int index = xml.indexOf(SRC_PLACEHOLDER);
		if (index > -1) {
			xml.replace(index, index + SRC_PLACEHOLDER.length(), scriptSrc + SRC_PLACEHOLDER);
		}
	}
	
	public void endScriptMetadata() {
		int index = xml.indexOf(SRC_PLACEHOLDER);
		if (index > -1) {
			xml.replace(index, index + SRC_PLACEHOLDER.length(), "");
		}
		xml.append("\t</script>\n");
		isScriptTagOpened = false;
	}

	public void endViewMetadata() {
		xml.append("\t</window>\n");
		isViewTagOpened = false;
	}

	public void endFile() {
		xml.append("</arya>\n");
	}
	
	public String getMetadata() {
		return xml.toString();
	}

	public boolean isScriptTagOpened() {
		return isScriptTagOpened;
	}

	public boolean isViewTagOpened() {
		return isViewTagOpened;
	}
	
}
