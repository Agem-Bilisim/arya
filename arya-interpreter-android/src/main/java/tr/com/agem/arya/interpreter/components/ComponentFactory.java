package tr.com.agem.arya.interpreter.components;

import android.content.Context;

import org.xml.sax.Attributes;

import tr.com.agem.arya.interpreter.main.components.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

    public static IAryaComponent getComponent(String tagName, Context context, AryaWindow window,Attributes attributes) {

        IAryaComponent comp = null;

        if ("label".equalsIgnoreCase(tagName)) {
            comp = new AryaLabel(context, attributes, window);
        } else if (("textbox").equalsIgnoreCase(tagName)|| "intbox".equalsIgnoreCase(tagName)||"doublebox".equalsIgnoreCase(tagName)||"longbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, attributes, window,tagName);
        }else if ("checkbox".equalsIgnoreCase(tagName)) {
            comp = new AryaCheckbox(context, attributes, window);
        } else if ("datebox".equalsIgnoreCase(tagName)) {
            comp = new AryaDatebox(context, attributes, window);
        } else if ("button".equalsIgnoreCase(tagName)) {
            comp = new AryaButton(context, attributes, window);
        } else if ("combobox".equalsIgnoreCase(tagName)) {
            comp = new AryaComboBox(context, attributes, window);
        }else if ("comboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaComboItem(context, attributes, window);
        }else if ("multiplecombobox".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboBox(context, attributes, window);
        }else if ("mcomboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboItem(context, attributes, window);
        }else if ("listbox".equalsIgnoreCase(tagName)) {
            comp = new AryaListBox(context, attributes, window);
        }else if ("listhead".equalsIgnoreCase(tagName)) { // maybe create head class for self properties TODO sıraya göre değil direk head olmalı çizerken(volkan)
            comp = new AryaListItem(context, attributes, window);
        }else if ("listheader".equalsIgnoreCase(tagName)||"listcell".equalsIgnoreCase(tagName)) {
            comp = new AryaListCell(context, attributes, window,tagName);
        }else if ("listitem".equalsIgnoreCase(tagName)) {
            comp = new AryaListItem(context, attributes, window);
        }else if ("script".equalsIgnoreCase(tagName)) {
            comp = new AryaScript(context, attributes,window);
        } else if ("menubar".equalsIgnoreCase(tagName)) {
            comp = new AryaMenuBar(context, attributes, window);
        }else if ("menu".equalsIgnoreCase(tagName)) {
            comp = new AryaMenuItem(context, attributes,window);
        }

        return comp;
    }


}
