package gdp.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtils {

	/* Clase encargada de realizar el marshal y unmarshal de json-pojos.
	 * Como la serializacion y deserializacion de fechas y colecciones*/
	private static Gson gson;
	
	/**
	 * Devuelve un objeto Gson el cual permite parsear y formatear mensajes JSON
	 * 
	 * @return Gson
	 */
	public static Gson gson() {
		if ( gson == null ) {
			gson = new GsonBuilder().serializeNulls().setDateFormat("dd/MM/yyyy HH:mm:ss")
			.create();	
		}
		return gson;
	}

	private GsonUtils(){}
	
	public static String serializeDate(Date fecha){

		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		Long t = c.getTimeInMillis();
			
		return String.valueOf(t);	
	}
	
	public static Date deserializeDate(String dateStr){

		Long l = Long.parseLong(dateStr);
			
		return new Date(l);	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> deserializeJsonToCollection(String json, Object[] instance_DestType_Cache, T instance_DestType_Element) {
		
		try {
			Object[] list = GsonUtils.gson().fromJson(json, instance_DestType_Cache.getClass());
			List<Object> result = new ArrayList<Object>(Arrays.asList(list));
			return (new ArrayList<T>()).getClass().cast(result);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static <T> String serializeCollection(Collection<T> source) {
		Type collectionType = new TypeToken<Collection<T>>() {}.getType();
		
		return GsonUtils.gson().toJson(source, collectionType);
	}
	
	public static <T> T deserializeJsonToObject(String json, Class<T> destType) {
		return GsonUtils.gson().fromJson(json, destType);
	}
	
	public static <T> String serializeObject(T source) {
		String s = null;
		try {
			s = GsonUtils.gson().toJson(source);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
}