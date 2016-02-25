package tr.comagem.arya.jsengine;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

import tr.comagem.arya.element.ElementFunctions;

public class JsEngineUtil {

	private void jsRun(String script) {

		try {

			Context context = ContextFactory.getGlobal().enterContext();

			Scriptable scope = new org.mozilla.javascript.tools.shell.Global();
            ((Global) scope).init(context);

            ElementFunctions e = new ElementFunctions();
            e.addToScope(scope);
             
            @SuppressWarnings("unused")
			Object result = context.evaluateString(scope, script, "<rule>", 1, null);

		} finally {
			Context.exit();
		}
	}
}
