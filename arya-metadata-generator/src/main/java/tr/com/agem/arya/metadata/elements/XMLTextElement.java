package tr.com.agem.arya.metadata.elements;

import tr.com.agem.arya.metadata.util.RandomStringGenerator;
import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.tag.anotations.FormAttribute;

public class XMLTextElement implements XMLElement {

	@Override
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal) {

		StringBuilder xml = new StringBuilder();

		if (formAttrAnn.label() != null && !formAttrAnn.label().isEmpty()) {
			xml.append("\t\t<label id=\"").append(property).append("lbl").append("\" value=\"").append(formAttrAnn.label()).append("\" />\n");
		}
		xml.append("\t\t<textbox type=\"text\" id=\"").append(property).append("\" ");
		if (generateRandomVal) {
			xml.append(" value=\"").append(new RandomStringGenerator(formAttrAnn.maxlength() > 0 ? formAttrAnn.maxlength() : 10).nextString()).append("\" ");
		}
		if (formAttrAnn.mandatory()) {
			// TODO xul mandatory attr?
		}
		if (formAttrAnn.width() > 0) {
			xml.append(" width=\"").append(formAttrAnn.width()).append("\" ");
		}
		if (formAttrAnn.height() > 0) {
			xml.append(" height=\"").append(formAttrAnn.height()).append("\" ");
		}
		if (formAttrAnn.maxlength() > 0) {
			xml.append(" maxlength=\"").append(formAttrAnn.maxlength()).append("\" ");
		}
		if (formAttrAnn.placeHolder() != null) {
			xml.append(" placeholder=\"").append(formAttrAnn.placeHolder()).append("\" ");
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
		xml.append(" />\n");

		return xml.toString();
	}

}
