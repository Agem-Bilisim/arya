package tr.com.agem.arya.metadata.xml;

import java.util.HashMap;

import tr.com.agem.arya.metadata.elements.*;
import tr.com.agem.tag.anotations.InputTypeEnum;

public class XMLElementFactory {

	private static HashMap<InputTypeEnum, XMLElement> map = null;
	static {
		map = new HashMap<InputTypeEnum, XMLElement>();
		map.put(InputTypeEnum.BUTTON, new XMLButtonElement());
		map.put(InputTypeEnum.TEXT, new XMLTextElement());
		map.put(InputTypeEnum.DOUBLE, new XMLDoubleElement());
		map.put(InputTypeEnum.INTEGER, new XMLIntegerElement());
		map.put(InputTypeEnum.AUTOCOMPLETE, new XMLAutocompleteElement());
		map.put(InputTypeEnum.DATE, new XMLDateElement());
		map.put(InputTypeEnum.HIDDEN, new XMLHiddenElement());
		map.put(InputTypeEnum.PASSWORD, new XMLPasswordElement());
		map.put(InputTypeEnum.TEXTAREA, new XMLTextareaElement());
		map.put(InputTypeEnum.CHECK, new XMLCheckboxElement());
		map.put(InputTypeEnum.STATIC, new XMLLabelElement());
		map.put(InputTypeEnum.SELECT, new XMLSelectElement());
	}
	
	public static XMLElement getInstance(InputTypeEnum key){
		if(key != null){
			return map.get(key);
		}
		return null;
	}
	
}
