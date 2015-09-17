package tr.com.agem.arya.interpreter.component;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.base.components.AryaMain;
import tr.com.agem.arya.interpreter.component.menu.AryaMenu;
import tr.com.agem.arya.interpreter.component.menu.AryaMenuBar;
import tr.com.agem.arya.interpreter.component.menu.AryaMenuItem;
import tr.com.agem.arya.interpreter.component.menu.AryaMenuPopUp;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

	public static IAryaComponent getComponent(String tagName, AryaMain main, Attributes attributes) {

		IAryaComponent comp = null;

		if ("label".equalsIgnoreCase(tagName)) {
			comp = new AryaLabel(main.getAryaWindow(), attributes);
		} else if ("textbox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(main.getAryaWindow(), attributes);
		} else if ("checkbox".equalsIgnoreCase(tagName)) {
			comp = new AryaCheckbox(main.getAryaWindow(), attributes);
		} else if ("datebox".equalsIgnoreCase(tagName)) {
			comp = new AryaDatebox(main.getAryaWindow(), attributes);
		} else if ("button".equalsIgnoreCase(tagName)) {
			comp = new AryaButton(main.getAryaWindow(), attributes);
		} else if ("intbox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(main.getAryaWindow(), attributes);
		} else if ("doublebox".equalsIgnoreCase(tagName)) {
			comp = new AryaTextbox(main.getAryaWindow(), attributes);
		} else if ("listbox".equalsIgnoreCase(tagName)) {
			comp = new AryaListbox(main.getAryaWindow(), attributes);
		} else if ("listitem".equalsIgnoreCase(tagName)) {
			comp = new AryaListItem(main.getAryaWindow(), attributes);
		} else if ("combobox".equalsIgnoreCase(tagName)) {
			comp = new AryaCombobox(main.getAryaWindow(), attributes);
		} else if ("multipleCombobox".equalsIgnoreCase(tagName)) {
			comp = new AryaMultipleCombobox(main.getAryaWindow(), attributes);
		} else if ("comboitem".equalsIgnoreCase(tagName)) {
			comp = new AryaComboItem(main.getAryaWindow(), attributes);
		} else if ("mcomboitem".equalsIgnoreCase(tagName)) {
			comp = new AryaMultiComboItem(main.getAryaWindow(), attributes);
		} else if ("script".equalsIgnoreCase(tagName)) {
			comp = new AryaScript(main.getAryaWindow(), attributes);
		} else if ("listhead".equalsIgnoreCase(tagName)) {
			comp = new AryaListHead(main.getAryaWindow(), attributes);
		} else if ("listheader".equalsIgnoreCase(tagName)) {
			comp = new AryaListHeader(main.getAryaWindow(), attributes);
		} else if ("listcell".equalsIgnoreCase(tagName)) {
			comp = new AryaListCell(main.getAryaWindow(), attributes);
		} else if ("grid".equalsIgnoreCase(tagName)) {
			comp = new AryaGrid(main.getAryaWindow(), attributes);
		} else if ("columns".equalsIgnoreCase(tagName)) {
			comp = new AryaColumns(main.getAryaWindow(), attributes);
		} else if ("column".equalsIgnoreCase(tagName)) {
			comp = new AryaColumn(main.getAryaWindow(), attributes);
		} else if ("rows".equalsIgnoreCase(tagName)) {
			comp = new AryaRows(main.getAryaWindow(), attributes);
		} else if ("row".equalsIgnoreCase(tagName)) {
			comp = new AryaRow(main.getAryaWindow(), attributes);
		}
		
		else if ("menubar".equalsIgnoreCase(tagName)) {
			comp = new AryaMenuBar(main.getAryaNavBar(), attributes);
		} else if ("menu".equalsIgnoreCase(tagName)) {
			comp = new AryaMenu(main.getAryaWindow(), attributes);
		}else if ("menupopup".equalsIgnoreCase(tagName)) {
			comp = new AryaMenuPopUp(main.getAryaWindow(), attributes);
		}else if ("menuitem".equalsIgnoreCase(tagName)) {
			comp = new AryaMenuItem(main.getAryaWindow(), attributes);
		}

		return comp;
	}

}
