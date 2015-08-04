package tr.com.agem.arya.interpreter.zkoss;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import tr.com.agem.core.gateway.model.AryaRequest;

public class AryaInterpreterHelper 
{

	public static String callUrl(String url, AryaRequest request) 
	{
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type", "application/json");
		httppost.setHeader("Accept", "application/json");

		try {
//			StringBuffer str= new StringBuffer();
//			str.append("{\"action\":\"").append(action).append("\" ,")
//			.append("\"requestType\":\"").append(requestType).append("\"");
//
//			if (params != null && params.size() > 0) {
//				
//				str.append(", \"params\": { ");
//				
//				Collection<String> p = new ArrayList<String>();
//				
//				
//				for (Map.Entry<String, String> entry : params.entrySet()) {
//					StringBuffer paramStr = new StringBuffer();
//					paramStr.append("\"").append(entry.getKey()).append("\":").append("\"").append(entry.getValue()).append("\"");
//					p.add(paramStr.toString());
//				}
//				str.append(StringUtils.join(p, ","));
//				str.append("}");
//			}
//			
//			str.append("}");
//			
			StringEntity se = new StringEntity(request.toJSON());
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
