package tr.com.agem.arya.converter;

import tr.com.agem.core.adaptor.IAryaConverter;
import tr.com.agem.core.utils.AryaUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AryaRestConverter implements IAryaConverter {

	@Override
	public Object convert(Object params) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(params);
		} catch (Exception e) {
			AryaUtils.logException(null,e);
			throw new RuntimeException(e);
		}
	}

}
