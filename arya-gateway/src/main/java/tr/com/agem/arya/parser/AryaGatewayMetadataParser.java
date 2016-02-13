package tr.com.agem.arya.parser;


import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.arya.component.AryaAttribute;
import tr.com.agem.arya.component.GatewayComponentFactory;
import tr.com.agem.arya.component.base.AryaGatewayMain;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaGatewayMetadataParser extends DefaultHandler {

	private AryaGatewayMain main = null;
	private Stack<IAryaComponent> currentComponent = null;

	public AryaGatewayMetadataParser(AryaGatewayMain main) 
	{
		this.main = main;
		this.currentComponent = new Stack<IAryaComponent>();
	}

	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		
		IAryaComponent comp = GatewayComponentFactory.getComponent(tagName, attributes);
		
		if (comp != null) {
			if(comp instanceof AryaAttribute){
				comp.setComponentParent(currentComponent.peek());
			}
			currentComponent.push(comp);
			main.getAryaSessionComponents().add(comp);
		}
	}

	@Override
	public void endElement(String uri, String localName, String tagName) throws SAXException {
		if (!currentComponent.isEmpty()) {
			currentComponent.pop();
		}
	}

	public Stack<IAryaComponent> getCurrentComponent() {
		return currentComponent;
	}

	public void setCurrentComponent(Stack<IAryaComponent> currentComponent) {
		this.currentComponent = currentComponent;
	}
}
