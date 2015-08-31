package tr.com.agem.arya.interpreter.zkoss;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import tr.com.agem.core.gateway.model.AryaRequest;

public class AryaInterpreterHelper {

	public static String callUrl(String url, AryaRequest request) {
		return callUrl(url, request.toJSON());
	}

	public static String callUrl(String url, String request) {
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type", "application/json");
		httppost.setHeader("Accept", "application/json");

		try {
			StringEntity se = new StringEntity(request);
			httppost.setEntity(se);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
