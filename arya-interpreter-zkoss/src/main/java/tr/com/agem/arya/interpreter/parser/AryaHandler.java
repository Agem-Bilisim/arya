package tr.com.agem.arya.interpreter.parser;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.arya.interpreter.component.AryaButton;
import tr.com.agem.arya.interpreter.component.AryaCheckbox;
import tr.com.agem.arya.interpreter.component.AryaDatebox;
import tr.com.agem.arya.interpreter.component.AryaLabel;
import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.component.AryaTextbox;
import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;

public class AryaHandler extends DefaultHandler {

	private final StringBuilder characters = new StringBuilder(64);
	private String scriptSource;
	boolean script;

	Object parent;
	AryaWindow aryaWindow;
	List<IAryaComponentProperty> components;

	public AryaHandler(Object parent, AryaWindow aryaWindow, List<IAryaComponentProperty> components) {
		this.parent = parent;
		this.aryaWindow = aryaWindow;
		this.components = components;
	}

	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {

		if (tagName.equalsIgnoreCase("arya")) {
			System.out.println("arya");
		} else if (tagName.equalsIgnoreCase("window")) {
			System.out.println("window");
		} else if (tagName.equals("label")) {
			components.add(new AryaLabel(this.parent, this.aryaWindow, attributes));
		} else if ("textbox".equalsIgnoreCase(tagName)) {
			components.add(new AryaTextbox(this.parent, this.aryaWindow, attributes));
		} else if ("checkbox".equalsIgnoreCase(tagName)) {
			components.add(new AryaCheckbox(this.parent, this.aryaWindow, attributes));
		} else if ("datebox".equalsIgnoreCase(tagName)) {
			components.add(new AryaDatebox(this.parent, this.aryaWindow, attributes));
		} else if ("button".equalsIgnoreCase(tagName)) {
			components.add(new AryaButton(this.parent, this.aryaWindow, attributes));
		} else if ("intbox".equalsIgnoreCase(tagName)) {
			components.add(new AryaTextbox(this.parent, this.aryaWindow, attributes));
		} else if ("doublebox".equalsIgnoreCase(tagName)) {
			components.add(new AryaTextbox(this.parent, this.aryaWindow, attributes));
		} else if ("listbox".equalsIgnoreCase(tagName)) {
			// TODO multiple combobox impl
		} else if ("selectbox".equalsIgnoreCase(tagName)) {
			// TODO single combobox impl
		} else if ("script".equalsIgnoreCase(tagName)) {
			script = true;
			scriptSource = attributes.getValue("src");
		}
	}

	@Override
	public void endElement(String uri, String localName, String tagName) throws SAXException {

		if ("arya".equalsIgnoreCase(tagName)) {
			aryaWindow.add(components);
		} else if ("script".equalsIgnoreCase(tagName)) {
			components.add(new AryaScript(this.parent, this.aryaWindow, characters.toString().trim(), scriptSource));
			characters.setLength(0);
			script = false;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (script) {
			characters.append(new String(ch, start, length));
		}
	}
}
