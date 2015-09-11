package tr.com.agem.arya.interpreter.components;

import android.content.Context;

import org.xml.sax.Attributes;

import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

    public static IAryaComponent getComponent(String tagName, Context context, AryaWindow aryaWindow,Attributes attributes) {

        IAryaComponent comp = null;

        if ("label".equalsIgnoreCase(tagName)) {
            comp = new AryaLabel(context, attributes, aryaWindow);
        } else if (("textbox").equalsIgnoreCase(tagName)|| "intbox".equalsIgnoreCase(tagName)||"doublebox".equalsIgnoreCase(tagName)||"longbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, attributes, aryaWindow,tagName);
        }else if ("checkbox".equalsIgnoreCase(tagName)) {
            comp = new AryaCheckbox(context, attributes, aryaWindow);
        } else if ("datebox".equalsIgnoreCase(tagName)) {
            comp = new AryaDatebox(context, attributes, aryaWindow);
        } else if ("button".equalsIgnoreCase(tagName)) {
            comp = new AryaButton(context, attributes, aryaWindow);
        } else if ("combobox".equalsIgnoreCase(tagName)) {
            comp = new AryaComboBox(context, attributes, aryaWindow);
        }else if ("comboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaComboItem(context, attributes, aryaWindow);
        }else if ("multiplecombobox".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboBox(context, attributes, aryaWindow);
        }else if ("mcomboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboItem(context, attributes, aryaWindow);
        }else if ("listbox".equalsIgnoreCase(tagName)) {
            comp = new AryaListBox(context, attributes, aryaWindow);
        }else if ("listhead".equalsIgnoreCase(tagName)) { // maybe create head class for self properties TODO sıraya göre değil direk head olmalı çizerken(volkan)
            comp = new AryaListItem(context, attributes, aryaWindow);
        }else if ("listheader".equalsIgnoreCase(tagName)||"listcell".equalsIgnoreCase(tagName)) {
            comp = new AryaListCell(context, attributes, aryaWindow,tagName);
        }else if ("listitem".equalsIgnoreCase(tagName)) {
            comp = new AryaListItem(context, attributes, aryaWindow);
        }else if ("script".equalsIgnoreCase(tagName)) {
            comp = new AryaScript(context, attributes,aryaWindow);
        }
        // TODO other components from zkoss

        return comp;
    }

}
