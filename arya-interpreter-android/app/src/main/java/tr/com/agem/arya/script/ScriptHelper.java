package tr.com.agem.arya.script;

import java.util.HashMap;

import tr.com.agem.arya.interpreter.components.IAryaComponent;

/**
 * Created by emre on 18/08/15.
 */
public class ScriptHelper {

    public static Object executeScript(IAryaComponent component, String script) {
        return executeScript(component, script, null);
    }


    public static Object executeScript(IAryaComponent component, String script, HashMap<Object, Object> params) {
        // TODO impl
        return null;
    }
}
