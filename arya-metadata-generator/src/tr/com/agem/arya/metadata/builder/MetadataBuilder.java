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

	public String start(Class<?> cls, String[] properties, String actionName, boolean isFirst) {

		StringBuilder xml = new StringBuilder();
		boolean generateRandomVal = Boolean.parseBoolean(PropertyReader.property("generate.random.values"));

		try {

			logger.log(Level.INFO, "Class: {0}", cls.getName());

			if (isFirst) {

				xml.append("<?page title=\"DOCSYS\" ?>\n");
				xml.append("<arya xmlns=\"aryaportal.org\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
				xml.append("xsi:schemaLocation=\"aryaportal.org arya.xsd\">\n");

				xml.append("\t<window id=\"")
						.append(MetadataGeneratorUtil.getInstance().generateWindowId(actionName, cls.getName()))
						.append("\">\n");
			}

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

		return xml.toString();
	}

	public String end() {
		StringBuilder xml = new StringBuilder();

		xml.append("\t</window>\n");
		xml.append("</arya>\n");

		return xml.toString();
	}

}
