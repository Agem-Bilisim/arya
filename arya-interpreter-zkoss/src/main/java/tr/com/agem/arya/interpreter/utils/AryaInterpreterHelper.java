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

import tr.com.agem.arya.interpreter.base.components.AryaMain;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.utils.AryaUtils;

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

	public static void interpretResponse(AryaResponse response, AryaMain main) {

		if(AryaUtils.isNotEmpty(response.getView())){
			// Remove previous components before adding new ones!
			if (main.getAryaWindow().getComponentContainer() != null) { //TODO bu alan yönetilmeli
				main.getAryaWindow().getComponentContainer().getChildren().clear();
			}
			if (main.getAryaWindow().getComponents() != null) {
				main.getAryaWindow().getComponents().clear();
			}
			
			drawView(response.getView(),main);
		}
		
		if(AryaUtils.isNotEmpty(response.getData())){
			populateView(response.getData(),main);
		}
	}

	private static void populateView(String data, AryaMain main) {
		
		/* ObjectMapper mapper = new ObjectMapper();

	        try {
	            JsonNode rootNode = mapper.readTree(data);
	            Map.Entry<String, JsonNode> resultEntry = null;

	            if (rootNode != null) {
	                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

	                if (fields != null) {
	                    IAryaComponent comp=null;

	                    while (fields.hasNext()) {
	                    	 Map.Entry<String, JsonNode> entry = fields.next();
	                    	 
	                    	 if("results".equals(entry.getKey().toString())){
	                             resultEntry=entry;
	                         }
	                    }
	                    
	                    JSONArray jsonArray = new JSONArray(resultEntry.getValue().toString());

	                       for (int i=0;i<jsonArray.length();i++){

	                           JSONObject j = (JSONObject) jsonArray.get(i);

	                           /*for (int k=0;k<idList.size();k++){//idList'teki id lere göre cell'leri oluşturup Item e set et

	                               AryaParserAttributes attr = new AryaParserAttributes();
	                               attr.setValue("id",idList.get(k)+""+k);
	                               attr.setValue("label",j.get(idList.get(k)).toString());

	                               AryaListCell newCell = new AryaListCell(attr,main.getAryaWindow(),null);//list itemin child ı olarak set et
	                               newCell.setComponentParent(listItem);
	                           }*//*
	                        }

	                   
	                }
	            }

	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } */
	}
	


	private static void drawView(String view, AryaMain main) {

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;

		try {
			parser = saxParserFactory.newSAXParser();
			parser.parse(new InputSource(new StringReader(view)), new AryaMetadataParser(main));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
