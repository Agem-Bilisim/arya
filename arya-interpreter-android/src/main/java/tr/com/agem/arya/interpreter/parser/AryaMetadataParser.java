package tr.com.agem.arya.interpreter.parser;

import android.content.Context;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Stack;

import tr.com.agem.arya.interpreter.components.AryaComboItem;
import tr.com.agem.arya.interpreter.components.AryaMultipleComboItem;
import tr.com.agem.arya.interpreter.components.AryaScript;
import tr.com.agem.arya.interpreter.components.AryaWindow;
import tr.com.agem.arya.interpreter.components.ComponentFactory;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser extends DefaultHandler {

    private Context context = null;
    private AryaWindow aryaWindow = null;
    private Stack<IAryaComponent> currentComponent = null;

    public AryaMetadataParser(Context context, AryaWindow aryaWindow) {
        this.context = context;
        this.aryaWindow = aryaWindow;
        this.currentComponent = new Stack<IAryaComponent>();
    }

    @Override
    public void startElement (String uri, String localName,String tagName, Attributes attributes){

        IAryaComponent comp = ComponentFactory.getComponent(tagName, context, aryaWindow, attributes);
        if (comp != null) {



            if(comp instanceof AryaComboItem || comp instanceof AryaMultipleComboItem){
                comp.setComponentParent(currentComponent.peek());
            }
            else {
                comp.setComponentParent(aryaWindow);
            }

            // Update current component pointer
            currentComponent.push(comp);

            // Add new component to the component list of parent window
            if (aryaWindow.getComponents() == null) {
                aryaWindow.setComponents(new ArrayList<IAryaComponent>());
            }
            aryaWindow.getComponents().add(comp);
        }
    }

    /*public void startElement(String tagName, XmlPullParser parser) {
        IAryaComponent comp = ComponentFactory.getComponent(tagName, context, aryaWindow, parser);
        if (comp != null) {

            Log.d("-------",comp.getClass().toString());

            if(comp instanceof AryaComboItem){
                Log.d("item parent "+((AryaComboItem) comp).getId(),currentComponent.peek().toString());
                comp.setComponentParent(currentComponent.peek());
            }
            else {
            comp.setComponentParent(aryaWindow);
            }

            // Update current component pointer
            currentComponent.push(comp);

            // Add new component to the component list of parent window
            if (aryaWindow.getComponents() == null) {
                aryaWindow.setComponents(new ArrayList<IAryaComponent>());
            }
            aryaWindow.getComponents().add(comp);
        }
    }*/

    @Override
    public void endElement (String uri, String localName, String qName){
        if (!currentComponent.isEmpty()) {
            currentComponent.pop();
        }
    }

    @Override
    public void characters (char ch[], int start, int length){
        if (!currentComponent.isEmpty() && currentComponent.peek() instanceof AryaScript) {
            String scriptPart = new String(ch, start, length);
            AryaScript scriptObj = (AryaScript) currentComponent.peek();
            scriptObj.setScript(scriptObj.getScript() != null ? scriptObj.getScript() + scriptPart : scriptPart);
        }
    }


    /*public void characters(String text) {
        if (!currentComponent.isEmpty() && currentComponent.peek() instanceof AryaScript) {
            String scriptPart = new String(text);
            AryaScript scriptObj = (AryaScript) currentComponent.peek();
            scriptObj.setScript(scriptObj.getScript() != null ? scriptObj.getScript() + scriptPart : scriptPart);
        }
    }*/

}
