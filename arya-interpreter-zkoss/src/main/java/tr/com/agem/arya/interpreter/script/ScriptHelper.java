package tr.com.agem.arya.interpreter.script;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class ScriptHelper {
	
	private static final Logger logger = Logger.getLogger(ScriptHelper.class.getName());

	public static Object executeScript(String functionName, HashMap<Object, Object> params, AryaWindow aryaWindow) {

		StringBuilder script = new StringBuilder();
		AryaScript scriptObj = getScriptComponent(aryaWindow);

		if (AryaUtils.isNotEmpty(scriptObj)) {
			script.append(scriptObj.getScript()).append(getFunctionName(functionName));
			logger.log(Level.FINE, "Script: {0}", script);
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
				logger.log(Level.FINE, "AryaScript instance found.");
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
