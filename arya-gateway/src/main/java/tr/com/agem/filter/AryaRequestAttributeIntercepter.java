package tr.com.agem.filter;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.component.AryaAttribute;
import tr.com.agem.component.base.AryaGatewayMain;
import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.metadata.IMetadataEngine;
import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.filter.utils.AryaRequestWrapper;
import tr.com.agem.parser.AryaGatewayMetadataParser;



public class AryaRequestAttributeIntercepter extends OncePerRequestFilter{
	
	@Autowired
	IMetadataEngine metadataEngine;
	
	@Autowired
	AryaApplicationAdaptor applicationAdaptor;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		
		AryaRequestWrapper requestWrapper = new AryaRequestWrapper((HttpServletRequest) request, "", "");
		
		IMetadata metadata = null;
		
		AryaRequest aryaRequest = new ObjectMapper().readValue(request.getInputStream(), AryaRequest.class);
		
		if (RequestTypes.VIEW_ONLY.equals(aryaRequest.getRequestType())){
			metadata = metadataEngine.findWithNameAsXML("asya", aryaRequest.getAction());  //TODO applicationName
		}
		else {
			
			IAryaAdaptorResponse adaptorResponse = applicationAdaptor.handleRequest(aryaRequest);

			if (adaptorResponse != null) {
				if (adaptorResponse.getViewName() != null && RequestTypes.ALL.equals(aryaRequest.getRequestType())) {
					metadata = metadataEngine.findWithNameAsXML("asya", adaptorResponse.getViewName());
				}
			}
		}
		
		if(metadata != null) {
			
			AryaGatewayMain main = new AryaGatewayMain();
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser parser = null;

			try {
				parser = saxParserFactory.newSAXParser();
				parser.parse(new InputSource(new StringReader(metadata.getMetadata())), new AryaGatewayMetadataParser(main));
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace(); 
			}
			
			
			
			if(getAttribute(main) != null) {
				AryaAttribute attr = (AryaAttribute)getAttribute(main);
				requestWrapper = new AryaRequestWrapper((HttpServletRequest) request, attr.getName(), attr.getValue());
			}
		}
		//HttpSession session = request.getSession();
		
		//IMetadata metadata = metadataEngine.findWithNameAsXML("asya", aryaRequest.getAction());
		//System.out.println("AryaRequestAttrIntercepter:---------"+metadata.getMetadata());
		
		//aryaRequest.setAttributeName("SESSION_SUBE_LIST");
		
		//System.out.println("intercepter ----- "+session.getAttribute(aryaRequest.getAttributeName()));
		//request.setAttribute(aryaRequest.getAttributeName(), session.getAttribute(aryaRequest.getAttributeName()));
		//request.setAttribute("SESSION_SUBE_LIST", session.getAttribute("SESSION_SUBE_LIST"));
		
		filterChain.doFilter(requestWrapper, response);
	}
	
	public IAryaComponent getAttribute(AryaGatewayMain main) {

		Set<IAryaComponent> comps = main.getAryaSessionComponents();
		for (IAryaComponent comp : comps) {

			if (comp instanceof AryaAttribute) {
					return comp;
			}
		}
		return null;
	}

}
