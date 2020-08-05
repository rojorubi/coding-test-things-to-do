package com.mimacom.infrastructure.services;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
private static final ObjectMapper mapper = new ObjectMapper();
	
	private JsonUtil() {
		
	}
	
	public static boolean isEmptyArray(final ArrayNode data) {
		boolean result;
		if(data == null) {
			result = true;
		}
		else {
			result = (data.size() == 0);
		}
		return result;
	}
	
	public static String getValueAsString(final JsonNode data, String field) {
		if(data == null || field == null) {
			return null;
		}
		String result = null;
		JsonNode e = data.get(field);

		if(e!=null && e.isValueNode()) {
			result = e.asText();
		}
		else if(e!=null){
			result = e.toString();
		}
				
		return result;
	}
	
	public static Long getValueAsLong(final JsonNode data, String field) {
		if(data == null || field == null) {
			return null;
		}
		
		JsonNode e = data.get(field);
		if(e == null) {
			return null;
		}
		else {
			return e.asLong();
		}
	}

	public static Integer getValueAsInteger(final JsonNode data, String field) {
		Long value = getValueAsLong(data, field);
		if(value == null) {
			return null;
		}
		return Integer.valueOf(value.intValue());
	}
	
	public static Boolean getValueAsBoolean(final JsonNode data, String field) {
		if(data == null || field == null) {
			return null;
		}
		
		JsonNode e = data.get(field);
		if(e == null) {
			return null;
		}
		else {
			return e.asBoolean();
		}
	}
	
	public static JsonNode copy(JsonNode src, JsonNode dst, boolean copyNullValues) {
		if(src == null) return dst;
		
		if(src.isArray()) {
			if(dst == null) {
				dst = mapper.createArrayNode();
			}
			copyArrayNode((ArrayNode)src,(ArrayNode)dst, copyNullValues);
		}
		else {
			if(dst == null) {
				dst = mapper.createObjectNode();
			}
			copyObjectNode((ObjectNode)src,(ObjectNode)dst, copyNullValues);
		}
		
		return dst;
	}
	
	private static ObjectNode copyObjectNode(ObjectNode src, ObjectNode dst, boolean copyNullValues) {
		if(src == null) return dst;
		
		if(dst == null) return src;
		
		Iterator<String> itFields = src.fieldNames();
		while(itFields.hasNext()) {
			String fieldName = itFields.next();
			JsonNode value = src.get(fieldName);
			if(value!=null && !value.isMissingNode()) {
				JsonNode valueDst = dst.get(fieldName);
				
				if(value.isNull() && valueDst == null) {
					dst.replace(fieldName, value);
				}
				else if(value.isValueNode() && (!value.isNull() || copyNullValues)) {					
					dst.replace(fieldName, value);
				}
				else if (value.isArray()) {
					if(valueDst == null) {
						dst.replace(fieldName, mapper.createArrayNode());
						valueDst = dst.get(fieldName);
					}
					copyArrayNode((ArrayNode)value, (ArrayNode)valueDst,copyNullValues);
				}
				else if (value.isObject()){
					if(valueDst == null) {
						dst.replace(fieldName, mapper.createObjectNode());
						valueDst = dst.get(fieldName);
					}
					copyObjectNode((ObjectNode)value, (ObjectNode)valueDst, copyNullValues);
				}				
			}
		}
		
		return dst;
	}

	private static ArrayNode copyArrayNode(ArrayNode src, ArrayNode dst, boolean copyNullValues) {
		if(src == null) return dst;
		
		if(dst == null) return src;
		
		
		for(int index = 0; index < src.size(); index ++) {
			JsonNode value = src.get(index);
			JsonNode valueDst = dst.get(index);
				
			if(value.isValueNode()) {
				if(valueDst == null) {
					dst.add(value);
				}
				else {
					dst.remove(index);
					dst.insert(index, value);					
				}
			}
			else if (value.isArray()) {
				if(valueDst == null) {
					valueDst = dst.addArray();
				}
				copyArrayNode((ArrayNode)value, (ArrayNode)valueDst, copyNullValues);
			}
			else if (value.isObject()){
				if(valueDst == null) {
					valueDst = dst.addObject();
				}
				copyObjectNode((ObjectNode)value, (ObjectNode)valueDst, copyNullValues);
			}
			
		}
		return dst;
	}
}
