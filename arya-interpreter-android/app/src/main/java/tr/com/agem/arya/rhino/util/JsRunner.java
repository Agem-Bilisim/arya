package tr.com.agem.arya.rhino.util;

import android.util.Log;
import android.widget.LinearLayout;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

import tr.com.agem.arya.rhino.functions.ElementFunctions;

/**
 * Created by volkan on 19.08.2015.
 */
public class JsRunner {

    public static void jsRun(String script,LinearLayout window) {

        try {

            Context context = Context.enter();
            context.setOptimizationLevel(-1);

            Scriptable scope = new org.mozilla.javascript.tools.shell.Global();
            ((Global) scope).init(context);

            ElementFunctions e = new ElementFunctions(window);
             e.addToScope(scope);


            Log.d("script", script);


            Object result = context.evaluateString(scope, script, "<rule>", 1, null);


            Log.d("--","----------------------------------------------------------");
            //Log.d("Rhino",result.toString());
           // Log.d("Rhino",Context.toString(result));
            Log.d("--","----------------------------------------------------------");


        } finally {
            Context.exit();
        }

    }
}
