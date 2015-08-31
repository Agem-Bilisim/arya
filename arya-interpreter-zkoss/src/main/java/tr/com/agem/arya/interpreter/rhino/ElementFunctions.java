package tr.com.agem.arya.interpreter.rhino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.mozilla.javascript.NativeJSON;

import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.arya.interpreter.zkoss.AryaInterpreterHelper;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;

public class ElementFunctions extends AnnotatedScriptableObject {

	private static final long serialVersionUID = 2251889177219110859L;
	private AryaWindow window;

	public ElementFunctions(AryaWindow window) {
		this.window = window;
	}

	@SuppressWarnings({"unchecked" })
	@AryaJsFunction
	public void populate(String jsonStr){
		
		try {
			HashMap<String,Object> result = new ObjectMapper().readValue(jsonStr, HashMap.class);
			mapToString(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AryaJsFunction
	public void post(String action, String requestType, Object params) {

		Object jsonParam = NativeJSON.stringify(JsRunner.context, JsRunner.scope, params, null, null);
		

		StringBuilder request = new StringBuilder("{ \"params\": ")
				.append(jsonParam)
				.append(", \"requestType\": \"")
				.append(requestType)
				.append("\", \"action\": \"")
				.append(action)
				.append("\" }");
		System.out.println("FUNC: " + request.toString());
		String result = AryaInterpreterHelper.callUrl("http://192.168.1.106:8080/arya/rest/asya/", request.toString() );
		
		System.out.println("\n"+result);
		
		populate(result.replace("<arya-response><view/><data><![CDATA[{\"list\":[", "").replace("]]></data></arya-response>", ""));
		// TODO 
	}

	@AryaJsFunction
	public Object getElementById(String id) {

		IAryaComponentProperty comp;

		for (int i = 0; i < window.getComponents().size(); i++) {
			comp = window.getComponents().get(i);

			if (id.equalsIgnoreCase(comp.getComponentId())) {
				return comp;
			}
		}
		return null;
	}

	@AryaJsFunction
	public Object[] getElementsByName(String name) {

		List<IAryaComponentProperty> objList = new ArrayList<IAryaComponentProperty>();
		IAryaComponentProperty comp;

		for (int i = 0; i < window.getComponents().size(); i++) {
			comp = window.getComponents().get(i);

			if (name.equalsIgnoreCase(
					comp.getClass().toString().replace("class tr.com.agem.arya.interpreter.component.Arya", ""))) {
				objList.add(comp);
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}

	@AryaJsFunction
	public Object[] getElementsByClass(String className) {

		List<IAryaComponentProperty> objList = new ArrayList<IAryaComponentProperty>();
		IAryaComponentProperty comp;

		for (int i = 0; i < window.getComponents().size(); i++) {
			comp = window.getComponents().get(i);

			if (className.equalsIgnoreCase(comp.getComponentClassName())) {
				objList.add(comp);
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}

	@AryaJsFunction
	public String serializeForm() {

		String strSerialize = "";
		IAryaComponentProperty comp;

		for (int i = 0; i < window.getComponents().size(); i++) {
			comp = window.getComponents().get(i);
			strSerialize += ",\"" + comp.getComponentId() + "\":"
					+ (comp.getComponentValue() == null ? null : "\"" + comp.getComponentValue() + "\"");
		}

		if (strSerialize.length() > 0)
			return "{" + strSerialize.substring(1, strSerialize.length()) + "}";
		return "{}";
	}
	
	@SuppressWarnings("rawtypes")
	private void mapToString(HashMap<String, Object> result) {
		 Iterator it = result.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
	}

	
}
