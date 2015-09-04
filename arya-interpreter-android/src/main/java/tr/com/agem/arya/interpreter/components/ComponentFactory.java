package tr.com.agem.arya.interpreter.components;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;

import tr.com.agem.core.interpreter.IAryaComponent;

public class ComponentFactory {

    public static IAryaComponent getComponent(String tagName, Context context, AryaWindow aryaWindow, XmlPullParser parser) {

        IAryaComponent comp = null;

        if ("label".equalsIgnoreCase(tagName)) {
            comp = new AryaLabel(context, parser, aryaWindow);
        } else if ("textbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, parser, aryaWindow);
        } else if ("checkbox".equalsIgnoreCase(tagName)) {
            comp = new AryaCheckbox(context, parser, aryaWindow);
        } else if ("datebox".equalsIgnoreCase(tagName)) {
            comp = new AryaDatebox(context, parser, aryaWindow);
        } else if ("button".equalsIgnoreCase(tagName)) {
            comp = new AryaButton(context, parser, aryaWindow);
        } else if ("intbox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, parser, aryaWindow);
        } else if ("doublebox".equalsIgnoreCase(tagName)) {
            comp = new AryaTextbox(context, parser, aryaWindow);
        } else if ("combobox".equalsIgnoreCase(tagName)) {
            comp = new AryaComboBox(context, parser, aryaWindow);
        }
        // TODO other components from zkoss

        return comp;
    }

}
