package tr.com.agem.filter.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AryaRequestWrapper extends HttpServletRequestWrapper {
	
	private final String requestBody;

	public AryaRequestWrapper(HttpServletRequest request, String name, String value) {
		super(request);
		
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {

			InputStream inputStream = request.getInputStream();
			
			if (inputStream != null) {
				
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				stringBuilder = stringBuilder.append(", \"attributeName\": \"")
								.append(name)
								.append("\", \"attributeValue\": \"")
								.append(value)
								.append("\" }");
				System.out.println("requestwrapper<<<<<<"+stringBuilder.toString());
			} else {
				// make an empty string since there is no payload
				stringBuilder.append("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException iox) {
				}
			}
		}

		requestBody = stringBuilder.toString();
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());
		ServletInputStream inputStream = new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
		return inputStream;
	}


}
