package tr.com.agem.arya.interpreter.script;

import java.util.HashMap;

import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class ScriptHelper {

	public static Object executeScript(String functionName, HashMap<Object, Object> params, AryaWindow aryaWindow) {

		StringBuilder script = new StringBuilder();
		AryaScript scriptObj = getScriptComponent(aryaWindow);

		if (AryaUtils.isNotEmpty(scriptObj)) {
			script.append(scriptObj.getScript()).append(getFunctionName(functionName));
			return JsRunner.jsRun(scriptObj.getSrcList(), script.toString(), aryaWindow);
		}

		return null;
	}

	private static AryaScript getScriptComponent(AryaWindow aryaWindow) {
		IAryaComponent comp;
		for (int i = 0; i < aryaWindow.getComponents().size(); i++) {
			comp = aryaWindow.getComponents().get(i);
			if (comp instanceof AryaScript) {
				AryaScript scriptObj = (AryaScript) comp;
				return scriptObj;
			}
		}
		return null;
	}

	private static StringBuilder getFunctionName(String functionName) {
		StringBuilder fName = new StringBuilder(functionName);
		if (!functionName.endsWith(")")) {
			fName.append("()");
		}
		return fName.append(";");
	}

}
