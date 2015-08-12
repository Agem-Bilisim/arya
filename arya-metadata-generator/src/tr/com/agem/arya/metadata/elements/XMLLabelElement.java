package tr.com.agem.arya.metadata.elements;

import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.tag.anotations.FormAttribute;

public class XMLLabelElement implements XMLElement {

	@Override
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal) {
		StringBuilder xml = new StringBuilder();
		xml.append("<label id=\"").append(property).append("lbl").append("\" value=\"").append(formAttrAnn.label()).append("\" />\n");
		return xml.toString();
	}

}
