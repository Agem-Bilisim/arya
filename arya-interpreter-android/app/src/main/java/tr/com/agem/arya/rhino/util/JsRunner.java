package tr.com.agem.arya.rhino.util;

import android.os.StrictMode;
import android.util.Log;
import android.widget.LinearLayout;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import tr.com.agem.arya.rhino.functions.ElementFunctions;

/**
 * Created by volkan on 19.08.2015.
 */
public class JsRunner {

    public static void jsRun(List<String> srcList, String script, LinearLayout window) {

        try {

            Context context = ContextFactory.getGlobal().enterContext();//Context.enter();
            context.setOptimizationLevel(-1);

            Scriptable scope = new org.mozilla.javascript.tools.shell.Global();
            ((Global) scope).init(context);

            ElementFunctions e = new ElementFunctions(window);
             e.addToScope(scope);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //TODO incele
            StrictMode.setThreadPolicy(policy);

            if(null!=srcList) 
                script=getSourceScript(srcList)+" "+script;

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

    private static String getSourceScript(List<String> srcList) {
        URL url;
        String extendedScript=" ";

        for (String strUrl:srcList) {

            try {
                url = new URL(strUrl);
                URLConnection conn = url.openConnection();

                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;

                while ((inputLine = bufferReader.readLine()) != null) {
                    extendedScript+=inputLine;
                }
                bufferReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extendedScript+" ";
    }
}
