package tr.com.agem.arya.interpreter.utils;

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

import tr.com.agem.arya.interpreter.component.AryaWindow;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;

public class AryaInterpreterHelper {

	private static final String MIME_TYPE = "application/json";

	public static String callUrl(String url, AryaRequest request) {
		return callUrl(url, request.toJSON());
	}

	// TODO should be run on a seperate thread!
	public static String callUrl(String url, String request) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", MIME_TYPE);
		httpPost.setHeader("Accept", MIME_TYPE);

		try {

			StringEntity se = new StringEntity(request);
			httpPost.setEntity(se);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			String result = EntityUtils.toString(entity, "UTF-8");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void interpretResponse(AryaResponse response, AryaWindow aryaWindow) {

		// Remove previous components before adding new ones!
		if (aryaWindow.getComponentContainer() != null) {
			aryaWindow.getComponentContainer().getChildren().clear();
		}
		if (aryaWindow.getComponents() != null) {
			aryaWindow.getComponents().clear();
		}

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;

		try {
			parser = saxParserFactory.newSAXParser();
			parser.parse(new InputSource(new StringReader(response.getView())), new AryaMetadataParser(aryaWindow));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
