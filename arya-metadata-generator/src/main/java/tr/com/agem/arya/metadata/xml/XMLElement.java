package tr.com.agem.arya.metadata.xml;

import tr.com.agem.tag.anotations.FormAttribute;

public interface XMLElement {
	public String xml(String property, FormAttribute formAttrAnn, boolean generateRandomVal);
}
