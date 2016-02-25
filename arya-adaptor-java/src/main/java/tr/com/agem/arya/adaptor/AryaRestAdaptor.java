package tr.com.agem.arya.adaptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.arya.mapper.AryaRestMappedRequest;
import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.context.AttributeScope;
import tr.com.agem.core.gateway.model.IAryaRequest;
import tr.com.agem.core.metadata.exception.AryaAdaptorReqFailedException;

public class AryaRestAdaptor extends AryaApplicationAdaptor {

	private static final Logger logger = Logger.getLogger(AryaRestAdaptor.class.getName());

	@Override
	public IAryaAdaptorResponse processRequest(IAryaRequest aryaRequest) {

		AryaRestMappedRequest mappedRequest = (AryaRestMappedRequest) getMapper().map(aryaRequest.getAction());
		String jsonStr = (String) getConverter().convert(aryaRequest.getParams());

		logger.log(Level.INFO, "Calling rest URL: {0} with parameters: {1}",
				new Object[] { mappedRequest.getActionURL(), jsonStr });

		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;

		try {
			url = new URL(mappedRequest.getActionURL());

			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(jsonStr.getBytes(StandardCharsets.UTF_8));
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.log(Level.SEVERE, "HTTP error on: {0}", mappedRequest.getActionURL());
				throw new AryaAdaptorReqFailedException();
			}

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

			String output;
			StringBuilder sb = new StringBuilder("");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			conn.disconnect();

			logger.log(Level.INFO, "Response successful: {0}", sb.toString());

			AryaAdaptorResponse response = new AryaAdaptorResponse();
			response.setData(sb.toString());
			response.setViewName(mappedRequest.getViewName());

			return response;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public IAryaAdaptorResponse processLogin(IAryaRequest request) {
		return null;
	}

	@Override
	public IAryaAdaptorResponse processLogout(IAryaRequest request) {
		return null;
	}

	@Override
	public boolean checkLogin(IAryaRequest aryaRequest) {
		return false;
	}

	@Override
	public Object getAttributeValue(String attributeName, AttributeScope scope) {
		return null;
	}

}
