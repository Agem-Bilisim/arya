package tr.com.agem.arya.mapper;

import tr.com.agem.core.adaptor.IAryaMappedRequest;
import tr.com.agem.core.adaptor.IAryaMapper;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.core.utils.AryaUtils;

public class AryaRestMapper implements IAryaMapper {

	@Override
	public IAryaMappedRequest map(String action) {
		String url = PropertyReader.property("rest.base.url");
		if (!url.endsWith("/")) url += "/";
		url += AryaUtils.join("/", action.split("\\."));
		AryaRestMappedRequest mr = new AryaRestMappedRequest();
		mr.setActionURL(url);
		mr.setViewName(findViewName(action));
		return mr;
	}

	// genel.kimlik.list ==> listKimlik
	private String findViewName(String action) {
		String[] split = action.split("\\.");
		return split[split.length - 1] + AryaUtils.capitalize(split[split.length - 2]);
	}

}
