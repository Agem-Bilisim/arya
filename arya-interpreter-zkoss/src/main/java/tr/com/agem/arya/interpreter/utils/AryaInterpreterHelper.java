package tr.com.agem.arya.interpreter.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.arya.interpreter.component.AryaGrid;
import tr.com.agem.arya.interpreter.component.AryaLabel;
import tr.com.agem.arya.interpreter.component.AryaListCell;
import tr.com.agem.arya.interpreter.component.AryaListItem;
import tr.com.agem.arya.interpreter.component.AryaListbox;
import tr.com.agem.arya.interpreter.component.AryaRow;
import tr.com.agem.arya.interpreter.component.AryaRows;
import tr.com.agem.arya.interpreter.component.AryaTemplate;
import tr.com.agem.arya.interpreter.component.ComponentFactory;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.arya.interpreter.parser.AryaParserAttributes;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaTemplate;
import tr.com.agem.core.utils.AryaUtils;

public class AryaInterpreterHelper {

	private static final String MIME_TYPE = "application/json";

	private static HttpClient httpClient = HttpClientBuilder.create().build();

	public static String callUrl(String url, AryaRequest request) throws AryaException {
		return callUrl(url, request.toJSON());
	}

	// TODO should be run on a seperate thread!
	public static String callUrl(String url, String request) throws AryaException {

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", MIME_TYPE);
		httpPost.setHeader("Accept", MIME_TYPE);

		StringEntity se;
		String result = null;
		try {
			se = new StringEntity(request);

			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost);
			// TODO AryaLoginFailedException reason ı göster
			System.out.println(response.getStatusLine().getStatusCode() + "-"
					+ response.getStatusLine().getReasonPhrase() + "-" + response.getStatusLine().getStatusCode());

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new AryaException("hooo");
			}

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static void interpretResponse(AryaResponse response, AryaMain main) {

		if (AryaUtils.isNotEmpty(response.getView())) {// Remove previous
														// components before
														// adding new ones!

			if (AryaUtils.isNotEmpty(main.getComponentContainer())) {

				if (AryaUtils.isNotEmpty(main.getAryaWindow().getComponents())) {
					for (IAryaComponent c : main.getAryaWindow().getComponents()) {
						main.getComponentContainer().removeChild((Component) c);
					}
				}
			}

			if (AryaUtils.isNotEmpty(main.getAryaWindow().getComponents())) {
				main.getAryaWindow().getComponents().clear();
			}

			drawView(response.getView(), main);
		}

		if (AryaUtils.isNotEmpty(response.getData())) {
			populateView(response.getData(), main);
		}
	}

	private static void populateAryaTemplate(AryaMain main, IAryaTemplate masterComponent, JSONArray jsonArrayData) {
		AryaRows rows = null;
		if (masterComponent instanceof AryaGrid) {
			rows = new AryaRows(main, null);
			rows.setComponentParent(masterComponent);
		}

		for (int i = 0; i < jsonArrayData.length(); i++) {
			JSONObject jsonObj = jsonArrayData.getJSONObject(i);

			AryaListItem item = new AryaListItem(main, null);
			AryaRow row = new AryaRow(main, null);

			if (masterComponent.getClass().equals(AryaListbox.class)) {
				item.setValue(jsonObj);
				item.setComponentParent(masterComponent);
			} else {
				row.setValue(jsonObj);
				row.setComponentParent(rows);
			}

			for (IAryaComponent comp : ((AryaTemplate) masterComponent.getAryaTemplate()).getChildren()) {
				if (!(comp instanceof AryaListItem) && !(comp instanceof AryaRow)) {
					AryaParserAttributes attr = new AryaParserAttributes();
					attr.setValue("id", comp.getComponentId() + "" + (i));
					if (masterComponent instanceof AryaListbox) {
						attr.setValue("label", splitId(comp.getComponentId(), jsonObj));
						AryaListCell cell = new AryaListCell(main, attr);
						cell.setComponentParent(item);
					} else if (masterComponent instanceof AryaGrid) {
						attr.setValue("value", splitId(comp.getComponentId(), jsonObj));
						IAryaComponent compNew = ComponentFactory.getComponent(comp.getComponentTagName(), main, attr);
						compNew.setComponentParent(row);
					} else {

					}
				}
			}
		}
	}

	private static void populateView(String data, AryaMain main) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(data);
			if (rootNode != null) {
				Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
				if (fields != null) {
					while (fields.hasNext()) {
						Map.Entry<String, JsonNode> entry = fields.next();
						if ("results".equals(entry.getKey().toString())) {
							JSONArray jsonArray = new JSONArray(entry.getValue().toString());
							if (jsonArray.length() > 1) {
								populateAryaTemplate(main, (IAryaTemplate) getElementById("list", main), jsonArray);
							} else {
								JSONObject jsonObj = jsonArray.getJSONObject(0);
								for (Iterator<?> iterator = jsonObj.keySet().iterator(); iterator.hasNext();) {
									String key = (String) iterator.next();
									IAryaComponent c = (IAryaComponent) getElementById(key, main);

									if (AryaUtils.isNotEmpty(jsonObj.get(key).toString()) && AryaUtils.isNotEmpty(c))
										c.setComponentValue(jsonObj.get(key).toString());
								}
							}
						}
					}
				}
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public static IAryaComponent getElementById(String id, AryaMain main) { // only
																			// on
																			// window
																			// not
																			// menu

		IAryaComponent comp;

		for (int i = 0; i < main.getAryaWindow().getComponents().size(); i++) {
			comp = main.getAryaWindow().getComponents().get(i);

			if (id.equalsIgnoreCase(comp.getComponentId())) {
				return comp;
			}
		}
		return null;
	}

	public static String splitId(String id, JSONObject jsonObj) {

		String retVal = null;
		JSONObject obj = null;
		if (jsonObj != null) {
			String[] spl = id.split("\\.");

			for (int i = 0; i < spl.length - 1; i++)
				obj = (JSONObject) jsonObj.get(spl[i]);
			Object ret;
			if (obj != null)
				ret = obj.get(spl[spl.length - 1]);
			else
				ret = jsonObj.get(spl[0]);
			if (!ret.equals(JSONObject.NULL)) {
				retVal = ret.toString();
			}
		}
		return retVal;
	}

}
