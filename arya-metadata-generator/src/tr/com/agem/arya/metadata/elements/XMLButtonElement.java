package tr.com.agem.arya.metadata.elements;

import tr.com.agem.arya.metadata.xml.XMLElement;
import tr.com.agem.tag.anotations.FormAttribute;

public class XMLButtonElement implements XMLElement {

	@Override
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal) {
		
		StringBuilder xml = new StringBuilder();
		
		xml.append("<button id=\"").append(property).append("\" label=\"").append(formAttrAnn.label()).append("\" ");
		if (formAttrAnn.onClickFunction() != null) {
			xml.append(" onClick=\"").append(formAttrAnn.onClickFunction()).append("\" ");
		}
		if (formAttrAnn.width() > 0) {
			xml.append(" width=\"").append(formAttrAnn.width()).append("\" ");
		}
		if (formAttrAnn.height() > 0) {
			xml.append(" height=\"").append(formAttrAnn.height()).append("\" ");
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
