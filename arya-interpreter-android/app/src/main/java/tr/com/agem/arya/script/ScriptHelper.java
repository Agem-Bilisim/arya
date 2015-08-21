package tr.com.agem.arya.script;

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
        //TODO rhino time
        StringBuilder script = new StringBuilder();
        script.append(AryaScript.script).append(functionName).append("();");
        jsRun(script.toString(),window);
        return null;
    }



}
