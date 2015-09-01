package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tr.com.agem.arya.interpreter.parser.AryaHandler;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;

public class AryaInterpreterHelper {

	public static String callUrl(String url, AryaRequest request) {
		return callUrl(url, request.toJSON());
	}

	public static String callUrl(String url, String request) {
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type", "application/json");
		httppost.setHeader("Accept", "application/json");

		try {
			StringEntity se = new StringEntity(request);
			httppost.setEntity(se);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void interpretResponse(AryaResponse response, AryaWindow aryaWindow) {

		// Remove previous components before adding new ones!
		aryaWindow.getComponentContainer().getChildren().clear();

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;

		try {
			parser = saxParserFactory.newSAXParser();
			parser.parse(new InputSource(new StringReader(response.getView())), new AryaHandler(aryaWindow));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
