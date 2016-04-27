package tr.com.agem.arya.metadata.intercepter;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tr.com.agem.arya.metadata.component.AryaAttribute;
import tr.com.agem.arya.metadata.component.base.AryaGatewayMain;
import tr.com.agem.arya.metadata.interpreter.IAryaComponent;
import tr.com.agem.arya.metadata.parser.AryaGatewayMetadataParser;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.IAryaIntercepter;
import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.utils.AryaUtils;

public class AryaResponseAttributeIntercepter implements IAryaIntercepter {

	public AryaResponse intercept(String applicationName, IMetadata metadata, 
			AryaResponse response, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		// if no metadata, no place to set attributes
		if (metadata == null) {
			return response;
		}
		
		AryaGatewayMain main = new AryaGatewayMain();

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;

		try {
			parser = saxParserFactory.newSAXParser();
			parser.parse(new InputSource(new StringReader(metadata.getMetadata())), new AryaGatewayMetadataParser(main));
		} catch (ParserConfigurationException e) {
			AryaUtils.logException(null,e);
		} catch (SAXException e) {
			AryaUtils.logException(null,e); 
		} catch (IOException e) {
			AryaUtils.logException(null,e); 
		}

		HttpSession session = servletRequest.getSession();
		StringBuilder attribute = null;

		Set<IAryaComponent> comps = main.getAryaSessionComponents();
		for (IAryaComponent comp : comps) {
			if (comp instanceof AryaAttribute) {
				AryaAttribute attr = (AryaAttribute)comp;
				if(attribute == null)
					attribute = new StringBuilder("\""+attr.getName()+"\":"+session.getAttribute(attr.getName()));
				else
					attribute.append(",\""+attr.getName()+"\":"+session.getAttribute(attr.getName()));
			}
		}
		if(attribute != null) {
			response.setAttributes(attribute);
		}


		return response;
	}
}
