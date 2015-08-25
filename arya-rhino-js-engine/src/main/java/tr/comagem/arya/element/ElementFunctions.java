package tr.comagem.arya.element;

import tr.comagem.arya.jsengine.AnnotatedScriptableObject;

@SuppressWarnings("serial")
public class ElementFunctions extends AnnotatedScriptableObject {

	@AryaJsFunction
	public String javaFunction1(String i) {
		return "j1->"+i;
	}
	
	@AryaJsFunction
	public String javaFunction2(String i) {
		return "j2->"+i;
	}
}
