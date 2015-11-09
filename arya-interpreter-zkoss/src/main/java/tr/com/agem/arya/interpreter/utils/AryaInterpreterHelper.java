package tr.com.agem.arya.interpreter.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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
import org.zkoss.zul.Div;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.arya.interpreter.component.AryaComboItem;
import tr.com.agem.arya.interpreter.component.AryaCombobox;
import tr.com.agem.arya.interpreter.component.AryaGrid;
import tr.com.agem.arya.interpreter.component.AryaListCell;
import tr.com.agem.arya.interpreter.component.AryaListItem;
import tr.com.agem.arya.interpreter.component.AryaListbox;
import tr.com.agem.arya.interpreter.component.AryaRow;
import tr.com.agem.arya.interpreter.component.AryaRows;
import tr.com.agem.arya.interpreter.component.AryaTab;
import tr.com.agem.arya.interpreter.component.AryaTabbox;
import tr.com.agem.arya.interpreter.component.AryaTabpanel;
import tr.com.agem.arya.interpreter.component.AryaTabpanels;
import tr.com.agem.arya.interpreter.component.AryaTabs;
import tr.com.agem.arya.interpreter.component.AryaTemplate;
import tr.com.agem.arya.interpreter.component.ComponentFactory;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.arya.interpreter.parser.AryaParserAttributes;
import tr.com.agem.arya.interpreter.script.ElementFunctions;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaTemplate;
import tr.com.agem.core.property.reader.PropertyReader;
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

	public static void interpretResponse(AryaResponse response, AryaMain main, AryaTabs tabs, AryaTabpanels tabpanels, String tabValue) {

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

			drawView(response.getView(), main, tabs, tabpanels, false, tabValue);
		}

		if (AryaUtils.isNotEmpty(response.getData())) {
			populateView(response.getData(), main);
		}
		
		populateListComponents(main);
	}

	public static void interpretResponseMenu(AryaResponse response, AryaMain main, AryaTabs tabs, AryaTabpanels tabpanels) {
		if (AryaUtils.isNotEmpty(response.getView())) {// Remove previous
														// components before
														// adding new ones!
			if (AryaUtils.isNotEmpty(main.getMenuContainer())) {
				Div menuDiv = main.getMenuContainer();
				menuDiv.getChildren().clear();
			}

			drawView(response.getView(), main, tabs, tabpanels, true, null);
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
			
			// to search a json object
			if(ElementFunctions.getComps() != null) {
			
				for (int j = 0; j < ElementFunctions.getComps().size(); j++) {
					
					String compValue = splitId((String) ElementFunctions.getComps().get(j), jsonObj);
					String value = (String)ElementFunctions.getValues().get(j);

					if(compValue != null && !value.equals("")) {
						if(compValue.startsWith(value)) { 
							
							setValue(jsonObj, masterComponent, rows, row, item);							
						}
						else {	
							
							if (masterComponent.getClass().equals(AryaListbox.class)) {
								item = new AryaListItem(main, null);
								
							} else { 
								row = new AryaRow(main, null);							
							}
							break;
						}  
					}
					else if(!value.equals("")) {
						
						if (masterComponent.getClass().equals(AryaListbox.class)) {
							item = new AryaListItem(main, null);
							
						} else { 
							row = new AryaRow(main, null);							
						}	
						break;
					}						
				}				
			}			
			else {
				
				setValue(jsonObj, masterComponent, rows, row, item);
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
		
		ElementFunctions.setComps(null);
		ElementFunctions.setValues(new ArrayList<String>());
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
							} else  if (jsonArray.length() == 1){  
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
	
	private static void populateListComponents(AryaMain main) {
		
		for (Iterator<IAryaComponent> iterator = main.getAryaWindow().getComponents().iterator(); iterator.hasNext();) {
			IAryaComponent comp = (IAryaComponent) iterator.next();
			
			if (comp instanceof AryaCombobox) {
				AryaCombobox combobox = (AryaCombobox) comp;
				
				if (combobox.getTooltip() != null) {
					AryaRequest request = new AryaRequest();
					
					request.setAction(combobox.getTooltip());
					request.setRequestType(RequestTypes.DATA_ONLY);
	
					String responseStr=null;
					try {
						responseStr = AryaInterpreterHelper.callUrl(PropertyReader.property("gateway.base.url"), request);
						
						AryaResponse response = new AryaResponse();
						response.fromXMLString(responseStr);
						
						
						ObjectMapper mapper = new ObjectMapper();
						try {
							JsonNode rootNode = mapper.readTree(response.getData());
							if (rootNode != null) {
								Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
								if (fields != null) {
									while (fields.hasNext()) {
										Map.Entry<String, JsonNode> entry = fields.next();
										if ("results".equals(entry.getKey().toString())) {
											
											JSONArray jsonArray = new JSONArray(entry.getValue().toString());
											
											populateCombobox(main, jsonArray, combobox);
											
										}
									}
								}
							}
							

						} catch (JsonProcessingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
						System.out.println(responseStr);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
	}
	
	private static void populateCombobox(AryaMain main, JSONArray jsonArray, AryaCombobox combobox) {
		
		for (int i = 0; i < jsonArray.length(); i++) {
			
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			
			AryaParserAttributes attr = new AryaParserAttributes();
			attr.setValue("id", combobox.getComponentId() + "" + (i));
			
			attr.setValue("label", splitId(combobox.getComponentId(), jsonObj));
			AryaComboItem comboItem = new AryaComboItem(main, attr);
			comboItem.setComponentParent(combobox);
			
		} 
		
	}

	private static void drawView(String view, AryaMain main, AryaTabs tabs, AryaTabpanels tabpanels, Boolean isMenu, String tabValue) {
						
		AryaTabpanel tabpanel = null;
		
		if(!isMenu) {
			AryaTab tab = new AryaTab(main, null);
			tab.setParent(tabs);
			tab.setClosable(true);
			tab.setSelected(true);
			tab.setLabel(tabValue); 
				
			tabpanel = new AryaTabpanel(main, null);
			tabpanel.setParent(tabpanels); 
		}

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;

		try {
			parser = saxParserFactory.newSAXParser();
			parser.parse(new InputSource(new StringReader(view)), new AryaMetadataParser(main, isMenu, tabpanel));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		AryaTabbox tabbox = new AryaTabbox(main, null);
//		tabbox.setComponentParent(getElementById("list", main));
//		
//		System.out.println(getElementById("listIsciWindow", main));
//		
//		AryaTabs tabs = new AryaTabs(main, null);
//		tabs.setComponentParent(tabbox);
		
	
//			AryaTab tab = (AryaTab) getElementById("tab", main);
//
//			if(tab != null) {
//				tab.setValue("aaa");
//				tab.setComponentParent(getElementById("tabs", main));
//			}
		
		
		
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
	
	public static void setValue(JSONObject jsonObj, IAryaTemplate masterComponent, AryaRows rows, AryaRow row, AryaListItem item) {
		
		if (masterComponent.getClass().equals(AryaListbox.class)) {
			item.setValue(jsonObj);
			item.setComponentParent(masterComponent);
		} else {
			row.setValue(jsonObj);
			row.setComponentParent(rows);
		}		
	}
}
