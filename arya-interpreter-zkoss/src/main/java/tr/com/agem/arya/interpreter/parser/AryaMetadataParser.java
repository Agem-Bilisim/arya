package tr.com.agem.arya.interpreter.parser;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.arya.interpreter.component.AryaGrid;
import tr.com.agem.arya.interpreter.component.AryaListbox;
import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.component.AryaTemplate;
import tr.com.agem.arya.interpreter.component.ComponentFactory;
import tr.com.agem.arya.interpreter.component.menu.IAryaMenu;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser extends DefaultHandler {

	private AryaMain main = null;
	private Stack<IAryaComponent> currentComponent = null;

	public AryaMetadataParser(AryaMain main) {
		this.main = main;
		this.currentComponent = new Stack<IAryaComponent>();
	}

	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		IAryaComponent comp = ComponentFactory.getComponent(tagName, main, attributes);
		if (comp != null) {
			if (comp instanceof AryaTemplate) {
				if (currentComponent.peek() instanceof AryaListbox) {
					((AryaListbox)currentComponent.peek()).setAryaTemplate(comp);
				}
				else if(currentComponent.peek() instanceof AryaGrid) {
					((AryaGrid)currentComponent.peek()).setAryaTemplate(comp);
				}
				currentComponent.push(comp);
			} else if ((currentComponent.size() > 0) && (currentComponent.peek() instanceof AryaTemplate)) {
				AryaTemplate template = (AryaTemplate) currentComponent.peek();
				template.getChildren().add(comp);				
				currentComponent.push(template);
			}
			else {
				comp.setComponentParent(currentComponent.size() > 0? currentComponent.peek():main.getComponentContainer());
				currentComponent.push(comp);
				main.getAryaWindow().getComponents().add(comp);
			}

			// Add new component to the component list of parent window
			if (main.getAryaWindow().getComponents() == null) {
				main.getAryaWindow().setComponents(new ArrayList<IAryaComponent>());
			}
			if (main.getAryaNavBar().getComponents() == null) {
				main.getAryaNavBar().setComponents(new ArrayList<IAryaComponent>());
			}

			if (comp instanceof IAryaMenu) {
				main.getAryaNavBar().getComponents().add(comp);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String tagName) throws SAXException {
		if (!currentComponent.isEmpty()) {
			currentComponent.pop();
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (!currentComponent.isEmpty() && currentComponent.peek() instanceof AryaScript) {
			String scriptPart = new String(ch, start, length);
			AryaScript scriptObj = (AryaScript) currentComponent.peek();
			scriptObj.setScript(scriptObj.getScript() != null ? scriptObj.getScript() + scriptPart : scriptPart);
		}
	}

}
