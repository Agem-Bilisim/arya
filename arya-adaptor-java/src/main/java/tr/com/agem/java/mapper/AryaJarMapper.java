package tr.com.agem.java.mapper;

import java.util.HashMap;
import java.util.Map;

import tr.com.agem.core.adaptor.IAryaMappedRequest;
import tr.com.agem.core.adaptor.IAryaMapper;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.core.utils.AryaUtils;

public class AryaJarMapper implements IAryaMapper {

	@Override
	public IAryaMappedRequest map(String action) {

		AryaJarMappedRequest mr = new AryaJarMappedRequest();

		mr.setAction(action);
		mr.setActionMethodName(findActionMethodName(action));
		mr.setPath(findPath(action));
		String clsNamePrefix = findClassNamePrefix(action);
		mr.setFormName(clsNamePrefix + ("list".equalsIgnoreCase(mr.getActionMethodName()) ? "ParameterForm" : "Form"));
		mr.setServiceName(clsNamePrefix + "Service");
		mr.setViewName(findViewName(action));

		return mr;
	}

	// genel.kimlik.list ==> genel.listKimlik
	private String findViewName(String action) {
		
		String[] split = action.split("\\.");
		String[] prefix = new String[split.length-2];
		for (int i = 0; i < prefix.length; i++) {
			prefix[i] = split[i];
		}
		return AryaUtils.join(".", prefix) + "." + split[split.length - 2] + "." + split[split.length - 1] + AryaUtils.capitalize(split[split.length - 2]);
	}
	
	private static final Map<String, String> ACTION_MAP = new HashMap<String, String>();
	static {
		ACTION_MAP.put("edit", "update");
		ACTION_MAP.put("add", "insert");
	}

	private String findActionMethodName(String action) {
		String[] split = action.split("\\.");
		String v = split[split.length - 1];
		String x =  ACTION_MAP.get(v);
		if (x == null) {
			return v;
		}
		return x;
	}

	private String findPath(String action) {
		return "/" + action.replaceAll("\\.", "/");
	}

	// action: genel.kimlik.list ==> returns 'genel.kimlik.Kimlik'
	private String findClassNamePrefix(String action) {

		String clsNamePrefix = PropertyReader.property("package.name.prefix");
		if (!clsNamePrefix.endsWith("\\."))
			clsNamePrefix += ".";
		String[] split = action.split("\\.");
		
		split[split.length - 1] = AryaUtils.capitalize(split[split.length - 2]);
		clsNamePrefix += AryaUtils.join(".", split);
		return clsNamePrefix;
	}

}
