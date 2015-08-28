package tr.com.agem.arya.rhino.functions;

import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tr.com.agem.arya.interpreter.components.IAryaComponent;
import tr.com.agem.arya.rhino.util.AnnotatedScriptableObject;

/**
 * Created by volkan on 19.08.2015.
 */
public class ElementFunctions extends AnnotatedScriptableObject {


    private LinearLayout window;

    public ElementFunctions(LinearLayout window) {
        this.window = window;
    }

    @AryaJsFunction
    public Object getElementById(String id) {

        View child=null;
        for (int i = 0 ; i<window.getChildCount();i++){

            child = window.getChildAt(i);
            if(child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;

                if(id.equals(o.getComponentId())){
                    return o;
                }
            }
        }
        return null;
    }

    @AryaJsFunction
    public Object[] getElementsByName(String name) {
        List objList = new ArrayList();
        View child=null;
        for (int i = 0 ; i<window.getChildCount();i++){

            child = window.getChildAt(i);
            if(child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;
                    if(name.equalsIgnoreCase(o.getClass().toString().replace("class tr.com.agem.arya.interpreter.components.Arya", ""))) {
                        objList.add(o);
                    }
            }
        }
        return objList.toArray(new Object[objList.size()]);
    }


    @AryaJsFunction
    public Object[] getElementsByClass(String className) {
        List objList = new ArrayList();
        View child=null;
        for (int i = 0 ; i<window.getChildCount();i++){

            child = window.getChildAt(i);
            if(child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;
                if(className.equalsIgnoreCase(o.getComponentClassName())) {
                    objList.add(o);
                }
            }
        }
        return objList.toArray(new Object[objList.size()]);
    }

    @AryaJsFunction
    public String serializeForm() throws IOException {

        String strSerialize="";
        View child=null;
        for (int i = 0 ; i<window.getChildCount();i++){
            child = window.getChildAt(i);
            if(child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;
                strSerialize+=",\""+o.getComponentId()+"\":"+(o.getComponentValue()==null?null:"\""+o.getComponentValue()+"\"");
            }
        }

        if(strSerialize.length()>0)
            return "{"+strSerialize.substring(1,strSerialize.length())+"}";
        return "{}";
    }
}
