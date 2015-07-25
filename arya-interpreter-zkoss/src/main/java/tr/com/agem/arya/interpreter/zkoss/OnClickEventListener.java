package tr.com.agem.arya.interpreter.zkoss;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Textbox;

@SuppressWarnings("rawtypes")
public class OnClickEventListener implements EventListener {

	Component parent;
	String onClick;

	public OnClickEventListener(Component comp, String onClick) {
		super();
		this.parent = comp;
		this.onClick = onClick;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		HttpPost httppost = new HttpPost("http://localhost:8080/arya/rest/arya");
		httppost.setHeader("Content-Type", "application/json");
		httppost.setHeader("Accept", "application/json");

		StringEntity se = new StringEntity(
				"{\"window\":\"test\", \"action\":\"myAction\", \"params\":{\"username\":\"alio\"}}");
		httppost.setEntity(se);
		HttpClient httpClient = HttpClientBuilder.create().build();

		try {
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseString);
			
			Textbox tb = new Textbox();
			tb.setValue(responseString);
			tb.setParent(this.parent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
