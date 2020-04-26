package com.foryou.tax.util;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class JsonTools {
	/**
	 * 将json格式的字符串转换为map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 把JsonArray的字符串转换成List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> parseJsonArrayStrToListForMaps(String jsonArrayStr) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			if(jsonArrayStr != null) {

				JSONArray jsonArray = JSONObject.parseArray(jsonArrayStr);
				for(int j=0;j<jsonArray.size();j++) {
					Map<String, Object> map = new HashMap<>();
					JSONObject jsonObject = JSONObject.parseObject(Convert.toStr(jsonArray.get(j)));
					for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
						LoggerUtils.debug(JsonTools.class,"key is: " + entry.getKey() + " and value is: " + entry.getValue());
						map.put(entry.getKey(), entry.getValue());
					}
					list.add(map);
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 把JsonObject的字符串转换成Map<String, Object>
	 */
	public static Map<String, Object> parseJsonObjectStrToMap(String jsonObjectStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(jsonObjectStr != null) {
				JSONObject jsonObject = JSONObject.parseObject(jsonObjectStr);
				for(int j=0;j<jsonObject.size();j++) {
					for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
						map.put(entry.getKey(), entry.getValue());
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(map.size() == 0) {
			return null;
		}
		return map;
	}

	/**
	 * 把List<Map<String, Object>>的字符串转换成JsonArray
	 */
	public static String parseListForMapsToJsonArrayStr(List<Map<String, Object>> list) {
		String jsonArrayStr = null;
		if(list != null && list.size() != 0) {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			Object value = null;
			for(Map<String, Object> map : list) {
				jsonObject = new JSONObject();
				Set<String> set = map.keySet();
				for(String key : set) {
					value = map.get(key);
					if(value != null) {
						try {
							jsonObject.put(key, value.toString());
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				if(jsonObject.size() != 0) {
					jsonArray.add(jsonObject);
				}
			}
			jsonArrayStr = jsonArray.toString();
		}

		return jsonArrayStr;
	}

	/**
	 * 把Map<String, Object>的字符串转换成JsonObject
	 */
	public static String parseMapToJsonObjectStr(Map<String, Object> map) {
		String result = null;
		if (map != null && map.keySet().size() != 0) {
			Set<String> set = map.keySet();
			JSONObject jsonObject = new JSONObject();
			Object value = null;
			for (String key : set) {
				value = map.get(key);
				if (value != null) {
					try {
						jsonObject.put(key, value.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			if (jsonObject.size() != 0) {
				result = jsonObject.toString();
			}
		}
		return result;
	}
}
