package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.arya.interpreter.parser.AryaHandler;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaInterpreter;

public class AryaInterpreterZkoss implements IAryaInterpreter {

	private List<IAryaComponentProperty> components;
	private AryaWindow aryaWindow;
	
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	
	@Override
	public void interpretAryaResponse(AryaResponse response, Object parent,Object masterWindow) {
		aryaWindow = (AryaWindow) masterWindow;
		aryaWindow.getIcerik().getChildren().clear();
		components = new ArrayList<IAryaComponentProperty>();
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);
		SAXParser parser;
		
		try {
			parser = saxParserFactory.newSAXParser();
			parser.parse(new InputSource(new StringReader(response.getView())), new AryaHandler(parent,aryaWindow,components));
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}

	@Override
	public void interpretAryaResponse(String responseStr, Object parent,Object masterWindow) {
		AryaResponse response = new AryaResponse();
		response.fromXMLString(responseStr);
		interpretAryaResponse(response, parent, masterWindow);
	}
	
	public List<IAryaComponentProperty> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponentProperty> components) {
		this.components = components;
	}

	@Override
	public void createComponents(Object parent, List<Object> components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object findComponentById(String id) {
			return null;
	}

	public JAXBContext getJaxbContext() {
		return jaxbContext;
	}

	public void setJaxbContext(JAXBContext jaxbContext) {
		this.jaxbContext = jaxbContext;
	}

	public Unmarshaller getJaxbUnmarshaller() {
		return jaxbUnmarshaller;
	}

	public void setJaxbUnmarshaller(Unmarshaller jaxbUnmarshaller) {
		this.jaxbUnmarshaller = jaxbUnmarshaller;
	}
	

}