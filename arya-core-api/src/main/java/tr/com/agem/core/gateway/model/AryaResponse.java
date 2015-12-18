package tr.com.agem.core.gateway.model;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Arya response object for Arya interpreters
 *
 */
public class AryaResponse implements IAryaResponse
{
	/**
	 * metadata of the view in XML notation
	 */
	private String view;
	
	/**
	 * response data in JSON  
	 */
	private String data;
	
	private Object attributes;

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public Object getAttributes() {
		return attributes;
	}
	
	public void fromXMLString(String xmlString) {
		
		try {
			
			Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
			
			NodeList nodeList = doc.getElementsByTagName("view");

			assert(nodeList != null && nodeList.getLength() == 1);

			this.view = nodeList.item(0).getTextContent();

			nodeList = doc.getElementsByTagName("data");

			assert(nodeList != null && nodeList.getLength() == 1);

			this.data = nodeList.item(0).getTextContent();
			
			nodeList = doc.getElementsByTagName("attributes");

			assert(nodeList != null && nodeList.getLength() == 1);

			this.attributes = nodeList.item(0).getTextContent();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} 
	}

	public String toString() {

		StringBuilder xmlString = new StringBuilder();

		xmlString.append("<arya-response>");

		if (this.view != null) {
			xmlString.append("<view><![CDATA[")
			.append(this.view)
			.append("]]></view>");
		} else {
			xmlString.append("<view/>");			
		}

		if (this.data != null) {
			xmlString.append("<data><![CDATA[")
			.append(this.data)
			.append("]]></data>");
		} else {
			xmlString.append("<data/>");			
		}
		
		if (this.attributes != null) {
			xmlString.append("<attributes><![CDATA[{ \"session\":[{")
			.append(this.attributes)
			.append("}] }]]></attributes>");
		} else {
			xmlString.append("<attributes/>");			
		}
		
		xmlString.append("</arya-response>");

		return xmlString.toString();
	}

	

}
