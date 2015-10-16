package tr.com.agem.arya.gateway;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.CookieHandler;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import tr.com.agem.arya.R;
import tr.com.agem.arya.interpreter.components.AryaLabel;
import tr.com.agem.arya.interpreter.components.AryaListCell;
import tr.com.agem.arya.interpreter.components.AryaListItem;
import tr.com.agem.arya.interpreter.components.ComponentFactory;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.components.base.AryaWindow;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.arya.interpreter.parser.AryaParserAttributes;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaComponent;
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

    public static void interpretResponse(AryaResponse response, AryaMain main) {

        if(AryaUtils.isNotEmpty(response.getView())) {

            if (main.getAryaWindow().getComponents() != null) {// TODO bu alan yönetilmeli neler kaldırılacak ekrandan

                while ((main.getAryaWindow().getChildCount()) != 1)
                    main.getAryaWindow().removeView(main.getAryaWindow().getChildAt(1));

            }

            AryaInterpreterHelper.drawView(response.getView(), main);
        }

        if(AryaUtils.isNotEmpty(response.getData()))
            AryaInterpreterHelper.populateView(response.getData(), main);

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

        ImageView image = new ImageView(main.getAryaWindow().getContext());
        image.setImageResource(R.drawable.agem_logo);
        image.setPadding(700, 0, 1, 0);

        main.getAryaWindow().addView(image);
    }


    public static void populateView(String responseData, AryaMain main) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(responseData);

            if (rootNode != null) {
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

                if (fields != null) {

                    while (fields.hasNext()) {
                        Map.Entry<String, JsonNode> entry = fields.next();


                        if("results".equals(entry.getKey().toString())){
                            if ("results".equals(entry.getKey().toString())) {

                                JSONArray jsonArray = new JSONArray(entry.getValue().toString());

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                                    String whatYouWantToFill = "list";// TODO liste datası olup olmadığı anlaşılmalı

                                    if ("list".equals(whatYouWantToFill)) {

                                        AryaListItem item = new AryaListItem(null,main);
                                        item.setComponentParent(getElementById("list", main));

                                        for (Iterator<?> iterator = jsonObj.keySet().iterator(); iterator.hasNext();) {///TODO (volkan)
                                            String key = (String) iterator.next();

                                            if (AryaUtils.isNotEmpty(jsonObj.get(key).toString())&&AryaUtils.isNotEmpty(getElementById(key, main))){
                                                AryaParserAttributes attr = new AryaParserAttributes();
                                                attr.setValue("id", key+""+(i));
                                                attr.setValue("label", jsonObj.get(key).toString());
                                                AryaListCell cell = new AryaListCell(attr,main,null);
                                                cell.setComponentParent(item);

                                            }
                                        }

                                    } else {
                                        for (Iterator<?> iterator = jsonObj.keySet().iterator(); iterator.hasNext();) {
                                            String key = (String) iterator.next();
                                            IAryaComponent c = (IAryaComponent) getElementById(key, main);

                                            if (AryaUtils.isNotEmpty(jsonObj.get(key).toString())&& AryaUtils.isNotEmpty(c))
                                                c.setComponentValue(jsonObj.get(key).toString());
                                        }

                                    }

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
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static Object getElementById(String id, AryaMain main) {

        View child = null;
        for (int i = 0; i < main.getAryaWindow().getChildCount(); i++) {

            child = main.getAryaWindow().getChildAt(i);
            if (child instanceof IAryaComponent) {
                IAryaComponent o = (IAryaComponent) child;

                if (id.equals(o.getComponentId())) {
                    return o;
                }
            }
        }
        return null;
    }

    public static String getjSessionId() {
        return jSessionId;
    }

    public static void setjSessionId(String jSessionId) {
        AryaInterpreterHelper.jSessionId = jSessionId;
    }

}