package tr.com.agem.java.mapper;

import tr.com.agem.core.adaptor.IAryaMapper;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.core.utils.AryaUtils;

public class AryaJarMapper implements IAryaMapper {

	@Override
	public String map(String action) {
		String packageNamePrefix = PropertyReader.property("package.name.prefix");
		if (!packageNamePrefix.endsWith("\\.")) packageNamePrefix += ".";
		String classNamePrefix = findClassNamePrefix(action);
		packageNamePrefix += classNamePrefix;
		return packageNamePrefix;
	}

	// action: genel.kimlik.list ==> returns 'genel.kimlik.Kimlik'
	private String findClassNamePrefix(String action) {
		String[] split = action.split("\\.");
		split[split.length - 1] = AryaUtils.capitalize(split[split.length - 2]);
		return AryaUtils.join(".", split);
	}

}
