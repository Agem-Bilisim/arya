package tr.com.agem.java.mapper;

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
		return mr;
	}

}
