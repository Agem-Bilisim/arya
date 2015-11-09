package tr.com.agem.arya.interpreter.script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.Scriptable;
import org.zkoss.zul.Combobox;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.arya.interpreter.component.AryaCombobox;
import tr.com.agem.arya.interpreter.component.AryaListbox;
import tr.com.agem.arya.interpreter.component.AryaSelectbox;
import tr.com.agem.arya.interpreter.component.AryaTextbox;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.utils.AryaException;
import tr.com.agem.arya.interpreter.utils.AryaInterpreterHelper;
import tr.com.agem.arya.interpreter.utils.BaseController;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.property.reader.PropertyReader;

public class ElementFunctions extends AnnotatedScriptableObject {

	private static final long serialVersionUID = 2251889177219110859L;
	private static final Logger logger = Logger.getLogger(ElementFunctions.class.getName());
	
	private Context context;
	private Scriptable scope;
	private AryaMain main;
	
	private static String lastPage;
	private static String reqType;
	
	private static NativeArray comps;
	private static ArrayList<String> values;

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
	public void post(String action, String requestType, Object params, String tabValue, NativeFunction onSuccess, NativeFunction onFailure) {//debug dan geçirmek gerek(volkan)

		Object jsonParam = NativeJSON.stringify(context, scope, params, null, null);

		StringBuilder request = new StringBuilder("{ \"params\": ")
				.append(jsonParam)
				.append(", \"requestType\": \"")
				.append(requestType)
				.append("\", \"action\": \"")
				.append(action)
				.append("\" }");
		
		lastPage = action;
		reqType = requestType;
		
		String result=null;
		AryaResponse response=null;
		try {
			result = AryaInterpreterHelper.callUrl(PropertyReader.property("gateway.base.url"), request.toString());
			
			logger.log(Level.FINE, "Post result: {0}", result);
			
			
			response = new AryaResponse();
			response.fromXMLString(result);
			
			AryaInterpreterHelper.interpretResponse(response, main, BaseController.getTabs(), BaseController.getTabpanels(), tabValue);
			
			if (onSuccess != null) {
				scope.put(onSuccess.getFunctionName(), scope, onSuccess);
				Context.call(null, onSuccess, scope, this, new Object[]{ response });
			}
			
		} catch (AryaException e) {
			if (onFailure != null) {
				scope.put(onFailure.getFunctionName(), scope, onFailure);
				Context.call(null, onFailure, scope, this, new Object[]{ response });			
			}
		}
		
	}
	
	@AryaJsFunction
	public void renderSelectedItem (String id, NativeArray comps, NativeArray values) {
		
		//TODO id listbox olmayabilir???!??!?
		JSONObject jsonObj = null;
		
		if(getElementById(id) instanceof AryaListbox) {
			
			jsonObj = ((AryaListbox)getElementById(id)).getSelectedItem().getValue();
		}		
		else if(getElementById(id) instanceof AryaCombobox) {
			
			jsonObj = ((AryaCombobox)getElementById(id)).getSelectedItem().getValue();
		}
		
		for (int i = 0; i < comps.size(); i++) {
			
			String comp = (String) comps.get(i);
			((IAryaComponent)getElementById(comp)).setComponentValue(jsonObj.get((String)values.get(i)).toString());
		}	
	}
	
	@AryaJsFunction
	public void setParams (NativeArray values) {
		
		ArrayList<String> temp = new ArrayList<String>();
		
		for (int i = 0; i < values.size(); i++) {
			
			temp.add(((AryaTextbox)getElementById((String) values.get(i))).getText());
		}		
		setValues(temp);
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
	
	public static String getLastPage() {

		return lastPage;
	}

	public static void setLastPage(String lastPage) {

		ElementFunctions.lastPage = lastPage;
	}

	public static String getReqType() {

		return reqType;
	}

	public static void setReqType(String reqType) {

		ElementFunctions.reqType = reqType;
	}

	@AryaJsFunction
	public static NativeArray getComps() {
		return comps;
	}

	@AryaJsFunction
	public static void setComps(NativeArray comps) {
		ElementFunctions.comps = comps;
	}

	public static ArrayList<String> getValues() {
		return values;
	}

	public static void setValues(ArrayList<String> values) {
		ElementFunctions.values = values;
	}

	
		
}
