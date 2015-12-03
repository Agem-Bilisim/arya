package tr.com.agem.arya.gateway;

import android.app.Activity;
import android.widget.ImageView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import tr.com.agem.arya.MainActivity;
import tr.com.agem.arya.R;
import tr.com.agem.arya.interpreter.components.AryaListBox;
import tr.com.agem.arya.interpreter.components.AryaListCell;
import tr.com.agem.arya.interpreter.components.AryaListItem;
import tr.com.agem.arya.interpreter.components.AryaTemplate;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.components.base.AryaNavBar;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.arya.interpreter.parser.AryaParserAttributes;
import tr.com.agem.arya.interpreter.parser.IAryaMenu;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaTemplate;
import tr.com.agem.core.utils.AryaUtils;


public class AryaInterpreterHelper {

    private static final String MIME_TYPE = "application/json";
    private static final String TAG = "AryaInterpreterHelper";

    private static String jSessionId = null;

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

            if (jSessionId != null)
               urlConnection.setRequestProperty("Cookie", jSessionId);

            urlConnection.setConnectTimeout(4000);
            urlConnection.setDoOutput(true);


            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.writeBytes(request);
            writer.flush();
            writer.close();


            Map<String, List<String>> hdrs = urlConnection.getHeaderFields();
            Set<String> hdrKeys = hdrs.keySet();

            if (hdrs != null) {
                if (hdrs.get("Set-Cookie") != null) {
                    String header = hdrs.get("Set-Cookie").get(0);
                    jSessionId = header.substring(0, header.indexOf(";"));
                }
            }

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


    public static void interpretResponse(AryaResponse response, String action, boolean login, AryaMain main) { //Overloaded function


        if(login){
            main.getAryaNavBar().getMenuBar().getMenuItemsCopy().clear();
            for(int i=0;i<main.getAryaNavBar().getMenuBar().getMenuItems().size();i++){    //If it is a login page, delete menu and have a backup
                main.getAryaNavBar().getMenuBar().getMenuItemsCopy().add(main.getAryaNavBar().getMenuBar().getMenuItems().get(i));
            }
           while(main.getAryaNavBar().getMenuBar().getMenuItems().size()>0){        //Determine which menu items remain on login screen
               main.getAryaNavBar().getMenuBar().getMenuItems().remove(0);
           }
        }
        else{
            if(main.getAryaNavBar().getMenuBar().getMenuItems().size()!=main.getAryaNavBar().getMenuBar().getMenuItemsCopy().size()) {
                for (int i = 0; i < main.getAryaNavBar().getMenuBar().getMenuItemsCopy().size(); i++) {    //reload menu items to show in master
                    main.getAryaNavBar().getMenuBar().setMenuItems(main.getAryaNavBar().getMenuBar().getMenuItemsCopy());
                }
                ((Activity) (AryaNavBar.context)).invalidateOptionsMenu();
            }
        }
        if(AryaUtils.isNotEmpty(response.getView())) {

            if (main.getAryaWindow().getComponents() != null) {// TODO bu alan yönetilmeli neler kaldırılacak ekrandan

          /*      while ((main.getAryaWindow().getChildCount()) != 1)
                    if(!(main.getAryaWindow().getChildAt(1) instanceof IAryaMenu)) {
                        main.getAryaWindow().removeView(main.getAryaWindow().getChildAt(1));
                   }
*/
                main.getAryaWindow().clearPageExceptMenu();
            }

            AryaInterpreterHelper.drawView(response.getView(), main,false);
        }

        if(AryaUtils.isNotEmpty(response.getData()))
            AryaInterpreterHelper.populateView(response.getData(), action, main);

    }

    public static void interpretResponseMenu(AryaResponse response, AryaMain main) {
        if (AryaUtils.isNotEmpty(response.getView())) {// Remove previous
            // components before
            // adding new ones!
            if (AryaUtils.isNotEmpty(main.getAryaNavBar().getMenuBar())) {
                main.getAryaNavBar().getMenuBar().getMenuItems().clear();
            }

            drawView(response.getView(), main,true);
        }
    }

    private static void drawView(String view, AryaMain main, Boolean isMenu) {

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

        if(!isMenu) {
            ImageView image = new ImageView(main.getAryaWindow().getContext());
            image.setImageResource(R.drawable.agem_logo);
            image.setPadding(700, 0, 1, 0);

        main.getAryaWindow().addView(image);
        }
    }

    private static void populateAryaTemplate(AryaMain main, IAryaTemplate masterComponent, JSONArray jsonArrayData) {

        for (int i = 0; i < jsonArrayData.length(); i++) {

            JSONObject jsonObj = jsonArrayData.getJSONObject(i);

            AryaListItem item = new AryaListItem(null, main);

            if (masterComponent instanceof AryaListBox) {
                item.setComponentValue(jsonObj.toString());
                item.setComponentParent(masterComponent);
            }

            for (IAryaComponent comp : ((AryaTemplate) masterComponent.getAryaTemplate()).getChildren()) {

                if (!(comp instanceof AryaListItem)) {

                    AryaParserAttributes attr = new AryaParserAttributes();
                    attr.setValue("id", comp.getComponentId() + "" + (i));
                    if (masterComponent instanceof AryaListBox) {
                        attr.setValue("label", splitId(comp.getComponentId(), jsonObj));
                        AryaListCell cell = new AryaListCell(attr, main, null);
                        cell.setComponentParent(item);
                    }
                }
            }
        }
    }


    public static void populateView(String data, String action, AryaMain main) {

        System.out.println(data);
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
                            try {
                                String message = "";
                                if (action.endsWith("list")) {
                                    if (jsonArray.length() > 0)
                                        message=jsonArray.length()+" adet kayıt bulundu.";
                                    else
                                        message="Hiçbir kayıt bulunamadı.";

                                    populateAryaTemplate(main, (IAryaTemplate) getElementById(action, main), jsonArray);

                                }
                                else  if (jsonArray.length() == 1){


                                }
                            }
                            catch (Exception e ) {

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


    public static Object getElementById(String id, AryaMain main) {

        IAryaComponent child = null;
        for (int i = 0; i < main.getAryaWindow().getComponents().size(); i++) {

            child = main.getAryaWindow().getComponents().get(i);
            if (child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;

                if (id.equals(o.getComponentId())) {
                    return o;
                }
            }
        }
        return null;
    }

    public static String splitId(String id, JSONObject jsonObj) {

        String retVal = null;
        JSONObject obj = jsonObj;

        if (jsonObj != null) {

            String[] spl;

            if(id.contains("-")) {

                String[] temp = id.split("-");
                spl = temp[1].split("\\.");
            }
            else {
                spl = id.split("\\.");
            }

            for (int i = 0; i < spl.length - 1; i++)
                obj = (JSONObject) obj.get(spl[i]);

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

    public static String getjSessionId() {
        return jSessionId;
    }

    public static void setjSessionId(String jSessionId) {
        AryaInterpreterHelper.jSessionId = jSessionId;
    }

}