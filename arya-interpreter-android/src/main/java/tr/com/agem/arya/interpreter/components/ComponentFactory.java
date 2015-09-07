package tr.com.agem.arya.interpreter.components;

import android.content.Context;

import org.xml.sax.Attributes;
import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

    public static IAryaComponent getComponent(String tagName, Context context, AryaWindow aryaWindow,Attributes attributes) {

        IAryaComponent comp = null;

        if ("label".equalsIgnoreCase(tagName)) {
            comp = new AryaLabel(context, attributes, aryaWindow);
        } else if ("textbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, attributes, aryaWindow);
        } else if ("checkbox".equalsIgnoreCase(tagName)) {
            comp = new AryaCheckbox(context, attributes, aryaWindow);
        } else if ("datebox".equalsIgnoreCase(tagName)) {
            comp = new AryaDatebox(context, attributes, aryaWindow);
        } else if ("button".equalsIgnoreCase(tagName)) {
            comp = new AryaButton(context, attributes, aryaWindow);
        } else if ("intbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, attributes, aryaWindow);
        } else if ("doublebox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, attributes, aryaWindow);
        } else if ("combobox".equalsIgnoreCase(tagName)) {
            comp = new AryaComboBox(context, attributes, aryaWindow);
        }else if ("comboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaComboItem(context, attributes, aryaWindow);
        }else if ("multiplecombobox".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboBox(context, attributes, aryaWindow);
        }else if ("mcomboitem".equalsIgnoreCase(tagName)) {
            comp = new AryaMultipleComboItem(context, attributes, aryaWindow);
        }
        /*else if ("listbox".equalsIgnoreCase(tagName)) {
            comp = new AryaListBox(context, attributes, aryaWindow);
        }*/
        // TODO other components from zkoss

        return comp;
    }

}
