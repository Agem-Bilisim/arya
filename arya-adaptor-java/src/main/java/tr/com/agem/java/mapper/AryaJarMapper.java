package tr.com.agem.java.mapper;

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

		return mr;
	}

	private String findActionMethodName(String action) {
		String[] split = action.split("\\.");
		return split[split.length - 1];
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
