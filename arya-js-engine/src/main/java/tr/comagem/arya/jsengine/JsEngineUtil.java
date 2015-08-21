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

//			 ScriptableObject scope = context.initStandardObjects();
			Scriptable scope = new org.mozilla.javascript.tools.shell.Global(); 	//global scope
			((Global) scope).init(context);

			data.addToScope(scope);
			// for (AnnotatedScriptableObject object : objects) {
			// object.addToScope(scope);
			// }

			
//			Scriptable newScope = context.newObject(scope); //shared scope
//			newScope.setPrototype(scope);
//			newScope.setParentScope(null);
			
//			String script = "print('arya')";
			 String script = "a = function() {test('tt');}; b = function() { print('b');};b();";
			// String script = "getElementsByName('volkan')";
			// String script = "getElementsByClass('12A')";

			Object result = context.evaluateString(scope, script, "<rule>", 1, null);
//			Object result = context.evaluateString(newScope, script, "<rule>", 1, null); //shared scope

			scope.put(61, scope, "obj");			//scope a 61 id li herhangi bir nesne koyma 
			Object a = scope.get(61, scope);		//ve alma
			
			System.out.println(result+""+a);

		} finally {
			Context.exit();
		}

	}

}
