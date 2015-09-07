package tr.com.agem.arya.interpreter.parser;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Stack;

import tr.com.agem.arya.interpreter.components.AryaScript;
import tr.com.agem.arya.interpreter.components.AryaWindow;
import tr.com.agem.arya.interpreter.components.ComponentFactory;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser {

    private Context context = null;
    private AryaWindow aryaWindow = null;
    private Stack<IAryaComponent> currentComponent = null;

    public AryaMetadataParser(Context context, AryaWindow aryaWindow) {
        this.context = context;
        this.aryaWindow = aryaWindow;
        this.currentComponent = new Stack<IAryaComponent>();
    }

    public void startElement(String tagName, XmlPullParser parser) {
        IAryaComponent comp = ComponentFactory.getComponent(tagName, context, aryaWindow, parser);
        if (comp != null) {

            // If the component is a ComboItem (or ListItem) instance,
            // then set its parent to the current component which is a ComboBox
            // (or ListBox) instance.
            /*if (comp instanceof AryaComboItem || comp instanceof AryaMultiComboItem || comp instanceof AryaListItem
                    || comp instanceof AryaListCell || comp instanceof AryaListHeader || comp instanceof AryaListHead) {
                comp.setComponentParent(currentComponent.peek());
            }*/
            // For other components,
            // Set their parent to the component container which is a Div
            // instance.
            /*else {*/
            comp.setComponentParent(aryaWindow);
            /*}*/

            // Update current component pointer
            currentComponent.push(comp);

            // Add new component to the component list of parent window
            if (aryaWindow.getComponents() == null) {
                aryaWindow.setComponents(new ArrayList<IAryaComponent>());
            }
            aryaWindow.getComponents().add(comp);
        }
    }

    public void endElement(String tagName) {
        if (!currentComponent.isEmpty()) {
            currentComponent.pop();
        }
    }

    public void characters(String text) {
        if (!currentComponent.isEmpty() && currentComponent.peek() instanceof AryaScript) {
            String scriptPart = new String(text);
            AryaScript scriptObj = (AryaScript) currentComponent.peek();
            scriptObj.setScript(scriptObj.getScript() != null ? scriptObj.getScript() + scriptPart : scriptPart);
        }
    }

}
