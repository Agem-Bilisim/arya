package tr.com.agem.arya.interpreter.rhino;

import java.util.ArrayList;
import java.util.List;

import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.arya.interpreter.zkoss.AryaWindow;

public class ElementFunctions extends AnnotatedScriptableObject {

	private static final long serialVersionUID = 2251889177219110859L;
	private AryaWindow window;

	public ElementFunctions(AryaWindow window) {
		this.window = window;
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
}
