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
		
		String componentName = formAttrAnn.selectBoxMultiple() ? "listbox" : "combobox";
		
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
		
		if (generateRandomVal) {
			xml.append(" >\n");
			if (formAttrAnn.selectBoxMultiple()) {
				xml.append("\t\t\t<listitem label=\"").append(new RandomStringGenerator(10).nextString()).append("\" value=\"").append(new RandomStringGenerator(4).nextString()).append("\" />\n");
			} else {
				xml.append("\t\t\t<comboitem label=\"").append(new RandomStringGenerator(10).nextString()).append("\" value=\"").append(new RandomStringGenerator(4).nextString()).append("\" />\n");
			}
			xml.append("\t\t</").append(componentName).append(">\n");
		}
		else {
			xml.append(" />\n");
		}
		
		return xml.toString();
	}

}
