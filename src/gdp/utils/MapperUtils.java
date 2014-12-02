package gdp.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class MapperUtils {

	/*Clase encargada de realizar el marshal y unmarshal de entities-pojos*/
	static DozerBeanMapper mapper;
	
	private static Mapper getMapper() {
		if (mapper == null) {
			mapper = new DozerBeanMapper();
			List<String> myMappingFiles = new ArrayList<String>();
			myMappingFiles.add("dozerMappings.xml");
			mapper.setMappingFiles(myMappingFiles);
		}
		
		return mapper;
	}
	
	private MapperUtils() {
	}
	
	public static <T, U> List<U> map(final List<T> source, final Class<U> destType) {

	    final List<U> dest = new ArrayList<U>();

	    for (T element : source) {
	        dest.add(getMapper().map(element, destType));
	    }

	    return dest;
	}

	public static <T, U> U map(final T source, final Class<U> destType) {
		return getMapper().map(source, destType);
	}
	
	public static <T, U> List<U> map(final List<T> source, final Class<U> destType,final String mapId) {

	    final List<U> dest = new ArrayList<U>();

	    for (T element : source) {
	        dest.add(getMapper().map(element, destType,mapId));
	    }

	    return dest;
	}

	public static <T, U> U map(final T source, final Class<U> destType,final String mapId) {
		return getMapper().map(source, destType,mapId);
	}
	
	
}
