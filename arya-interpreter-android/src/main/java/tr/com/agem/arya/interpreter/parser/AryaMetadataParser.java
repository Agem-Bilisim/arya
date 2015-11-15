package tr.com.agem.arya.interpreter.parser;

import android.view.View;
import android.view.ViewGroup;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Stack;


import tr.com.agem.arya.interpreter.components.base.AryaMain;

import tr.com.agem.arya.interpreter.components.menu.AryaMenu;
import tr.com.agem.arya.interpreter.components.menu.AryaMenuItem;
import tr.com.agem.arya.interpreter.components.AryaScript;
import tr.com.agem.arya.interpreter.components.ComponentFactory;
import tr.com.agem.arya.interpreter.components.menu.AryaPopupMenu;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaMetadataParser extends DefaultHandler {

    private AryaMain main=null;
    private Stack<IAryaComponent> currentComponent = null;

    public AryaMetadataParser(AryaMain main) {
        this.main = main;
        this.currentComponent = new Stack<>();
    }

    @Override
    public void startElement (String uri, String localName,String tagName, Attributes attributes){

        IAryaComponent comp = ComponentFactory.getComponent(tagName,main, attributes);

        if (comp != null) {

            if(currentComponent.size() > 0 ){
                if((!(comp instanceof IAryaMenu))){
                    if( ((View)comp).getParent()!=null) { //have to check whether it is instanceof AryaMenuItem or not because MenuItems cannot be casted to View
                        ((ViewGroup) ((View) comp).getParent()).removeView((View) comp);
                    }
                    comp.setComponentParent(currentComponent.peek());}
                else{
                    if(comp instanceof AryaPopupMenu){
                        ((AryaPopupMenu) comp).setLabel(((AryaMenu)currentComponent.peek()).getLabel());
                        comp.setComponentParent(main.getAryaNavBar().getMenuBar());
                    }
                    else if(comp instanceof AryaMenuItem){
                        if(currentComponent.peek() instanceof AryaPopupMenu){
                            ((AryaPopupMenu)currentComponent.peek()).choice.add(((AryaMenuItem) comp).getLabel());
                            ((AryaPopupMenu)currentComponent.peek()).menuItems.add(((AryaMenuItem) comp));
                        }
                        else {
                            comp.setComponentParent(main.getAryaNavBar().getMenuBar());
                        }
                    }
                    else if(comp instanceof AryaMenu){
                        if(((AryaMenu) comp).getLabel()!=null){
                            comp.setComponentParent(main.getAryaNavBar().getMenuBar());
                        }
                    }
                }
            }
            else {      //this part assigns MenuBar's parent.
                if (comp instanceof IAryaMenu) {
                    comp.setComponentParent(main.getAryaNavBar());
                }
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
