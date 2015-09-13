package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;

import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

	public static IAryaComponent getComponent(String tagName,
			AryaWindow aryaWindow, Attributes attributes) {

		IAryaComponent comp = null;

		if ("label".equalsIgnoreCase(tagName)) {
			comp = new AryaLabel(aryaWindow, attributes);
		} else if ("textbox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(aryaWindow, attributes);
		} else if ("checkbox".equalsIgnoreCase(tagName)) {
			comp = new AryaCheckbox(aryaWindow, attributes);
		} else if ("datebox".equalsIgnoreCase(tagName)) {
			comp = new AryaDatebox(aryaWindow, attributes);
		} else if ("button".equalsIgnoreCase(tagName)) {
			comp = new AryaButton(aryaWindow, attributes);
		} else if ("intbox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(aryaWindow, attributes);
		} else if ("doublebox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(aryaWindow, attributes);
		} else if ("listbox".equalsIgnoreCase(tagName)) {
			comp = new AryaListbox(aryaWindow, attributes);
		} else if ("listitem".equalsIgnoreCase(tagName)) {
			comp = new AryaListItem(aryaWindow, attributes);
		} else if ("combobox".equalsIgnoreCase(tagName)) {
			comp = new AryaCombobox(aryaWindow, attributes);
		} else if ("multipleCombobox".equalsIgnoreCase(tagName)) {
			comp = new AryaMultipleCombobox(aryaWindow, attributes);
		} else if ("comboitem".equalsIgnoreCase(tagName)) {
			comp = new AryaComboItem(aryaWindow, attributes);
		} else if ("mcomboitem".equalsIgnoreCase(tagName)) {
			comp = new AryaMultiComboItem(aryaWindow, attributes);
		} else if ("script".equalsIgnoreCase(tagName)) {
			comp = new AryaScript(aryaWindow, attributes);
		} else if ("listhead".equalsIgnoreCase(tagName)) {
			comp = new AryaListHead(aryaWindow, attributes);
		} else if ("listheader".equalsIgnoreCase(tagName)) {
			comp = new AryaListHeader(aryaWindow, attributes);
		} else if ("listcell".equalsIgnoreCase(tagName)) {
			comp = new AryaListCell(aryaWindow, attributes);
		} else if ("grid".equalsIgnoreCase(tagName)) {
			comp = new AryaGrid(aryaWindow, attributes);
		} else if ("columns".equalsIgnoreCase(tagName)) {
			comp = new AryaColumns(aryaWindow, attributes);
		} else if ("column".equalsIgnoreCase(tagName)) {
			comp = new AryaColumn(aryaWindow, attributes);
		} else if ("rows".equalsIgnoreCase(tagName)) {
			comp = new AryaRows(aryaWindow, attributes);
		} else if ("row".equalsIgnoreCase(tagName)) {
			comp = new AryaRow(aryaWindow, attributes);
		}

		return comp;
	}

}
