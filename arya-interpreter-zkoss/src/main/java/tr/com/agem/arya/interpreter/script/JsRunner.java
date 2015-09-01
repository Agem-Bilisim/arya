package tr.com.agem.arya.interpreter.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

import tr.com.agem.arya.interpreter.zkoss.AryaWindow;

public class JsRunner {

	public static Object jsRun(List<String> srcList, String script, AryaWindow window) {
		try {
			Context context = ContextFactory.getGlobal().enterContext();
			context.setOptimizationLevel(-1);

			Scriptable scope = new Global();
			((Global) scope).init(context);

			ElementFunctions e = new ElementFunctions(context, scope, window);
			e.addToScope(scope);

			if (null != srcList)
				script = getSourceScript(srcList) + " " + script;

			return context.evaluateString(scope, script, "<rule>", 1, null);

		} finally {
			Context.exit();
		}

	}

	// TODO srcList may not always contains http URLs!
	private static String getSourceScript(List<String> srcList) {
		URL url;
		StringBuilder extendedScript = new StringBuilder(" ");

		for (String strUrl : srcList) {

			try {
				url = new URL(strUrl);
				URLConnection conn = url.openConnection();

				BufferedReader bufferReader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;

				while ((inputLine = bufferReader.readLine()) != null) {
					extendedScript.append(inputLine);
				}
				bufferReader.close();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return extendedScript.append(" ").toString();
	}

}
