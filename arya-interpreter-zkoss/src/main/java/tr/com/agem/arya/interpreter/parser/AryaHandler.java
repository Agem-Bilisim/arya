package tr.com.agem.arya.interpreter.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tr.com.agem.arya.interpreter.component.AryaButton;
import tr.com.agem.arya.interpreter.component.AryaCheckbox;
import tr.com.agem.arya.interpreter.component.AryaCombobox;
import tr.com.agem.arya.interpreter.component.AryaDatebox;
import tr.com.agem.arya.interpreter.component.AryaLabel;
import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.component.AryaTextbox;
import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;

public class AryaHandler extends DefaultHandler {

	private AryaWindow aryaWindow = null;
	private List<IAryaComponentProperty> components = null;
	
	private final StringBuilder scriptBody = new StringBuilder(64);
	private String scriptSource = null;
	private boolean onScriptTag = false;

	public AryaHandler(AryaWindow aryaWindow) {
		this.aryaWindow = aryaWindow;
		this.components = new ArrayList<IAryaComponentProperty>();
	}

	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		if (tagName.equals("label")) {
			components.add(new AryaLabel(aryaWindow, attributes));
		} else if ("textbox".equalsIgnoreCase(tagName)) {
			components.add(new AryaTextbox(aryaWindow, attributes));
		} else if ("checkbox".equalsIgnoreCase(tagName)) {
			components.add(new AryaCheckbox(aryaWindow, attributes));
		} else if ("datebox".equalsIgnoreCase(tagName)) {
			components.add(new AryaDatebox(aryaWindow, attributes));
		} else if ("button".equalsIgnoreCase(tagName)) {
			components.add(new AryaButton(aryaWindow, attributes));
		} else if ("intbox".equalsIgnoreCase(tagName)) {
			components.add(new AryaTextbox(aryaWindow, attributes));
		} else if ("doublebox".equalsIgnoreCase(tagName)) {
			components.add(new AryaTextbox(aryaWindow, attributes));
		} else if ("listbox".equalsIgnoreCase(tagName)) {
			// TODO multiple combobox impl
		} else if ("combobox".equalsIgnoreCase(tagName)) {
			components.add(new AryaCombobox(aryaWindow, attributes));
		} else if ("script".equalsIgnoreCase(tagName)) {
			onScriptTag = true;
			scriptSource = attributes.getValue("src");
		}
	}

	@Override
	public void endElement(String uri, String localName, String tagName) throws SAXException {
		if ("arya".equalsIgnoreCase(tagName)) {
			aryaWindow.add(components);
		} else if ("script".equalsIgnoreCase(tagName)) {
			components.add(new AryaScript(aryaWindow, scriptBody.toString().trim(), scriptSource));
			scriptBody.setLength(0);
			onScriptTag = false;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (onScriptTag) {
			scriptBody.append(new String(ch, start, length));
		}
	}

}
