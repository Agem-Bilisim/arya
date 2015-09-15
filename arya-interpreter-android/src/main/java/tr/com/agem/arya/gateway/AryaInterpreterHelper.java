package tr.com.agem.arya.gateway;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import tr.com.agem.arya.R;
import tr.com.agem.arya.interpreter.components.AryaListBox;
import tr.com.agem.arya.interpreter.components.AryaListCell;
import tr.com.agem.arya.interpreter.components.AryaListItem;
import tr.com.agem.arya.interpreter.main.components.AryaMain;
import tr.com.agem.arya.interpreter.main.components.AryaWindow;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaInterpreterHelper {

    private static final String MIME_TYPE = "application/json";
    private static final String TAG = "AryaInterpreterHelper";

    public static String callUrl(String url, AryaRequest request) {
        return callUrl(url, request.toJSON());
    }

    public static String callUrl(String url, String request) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", MIME_TYPE);
            urlConnection.setRequestProperty("Accept", MIME_TYPE);
            urlConnection.setConnectTimeout(4000);

            urlConnection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.writeBytes(request);
            writer.flush();
            writer.close();

            int responseCode = urlConnection.getResponseCode();

            String responseStr = null;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line = null;
                StringBuffer responseText = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    responseText.append("\n");
                    responseText.append(line);
                }
                reader.close();
                responseStr = responseText.toString();
            }

            return responseStr;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void interpretResponse(String responseView, AryaMain main) {

        if (main.getAryaWindow().getComponents() != null) {
            main.getAryaWindow().getComponents().clear();
        }

        Log.d("------------------","INTERPRET RESPONSE");

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = null;

        try {
            parser = saxParserFactory.newSAXParser();
            parser.parse(new InputSource(new StringReader(responseView)), new AryaMetadataParser(main));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView image = new ImageView(main.getAryaWindow().getContext());
        image.setImageResource(R.drawable.agem_logo);
        image.setPadding(700, 0, 1, 0);

        main.getAryaWindow().addView(image);

    }



    public static void populateResponse(String responseData, AryaMain main) {

        Log.d("------------------", "POPULATE DATA");

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(responseData);

            Map.Entry<String, JsonNode> resultEntry = null;

            Log.d("------------- ANALIZ", "root node -" + rootNode.getNodeType()+"-");
            if (rootNode != null) {
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

                if (fields != null) {

                    IAryaComponent comp=null;

                    while (fields.hasNext()) {
                        Map.Entry<String, JsonNode> entry = fields.next();

                        Log.d("------------- ANALIZ", "key-"+entry.getKey()+"-value-"+entry.getValue());

                        if("fullListSize".equals(entry.getKey().toString()))//numeric mi
                        {
                            if(Integer.parseInt(entry.getValue().toString())>1){
                                 comp = (IAryaComponent) getElementById("list",main.getAryaWindow()); //listbox u buluruz

                                Log.d("**********",""+comp.getComponentId());
                                //listedir
                            }
                        }

                        if("results".equals(entry.getKey().toString())){
                            resultEntry=entry;
                        }

                    }

                    if(AryaUtils.isNotEmpty(comp)){//yukarda liste var o zaman dolduralım

                        ArrayList<String> idList = new ArrayList<>();

                        AryaListBox listBox = (AryaListBox) comp;
                        for (int i=0;i<listBox.getChildCount();i++){
                            IAryaComponent c= (IAryaComponent) listBox.getChildAt(i);
                            if(c instanceof AryaListItem)
                            {
                                AryaListItem item =((AryaListItem) c);
                                for(int j=0;j<item.getChildCount();j++){
                                    IAryaComponent cc= (IAryaComponent) item.getChildAt(j);
                                    if(cc instanceof AryaListCell){
                                        AryaListCell cell = (AryaListCell) cc;
                                        idList.add(cell.getComponentId());
                                    }
                                }
                            }
                        }



                        JSONArray jsonArray = new JSONArray(resultEntry.getValue().toString());

                        //TODO listbox componentinin id column id lerini çekelim bu id ler ile map ten veri çekip yeni row lar ekleyip set edelim window a

                       for (int i=0;i<jsonArray.length();i++){//her bir json nesnesi row yani listitem

                           JSONObject j = (JSONObject) jsonArray.get(i);

                           AryaListItem listItem = new AryaListItem(main.getAryaWindow());
                           listItem.setComponentParent(listBox);
                           //list item oluştur

                           for (int k=0;k<idList.size();k++){//id list sayısınca cell oluştur

                               AryaListCell newCell = new AryaListCell(main.getAryaWindow(),idList.get(k)+""+k,j.get(idList.get(k)).toString());//list itemin child ı olarak set et
                               newCell.setComponentParent(listItem);

                           }


                           /* Log.d("----" + i, "-" + j.toString());

                           Iterator<?> keys = j.keys();

                           while( keys.hasNext() ) {
                               String key = (String)keys.next();
                               String value=j.get(key).toString();
                               //Log.d("KEY:"+key,"-VALUE:"+j.get(key));
                           }*/

                        }
                        /*while(a.elements().hasNext()){
                            JsonNode x = a.elements().next();

                            Log.d("777777777777", "size a-"+a.size()+"-size x-"+x.size()+"-x value-"+x.textValue()+"-result nedir=" + resultEntry.getValue().toString());
                        }*/



                    }
                    else{//yukardaki liste değilmiş doldur kanka

                    }

                }


            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void populateReponse(String data, AryaMain main) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(data);
            if (rootNode != null) {
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
                if (fields != null) {
                    while (fields.hasNext()) {
                        Map.Entry<String, JsonNode> entry = fields.next();
                        //logger.log(Level.FINE, "JSON property: {0}:{1}", new Object[]{ entry.getKey(), entry.getValue() });

                        IAryaComponent comp = (IAryaComponent) getElementById(entry.getKey(),main.getAryaWindow());
                        if (comp != null) {
                            comp.setComponentValue(entry.getValue().asText());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getElementById(String id, AryaWindow window) {

        View child = null;
        for (int i = 0; i < window.getChildCount(); i++) {

            child = window.getChildAt(i);
            if (child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;

                if (id.equals(o.getComponentId())) {
                    return o;
                }
            }
        }
        return null;
    }

    public static boolean isNumeric(String str) //TODO bunu arya util e at
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}