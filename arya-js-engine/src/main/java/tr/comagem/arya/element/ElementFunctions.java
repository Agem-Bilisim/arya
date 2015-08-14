package tr.comagem.arya.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import tr.comagem.arya.jsengine.AnnotatedScriptableObject;

@SuppressWarnings("serial")
public class ElementFunctions extends AnnotatedScriptableObject {

	private Map<String, String> nameMap = Maps.newHashMap();
	private Map<String, String> clazzMap = Maps.newHashMap();
	// private Map<String, String> id = Maps.newHashMap();
	//TODO yapıyı elemente göre değiştiririz
	
	
	@Expose
	public String getElementById(String id) {
		return createStrObjById(id);
	}

	@Expose
	public String getElementsByName(String name) {
		List<String> idList = getKeyByValue(this.nameMap, name);
		return getElementsById(idList);
	}

	@Expose
	public String getElementsByClass(String clazz) {
		List<String> idList = getKeyByValue(this.clazzMap, clazz);
		return getElementsById(idList);
	}

	
	public ElementFunctions addElement(String id, String name, String clazz) {
		this.nameMap.put(id, name);
		this.clazzMap.put(id, clazz);
		return this;
	}
	private String getElementsById(List<String> idList) {

		String result="";
		
		for (String id : idList) {
			result+=createStrObjById(id);
		}
		return result;
	}

	public List<String> getKeyByValue(Map<String, String> map, String value) {
		List<String> list = new ArrayList<String>();

		for (String string : map.keySet()) {
			if (map.get(string).equals(value)) {
				list.add(string);
			}
		}
		return list;
	}
	
	private String createStrObjById(String id) {
		return new ElementModel(id, nameMap.get(id), clazzMap.get(id)).toString();
	}
}
