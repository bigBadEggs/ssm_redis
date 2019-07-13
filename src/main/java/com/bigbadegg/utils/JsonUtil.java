package com.bigbadegg.utils;

import net.sf.json.JSONObject;

/**
 * json工具类
 * @author bigBa
 *
 */
public class JsonUtil {

	/**
	 * 对象转json字符串
	 * @param obj
	 * @return
	 */
	public static String objToString(Object obj){
		return JSONObject.fromObject(obj).toString();
	}
	
	/**
	 * json字符串转对象
	 * @param <T>
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T jsonToObj(Object json, Class<T> cls){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return (T) JSONObject.toBean(jsonObject, cls);
	}
}
