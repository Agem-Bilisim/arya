package tr.com.agem.arya.gateway;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class AryaInterpreterHelper {

    private static final String TAG = "AryaInterpreterHelper";

    public static String callUrl(String url, AryaRequest request)
    {
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/json");
        httppost.setHeader("Accept", "application/json");

        try {
            StringEntity se = new StringEntity(request.toJSON());

            httppost.setEntity(se);
            // set the connection timeout value to 4 seconds (4000 milliseconds)
            final HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 4000);
            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            Log.d(TAG, ">>>>>>>>>>>>>postcodee:" + response.getStatusLine().getStatusCode());
            String responseStr = null;

            if(response.getStatusLine().getStatusCode() == 200) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            Log.d(TAG, ">>>>>>>>>>>>>postresponse:" + responseStr);

            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
