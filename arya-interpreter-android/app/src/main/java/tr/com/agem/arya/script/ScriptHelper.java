package tr.com.agem.arya.script;

import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import tr.com.agem.arya.interpreter.components.AryaScript;
import tr.com.agem.arya.interpreter.components.IAryaComponent;

import static tr.com.agem.arya.rhino.util.JsRunner.jsRun;

/**
 * Created by emre on 18/08/15.
 */
public class ScriptHelper {

    public static Object executeScript(IAryaComponent component, String functionName) {
        return executeScript(component, functionName, null,null);
    }


    public static Object executeScript(IAryaComponent component, String functionName, HashMap<Object, Object> params,LinearLayout window) {

        StringBuilder script = new StringBuilder();

        AryaScript scriptObj = getScript(window);
        if(scriptObj!=null){
            script.append(scriptObj.getScript()).append(functionName).append("();");
            return jsRun(scriptObj.getSrcList(),script.toString(),window);
        }

        return null;
    }

    private static AryaScript getScript(LinearLayout window) {

        View child=null;
        for (int i = 0 ; i<window.getChildCount();i++){

            child = window.getChildAt(i);
            if(child instanceof AryaScript) {
                return  (AryaScript) child;
            }
        }
        return null;
    }


}
