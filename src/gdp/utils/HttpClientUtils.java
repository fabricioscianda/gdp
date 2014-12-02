package gdp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpClientUtils {
	
	public static String convertStreamToString (InputStream is, String requestCharset) throws IOException {
		/*
		 * To convert the InputStream to String we use the Reader.read(char[]
		 * buffer) method. We iterate until the Reader return -1 which means
		 * there's no more data to read. We use the StringWriter class to
		 * produce the string.
		 */
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, requestCharset));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} catch(Exception e){
				e.printStackTrace();
				
			}finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public static String getBody (HttpServletRequest request) {
		try {
			return convertStreamToString(request.getInputStream(), "UTF-8");
		} catch (IOException e) {
			return null;
		}
	}

	public static JsonObject getObjectFromRequest (HttpServletRequest request) {
		String data = getBody(request);
		try {
			JsonParser p = new JsonParser();
			return p.parse(data).getAsJsonObject();
		} catch (Exception e) {
			return null;
		}
	}
	
}