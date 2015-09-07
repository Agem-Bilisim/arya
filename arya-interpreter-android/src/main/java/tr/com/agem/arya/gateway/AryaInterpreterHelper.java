package tr.com.agem.arya.gateway;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import tr.com.agem.arya.interpreter.components.AryaWindow;
import tr.com.agem.arya.interpreter.parser.AryaMetadataParser;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;

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
            // set the connection timeout value to 4 seconds (4000 milliseconds)
            urlConnection.setConnectTimeout(4000);

            urlConnection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.writeBytes(request);
            writer.flush();
            writer.close();

            int responseCode = urlConnection.getResponseCode();
            Log.d(TAG, ">>>>>>>>>>>>>responseCode:" + responseCode);
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
            //Log.d(TAG, ">>>>>>>>>>>>>responseString:" + responseStr);
            return responseStr;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void interpretResponse(AryaResponse response, Context context, AryaWindow aryaWindow) {


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;

        try {
            parser = saxParserFactory.newSAXParser();
            parser.parse(new InputSource(new StringReader(response.getView())), new AryaMetadataParser(context,aryaWindow));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*// Remove previous components before adding new ones!
        aryaWindow.removeAllViews();

        XmlPullParser xpp = Xml.newPullParser();
        AryaMetadataParser parser = new AryaMetadataParser(context, aryaWindow);
        try {
            // Handle view metadata
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xpp.setInput(new StringReader(response.getView()));

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    parser.startElement(xpp.getName(), xpp);
                } else if (eventType == XmlPullParser.END_TAG) {
                    parser.endElement(xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    parser.characters(xpp.getText());
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}