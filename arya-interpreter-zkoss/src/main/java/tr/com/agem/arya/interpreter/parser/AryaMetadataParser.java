package tr.com.agem.arya.interpreter.parser;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.arya.interpreter.component.AryaComboItem;
import tr.com.agem.arya.interpreter.component.AryaListItem;
import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.component.ComponentFactory;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser extends DefaultHandler {

	private AryaWindow aryaWindow = null;
	private Stack<IAryaComponent> currentComponent = null;

	public AryaMetadataParser(AryaWindow aryaWindow) {
		this.aryaWindow = aryaWindow;
		this.currentComponent = new Stack<IAryaComponent>();
	}

	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		IAryaComponent comp = ComponentFactory.getComponent(tagName, aryaWindow, attributes);
		if (comp != null) {
			// If the component is a ComboItem (or ListItem) instance, 
			// then set its parent to the current component which is a ComboBox (or ListBox) instance.
			if (comp instanceof AryaComboItem || comp instanceof AryaListItem) {
				comp.setComponentParent(currentComponent.peek());
			}
			// For other components,
			// Set their parent to the component container which is a Div instance.
			else {
				comp.setComponentParent(aryaWindow.getComponentContainer());
			}
			// Update current component pointer
			currentComponent.push(comp);
			// Add new component to the component list of parent window
			if (aryaWindow.getComponents() == null) {
				aryaWindow.setComponents(new ArrayList<IAryaComponent>());
			}
			aryaWindow.getComponents().add(comp);
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
			((AryaScript) currentComponent.peek()).setScript(new String(ch, start, length));
		}
	}

}