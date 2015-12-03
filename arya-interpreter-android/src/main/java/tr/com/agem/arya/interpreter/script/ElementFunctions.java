package tr.com.agem.arya.interpreter.script;

import android.view.View;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.Scriptable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.arya.MainActivity;
import tr.com.agem.arya.gateway.AryaInterpreterHelper;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ElementFunctions extends AnnotatedScriptableObject {

	private static final long serialVersionUID = 2251889177219110859L;
	private static final Logger logger = Logger.getLogger(ElementFunctions.class.getName());

	private Context context;
	private Scriptable scope;
	private AryaMain main;

	private static String lastPage;
	private static String reqType;

	public ElementFunctions(Context context, Scriptable scope,AryaMain main) {
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
	public void post(String action, String requestType, Object params, String tabValue, NativeFunction onSuccess, NativeFunction onFailure) {
		//tab system is not implemented on android, so it isn't using
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
		String result = AryaInterpreterHelper.callUrl("http://"+MainActivity.inetAddr+":8080/arya/rest/asya", request.toString());

		logger.log(Level.FINE, "Post result: {0}", result);


		AryaResponse response = new AryaResponse();
		response.fromXMLString(result);//TODO result check

		if(tabValue.equals("login")) {
			AryaInterpreterHelper.interpretResponse(response, action, true, main);
		}
		else{
			AryaInterpreterHelper.interpretResponse(response, action, false, main);
		}

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
	public Object getElementById(String id) {

		View child = null;
		for (int i = 0; i < main.getAryaWindow().getChildCount(); i++) {

			child = main.getAryaWindow().getChildAt(i);
			if (child instanceof IAryaComponent) {
				IAryaComponent o = (IAryaComponent) child;

				if (id.equals(o.getComponentId())) {
					return o;
				}
			}
		}
		return null;
	}

	@AryaJsFunction
	public Object[] getElementsByName(String name) {
		List objList = new ArrayList();
		View child = null;
		for (int i = 0; i < main.getAryaWindow().getChildCount(); i++) {

			child = main.getAryaWindow().getChildAt(i);
			if (child instanceof IAryaComponent) {
				IAryaComponent o = (IAryaComponent) child;
				if (name.equalsIgnoreCase(
						o.getClass().toString().replace("class tr.com.agem.arya.interpreter.components.Arya", ""))) {
					objList.add(o);
				}
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}

	@AryaJsFunction
	public Object[] getElementsByClass(String className) {
		List objList = new ArrayList();
		View child = null;
		for (int i = 0; i < main.getAryaWindow().getChildCount(); i++) {

			child = main.getAryaWindow().getChildAt(i);
			if (child instanceof IAryaComponent) {
				IAryaComponent o = (IAryaComponent) child;
				if (className.equalsIgnoreCase(o.getComponentClassName())) {
					objList.add(o);
				}
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}

	@AryaJsFunction
	public String serializeForm() {
		String strSerialize = "";
		View child = null;
		for (int i = 0; i < main.getAryaWindow().getChildCount(); i++) {
			child = main.getAryaWindow().getChildAt(i);
			if (child instanceof IAryaComponent) {
				IAryaComponent o = (IAryaComponent) child;
				strSerialize += ",\"" + o.getComponentId() + "\":"
						+ (o.getComponentValue() == null ? null : "\"" + o.getComponentValue() + "\"");
			}
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

}
