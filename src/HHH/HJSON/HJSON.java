package HHH.HJSON;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HJSON {
	private static ObjectMapper objectMapper=new ObjectMapper();
	
	static{
		objectMapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
	}
	
	
	public static String toJSON(Object o) throws JsonProcessingException{
		return objectMapper.writeValueAsString(o);
	}
	
	
	public static Map toMAP(String json) throws Exception{
		return objectMapper.readValue(json, Map.class);
	}
	
	
	public static List toList(String json) throws Exception{
		return objectMapper.readValue(json, List.class);
	}
	
	public static Object toBean(String json,Class classz) throws Exception{
		return objectMapper.readValue(json,classz);
	}
	
	
	public static JsonNode fromXML(String json) throws Exception{
		return objectMapper.readTree(json);
	}
	
	
}
