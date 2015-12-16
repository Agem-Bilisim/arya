package tr.com.agem.parser;


import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.component.AryaAttribute;
import tr.com.agem.component.GatewayComponentFactory;
import tr.com.agem.component.base.AryaGatewayMain;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaGatewayMetadataParser extends DefaultHandler {

	private AryaGatewayMain main = null;
	private Stack<IAryaComponent> currentAttrComponent = null;

	public AryaGatewayMetadataParser(AryaGatewayMain main) 
	{
		this.main = main;
		this.currentAttrComponent = new Stack<IAryaComponent>();
	}

	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		
		IAryaComponent comp = GatewayComponentFactory.getComponent(tagName, attributes);
		
		if (comp != null && (comp instanceof AryaAttribute)) {
			currentAttrComponent.push(comp);
			main.getAryaSessionComponents().add(comp);
		}
	}

	@Override
	public void endElement(String uri, String localName, String tagName) throws SAXException {
		if (!currentAttrComponent.isEmpty()) {
			currentAttrComponent.pop();
		}
	}

	public Stack<IAryaComponent> getCurrentAttrComponent() {
		return currentAttrComponent;
	}

	public void setCurrentAttrComponent(Stack<IAryaComponent> currentAttrComponent) {
		this.currentAttrComponent = currentAttrComponent;
	}
}
