package tr.comagem.arya.jsengine;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

import tr.comagem.arya.element.ElementFunctions;

public class JsEngineUtil {

	public static void main(String[] args) {

		JsEngineUtil r = new JsEngineUtil();
		r.vol();
	}

	private void vol() {

		ElementFunctions data = new ElementFunctions().addElement("61", "volkan", "12A").addElement("1", "volkan", "12A");

		try {

			Context context = Context.enter();

			// ScriptableObject scope = context.initStandardObjects();
			Scriptable scope = new org.mozilla.javascript.tools.shell.Global();
			((Global) scope).init(context);

			data.addToScope(scope);
			// for (AnnotatedScriptableObject object : objects) {
			// object.addToScope(scope);
			// }

			String script = "print('print smt')";
			// String script = "getElementById('61')";
			// String script = "getElementsByName('volkan')";
			// String script = "getElementsByClass('12A')";

			Object result = context.evaluateString(scope, script, "<rule>", 1, null);

			System.out.println(result);

		} finally {
			Context.exit();
		}

	}

}
