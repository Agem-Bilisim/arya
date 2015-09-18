package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.component.menu.AryaMenu;
import tr.com.agem.arya.interpreter.component.menu.AryaMenuBar;
import tr.com.agem.arya.interpreter.component.menu.AryaMenuItem;
import tr.com.agem.arya.interpreter.component.menu.AryaMenuPopUp;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

	public static IAryaComponent getComponent(String tagName, AryaMain main, Attributes attributes) {

		IAryaComponent comp = null;

		if ("label".equalsIgnoreCase(tagName)) {
			comp = new AryaLabel(main, attributes);
		} else if ("textbox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(main, attributes);
		} else if ("checkbox".equalsIgnoreCase(tagName)) {
			comp = new AryaCheckbox(main, attributes);
		} else if ("datebox".equalsIgnoreCase(tagName)) {
			comp = new AryaDatebox(main, attributes);
		} else if ("button".equalsIgnoreCase(tagName)) {
			comp = new AryaButton(main, attributes);
		} else if ("intbox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(main, attributes);
		} else if ("doublebox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(main, attributes);
		} else if ("listbox".equalsIgnoreCase(tagName)) {
			comp = new AryaListbox(main, attributes);
		} else if ("listitem".equalsIgnoreCase(tagName)) {
			comp = new AryaListItem(main, attributes);
		} else if ("combobox".equalsIgnoreCase(tagName)) {
			comp = new AryaCombobox(main, attributes);
		} else if ("multipleCombobox".equalsIgnoreCase(tagName)) {
			comp = new AryaMultipleCombobox(main, attributes);
		} else if ("comboitem".equalsIgnoreCase(tagName)) {
			comp = new AryaComboItem(main, attributes);
		} else if ("mcomboitem".equalsIgnoreCase(tagName)) {
			comp = new AryaMultiComboItem(main, attributes);
		} else if ("script".equalsIgnoreCase(tagName)) {
			comp = new AryaScript(main, attributes);
		} else if ("listhead".equalsIgnoreCase(tagName)) {
			comp = new AryaListHead(main, attributes);
		} else if ("listheader".equalsIgnoreCase(tagName)) {
			comp = new AryaListHeader(main, attributes);
		} else if ("listcell".equalsIgnoreCase(tagName)) {
			comp = new AryaListCell(main, attributes);
		} else if ("grid".equalsIgnoreCase(tagName)) {
			comp = new AryaGrid(main, attributes);
		} else if ("columns".equalsIgnoreCase(tagName)) {
			comp = new AryaColumns(main, attributes);
		} else if ("column".equalsIgnoreCase(tagName)) {
			comp = new AryaColumn(main, attributes);
		} else if ("rows".equalsIgnoreCase(tagName)) {
			comp = new AryaRows(main, attributes);
		} else if ("row".equalsIgnoreCase(tagName)) {
			comp = new AryaRow(main, attributes);
		}
		
		else if ("menubar".equalsIgnoreCase(tagName)) {
			comp = new AryaMenuBar(main, attributes);
		} else if ("menu".equalsIgnoreCase(tagName)) {
			comp = new AryaMenu(main, attributes);
		}else if ("menupopup".equalsIgnoreCase(tagName)) {
			comp = new AryaMenuPopUp(main, attributes);
		}else if ("menuitem".equalsIgnoreCase(tagName)) {
			comp = new AryaMenuItem(main, attributes);
		}

		return comp;
	}

}
