package tr.com.agem.arya.gateway;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;
import tr.com.agem.core.gateway.model.AryaRequest;

public class AryaInterpreterHelper {

    private static final String TAG = "AryaInterpreterHelper";

    public static String callUrl(String url, AryaRequest request)
    {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            // set the connection timeout value to 4 seconds (4000 milliseconds)
            urlConnection.setConnectTimeout(4000);

            urlConnection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.writeBytes(request.toJSON());
            writer.flush();
            writer.close();

            int responseCode = urlConnection.getResponseCode();
            Log.d(TAG, ">>>>>>>>>>>>>responseCode:" + responseCode);
            String responseStr = null;

            if(responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line = null;
                StringBuffer responseText = new StringBuffer();
                while( (line = reader.readLine()) != null )
                {
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

        return  null;
    }
}