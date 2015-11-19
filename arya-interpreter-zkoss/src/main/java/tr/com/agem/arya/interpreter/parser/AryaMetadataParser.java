package tr.com.agem.arya.interpreter.parser;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.arya.interpreter.component.AryaGrid;
import tr.com.agem.arya.interpreter.component.AryaListbox;
import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.component.AryaTab;
import tr.com.agem.arya.interpreter.component.AryaTabbox;
import tr.com.agem.arya.interpreter.component.AryaTabpanel;
import tr.com.agem.arya.interpreter.component.AryaTabpanels;
import tr.com.agem.arya.interpreter.component.AryaTabs;
import tr.com.agem.arya.interpreter.component.AryaTemplate;
import tr.com.agem.arya.interpreter.component.ComponentFactory;
import tr.com.agem.arya.interpreter.component.menu.IAryaMenu;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser extends DefaultHandler {

	private AryaMain main = null;
	private AryaTabpanel tabpanel = null;
	private Stack<IAryaComponent> currentComponent = null;
	private Boolean isMenu = false;

	public AryaMetadataParser(AryaMain main, Boolean isMenu, AryaTabpanel tabpanel) {
		this.main = main;
		this.currentComponent = new Stack<IAryaComponent>();
		this.isMenu = isMenu;
		this.tabpanel = tabpanel;
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
				if(comp.getComponentId() != null && !comp.getComponentId().equals(""))
					comp.setComponentId(tabpanel.getComponentId()+"-"+comp.getComponentId());
				
				comp.setComponentParent(currentComponent.size() > 0 ? currentComponent.peek():(isMenu ? main.getMenuContainer() : tabpanel));
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
