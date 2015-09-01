package tr.com.agem.java.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.core.adaptor.IAryaConverter;

public class AryaRestConverter implements IAryaConverter {

	@Override
	public Object convert(Object params) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
