package tr.com.agem.arya.interpreter.script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.Scriptable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.arya.interpreter.base.components.AryaMain;
import tr.com.agem.arya.interpreter.utils.AryaInterpreterHelper;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.property.reader.PropertyReader;

public class ElementFunctions extends AnnotatedScriptableObject {

	private static final long serialVersionUID = 2251889177219110859L;
	private static final Logger logger = Logger.getLogger(ElementFunctions.class.getName());
	
	private Context context;
	private Scriptable scope;
	private AryaMain main;

	public ElementFunctions(Context context, Scriptable scope, AryaMain main) {
		this.context = context;
		this.scope = scope;
		this.main = main;
	}

	@AryaJsFunction
	public void populate(String data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(data);
			if (rootNode != null) {
				Iterator<Entry<String, JsonNode>> fields = rootNode.fields();
				if (fields != null) {
					while (fields.hasNext()) {
						Entry<String, JsonNode> entry = fields.next();
						logger.log(Level.FINE, "JSON property: {0}:{1}", new Object[]{ entry.getKey(), entry.getValue() });
						
						IAryaComponent comp = (IAryaComponent) getElementById(entry.getKey());
						if (comp != null) {
							comp.setComponentValue(entry.getValue().asText());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AryaJsFunction
	public void refresh(String view) {
		// TODO
	}

	@AryaJsFunction
	public void post(String action, String requestType, Object params, NativeFunction onSuccess, NativeFunction onFailure) {

		Object jsonParam = NativeJSON.stringify(context, scope, params, null, null);

		StringBuilder request = new StringBuilder("{ \"params\": ")
				.append(jsonParam)
				.append(", \"requestType\": \"")
				.append(requestType)
				.append("\", \"action\": \"")
				.append(action)
				.append("\" }");
		
		String result = AryaInterpreterHelper.callUrl(PropertyReader.property("gateway.base.url"), request.toString());
		
		logger.log(Level.FINE, "Post result: {0}", result);
		
		
		AryaResponse response = new AryaResponse();
		response.fromXMLString(result);
		
		AryaInterpreterHelper.interpretResponse(response, main);
		//TODO response fail condition add
		
		if (onSuccess != null) {
			scope.put(onSuccess.getFunctionName(), scope, onSuccess);
			Context.call(null, onSuccess, scope, this, new Object[]{ response });
		}
		if (onFailure != null) {
			scope.put(onFailure.getFunctionName(), scope, onFailure);
			Context.call(null, onFailure, scope, this, new Object[]{ response });			
		}
	}

	@AryaJsFunction
	public Object getElementById(String id) { //only on window not menu

		IAryaComponent comp;

		for (int i = 0; i < main.getAryaWindow().getComponents().size(); i++) {
			comp = main.getAryaWindow().getComponents().get(i);

			if (id.equalsIgnoreCase(comp.getComponentId())) {
				return comp;
			}
		}
		return null;
	}

	@AryaJsFunction
	public Object[] getElementsByName(String name) {//only on window not menu

		List<IAryaComponent> objList = new ArrayList<IAryaComponent>();
		IAryaComponent comp;

		for (int i = 0; i < main.getAryaWindow().getComponents().size(); i++) {
			comp = main.getAryaWindow().getComponents().get(i);

			if (name.equalsIgnoreCase(
					comp.getClass().toString().replace("class tr.com.agem.arya.interpreter.component.Arya", ""))) {
				objList.add(comp);
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}

	@AryaJsFunction
	public Object[] getElementsByClass(String className) {//only on window not menu

		List<IAryaComponent> objList = new ArrayList<IAryaComponent>();
		IAryaComponent comp;

		for (int i = 0; i < main.getAryaWindow().getComponents().size(); i++) {
			comp = main.getAryaWindow().getComponents().get(i);

			if (className.equalsIgnoreCase(comp.getComponentClassName())) {
				objList.add(comp);
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}

	@AryaJsFunction
	public String serializeForm() {//only on window not menu
		String strSerialize = "";
		IAryaComponent comp;

		for (int i = 0; i < main.getAryaWindow().getComponents().size(); i++) {
			comp = main.getAryaWindow().getComponents().get(i);
			strSerialize += ",\"" + comp.getComponentId() + "\":"
					+ (comp.getComponentValue() == null ? null : "\"" + comp.getComponentValue() + "\"");
		}

		if (strSerialize.length() > 0)
			return "{" + strSerialize.substring(1, strSerialize.length()) + "}";
		return "{}";
	}
	
}
