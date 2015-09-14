package tr.com.agem.arya.interpreter.parser;

import android.content.Context;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Stack;

import tr.com.agem.arya.interpreter.components.AryaComboItem;
import tr.com.agem.arya.interpreter.components.AryaListCell;
import tr.com.agem.arya.interpreter.components.AryaListItem;
import tr.com.agem.arya.interpreter.components.AryaMain;
import tr.com.agem.arya.interpreter.components.AryaMenuItem;
import tr.com.agem.arya.interpreter.components.AryaMultipleComboItem;
import tr.com.agem.arya.interpreter.components.AryaNavBar;
import tr.com.agem.arya.interpreter.components.AryaScript;
import tr.com.agem.arya.interpreter.components.AryaWindow;
import tr.com.agem.arya.interpreter.components.ComponentFactory;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser extends DefaultHandler {

    private Context context = null;
    private AryaMain main=null;
    private Stack<IAryaComponent> currentComponent = null;

    public AryaMetadataParser(Context context, AryaMain main) {
        this.context = context;
        this.main = main;
        this.currentComponent = new Stack<>();
    }

    @Override
    public void startElement (String uri, String localName,String tagName, Attributes attributes){

        IAryaComponent comp = ComponentFactory.getComponent(tagName, context, main.getAryaWindow(), attributes);
        
        if (comp != null) {

            if(comp instanceof AryaComboItem || comp instanceof AryaMultipleComboItem || comp instanceof AryaListItem || comp instanceof AryaListCell|| comp instanceof AryaMenuItem ){
                comp.setComponentParent(currentComponent.peek());
            }
            else {
                if(comp instanceof IAryaMenu)
                    comp.setComponentParent(main.getAryaNavBar());
                else
                    comp.setComponentParent(main.getAryaWindow());
            }

            currentComponent.push(comp);

            if (main.getAryaWindow().getComponents() == null) {
                main.getAryaWindow().setComponents(new ArrayList<IAryaComponent>());
            }

            main.getAryaWindow().getComponents().add(comp);
        }
    }

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

}
