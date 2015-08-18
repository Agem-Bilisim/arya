package tr.com.agem.arya.metadata.elements;

import java.util.Random;

import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.tag.anotations.FormAttribute;

public class XMLDoubleElement implements XMLElement {

	@Override
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal) {

		StringBuilder xml = new StringBuilder();

		if (formAttrAnn.label() != null && !formAttrAnn.label().isEmpty()) {
			xml.append("\t\t<label id=\"").append(property).append("lbl").append("\" value=\"").append(formAttrAnn.label()).append("\" />\n");
		}
		xml.append("\t\t<doublebox id=\"").append(property).append("\" ");
		if (generateRandomVal) {
			xml.append(" value=\"").append(new Random().nextDouble()).append("\" ");
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
		// TODO format="" ???? or precision="" ????
		xml.append(" />\n");

		return xml.toString();
	}

}
