package tr.com.agem.arya.metadata.elements;

import java.util.Random;

import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.tag.anotations.FormAttribute;

public class XMLCheckboxElement implements XMLElement {
	
	private static final Random random = new Random();

	@Override
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal) {

		StringBuilder xml = new StringBuilder();

		xml.append("\t\t<checkbox type=\"text\" id=\"").append(property).append("\" ");
		if (generateRandomVal) {
			xml.append(" checked=\"").append(random.nextBoolean()).append("\" ");
		}
		if (formAttrAnn.label() != null && !formAttrAnn.label().isEmpty()) {
			xml.append(" label=\"").append(formAttrAnn.label()).append("\" ");
		}
		if (formAttrAnn.onClickFunction() != null) {
			xml.append(" onCheck=\"").append(formAttrAnn.onClickFunction()).append("\" ");
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
