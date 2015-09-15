package tr.com.agem.arya.interpreter.components;


import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.main.components.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

    public static IAryaComponent getComponent(String tagName,AryaWindow window,Attributes attributes) {

        IAryaComponent comp = null;

        if ("label".equalsIgnoreCase(tagName)) {
            comp = new AryaLabel(attributes, window);
        } else if (("textbox").equalsIgnoreCase(tagName)|| "intbox".equalsIgnoreCase(tagName)||"doublebox".equalsIgnoreCase(tagName)||"longbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(attributes, window,tagName);
        }else if ("checkbox".equalsIgnoreCase(tagName)) {
            comp = new AryaCheckbox(attributes, window);
        } else if ("datebox".equalsIgnoreCase(tagName)) {
            comp = new AryaDatebox(attributes, window);
        } else if ("button".equalsIgnoreCase(tagName)) {
            comp = new AryaButton(attributes, window);
        } else if ("combobox".equalsIgnoreCase(tagName)) {
            comp = new AryaComboBox(attributes, window);
        }else if ("comboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaComboItem(attributes, window);
        }else if ("multiplecombobox".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboBox(attributes, window);
        }else if ("mcomboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboItem(attributes, window);
        }else if ("listbox".equalsIgnoreCase(tagName)) {
            comp = new AryaListBox(attributes, window);
        }else if ("listhead".equalsIgnoreCase(tagName)) { // maybe create head class for self properties TODO sıraya göre değil direk head olmalı çizerken(volkan)
            comp = new AryaListItem(attributes, window);
        }else if ("listheader".equalsIgnoreCase(tagName)||"listcell".equalsIgnoreCase(tagName)) {
            comp = new AryaListCell(attributes, window,tagName);
        }else if ("listitem".equalsIgnoreCase(tagName)) {
            comp = new AryaListItem(attributes, window);
        }else if ("script".equalsIgnoreCase(tagName)) {
            comp = new AryaScript(attributes,window);
        } else if ("menubar".equalsIgnoreCase(tagName)) {
            comp = new AryaMenuBar(attributes, window);
        }else if ("menu".equalsIgnoreCase(tagName)) {
            comp = new AryaMenuItem(attributes,window);
        }

        return comp;
    }

}
