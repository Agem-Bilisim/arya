package tr.com.agem.core.gateway.model;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import tr.com.agem.core.utils.AryaUtils;

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
	
	private String messageCode;
	private String message;
	private boolean isError;
	
	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

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
	
	
	private String getNodeTextContent(NodeList nodeList) {
		return (nodeList != null && nodeList.getLength() == 1) ? nodeList.item(0).getTextContent() : null;
	}
	
	public void fromXMLString(String xmlString) {
		
		try {
			
			Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
			
			this.view = getNodeTextContent( doc.getElementsByTagName("view") );					

			this.data  = getNodeTextContent( doc.getElementsByTagName("data") );					

			this.attributes  = getNodeTextContent( doc.getElementsByTagName("attributes") );					

			this.messageCode =  getNodeTextContent( doc.getElementsByTagName("messageCode") );

			this.message =  getNodeTextContent( doc.getElementsByTagName("message") );

			this.isError = ("Y".equals(getNodeTextContent( doc.getElementsByTagName("isError")))) ? true : false;


		} catch (Exception e) {
			AryaUtils.logException(null,e);
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
		
		xmlString.append("<isError>").append(this.isError ? "Y" : "").append("</isError>");
		xmlString.append("<message>").append((this.message != null ) ? this.message : "").append("</message>");
		xmlString.append("<messageCode>").append((this.messageCode != null ) ? this.messageCode : "").append("</messageCode>");

		xmlString.append("</arya-response>");

		return xmlString.toString();
	}

}
