package tr.com.agem.arya.gateway;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;

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
     * script metadata forms up the logic part of the view
     */
    private String script;

    /**
     * response data in JSON
     */
    private String data;

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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
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

            nodeList = doc.getElementsByTagName("script");

            assert(nodeList != null && nodeList.getLength() == 1);

            this.script = nodeList.item(0).getTextContent();

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

        if (this.script != null) {
            xmlString.append("<script><![CDATA[")
                    .append(this.script)
                    .append("]]></script>");
        } else {
            xmlString.append("<script/>");
        }

        xmlString.append("</arya-response>");

        return xmlString.toString();
    }

}