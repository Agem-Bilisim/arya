package tr.com.agem.arya.metadata.component;

import org.xml.sax.Attributes;

import tr.com.agem.arya.metadata.interpreter.IAryaComponent;

public class GatewayComponentFactory {

	public static IAryaComponent getComponent(String tagName, Attributes attributes) {

		IAryaComponent comp = null;

		if ("attribute".equalsIgnoreCase(tagName)) {
			comp = new AryaAttribute(attributes);
		} else if ("attributes".equalsIgnoreCase(tagName)) {
			comp = new AryaAttributes();
		} 
		
		return comp;
	}

}
