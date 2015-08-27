package tr.com.agem.arya.interpreter.script;

import java.util.HashMap;

import tr.com.agem.arya.interpreter.component.AryaScript;
import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.arya.interpreter.rhino.JsRunner;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.utils.AryaUtils;

public class ScriptHelper {

	public static Object executeScript(String functionName, HashMap<Object, Object> params, AryaWindow aryaWindow) {

		StringBuilder script = new StringBuilder();
		AryaScript scriptObj = getScriptComponent(aryaWindow);
		
		if(AryaUtils.isNotEmpty(scriptObj)){
			script.append(scriptObj.getScript()).append(functionName).append("();");
			return JsRunner.jsRun(scriptObj.getSrcList(), script.toString(), aryaWindow);
		}
		return null;
	}

	private static AryaScript getScriptComponent(AryaWindow aryaWindow) {
		
		IAryaComponentProperty comp;
		
		for(int i=0; i<aryaWindow.getComponents().size();i++){
			comp = aryaWindow.getComponents().get(i);
			if("script".equalsIgnoreCase(comp.getClass().toString().replace("class tr.com.agem.arya.interpreter.component.Arya", ""))){
				if(comp instanceof AryaScript){
					AryaScript scriptObj=(AryaScript)comp;
					return scriptObj;
				}
			}
		}
		return null;
	}

}
