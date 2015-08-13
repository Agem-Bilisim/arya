package tr.com.agem.arya.metadata.elements;

import tr.com.agem.arya.metadata.util.RandomStringGenerator;
import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.tag.anotations.FormAttribute;

public class XMLSelectElement implements XMLElement {

	@Override
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal) {
		
		StringBuilder xml = new StringBuilder();

		if (formAttrAnn.label() != null && !formAttrAnn.label().isEmpty()) {
			xml.append("\t\t<label id=\"").append(property).append("lbl").append("\" value=\"").append(formAttrAnn.label()).append("\" />\n");
		}
		
		String componentName = formAttrAnn.selectBoxMultiple() ? "listbox" : "selectbox";
		
		xml.append("\t\t<").append(componentName).append(" id=\"").append(property).append("\" ");
		if (formAttrAnn.mandatory()) {
			// TODO xul mandatory attr?
		}
		if (formAttrAnn.width() > 0) {
			xml.append(" width=\"").append(formAttrAnn.width()).append("\" ");
		}
		if (formAttrAnn.height() > 0) {
			xml.append(" height=\"").append(formAttrAnn.height()).append("\" ");
		}
		if (formAttrAnn.readonly()) {
			xml.append(" readonly=\"true\" ");
		}
		if (formAttrAnn.top() > 0) {
			xml.append(" top=\"").append(formAttrAnn.top()).append("\"");
		}
		if (formAttrAnn.left() > 0) {
			xml.append(" left=\"").append(formAttrAnn.left()).append("\"");
		}
		
//		Single Selection:
//		<selectbox id="typesSelectbox" model="${win$composer.typesModel}" >
//	    <template name="model">
//	        <label value="${each}" />
//	    </template>
//	    </selectbox>
//		Multiple Selection:
//		<listbox id="salesmenListbox" model="${win$composer.salesmenModel}" checkmark="true">
//	    <template name="model">
//	        <listitem label="${each}" />
//	    </template>
//		</listbox>
		
		if (generateRandomVal) {
			xml.append(" >\n");
			xml.append("\t\t\t<template>\n"); // TODO model?
			if (formAttrAnn.selectBoxMultiple()) {
				xml.append("\t\t\t\t<listitem label=\"").append(new RandomStringGenerator(10).nextString()).append("\" />\n");
			} else {
				xml.append("\t\t\t\t<label value=\"").append(new RandomStringGenerator(10).nextString()).append("\" />\n");
			}
			xml.append("\t\t\t</template>\n");
			xml.append("\t\t</").append(componentName).append(">\n");
		}
		else {
			xml.append(" />\n");
		}
		
		return xml.toString();
	}

}
