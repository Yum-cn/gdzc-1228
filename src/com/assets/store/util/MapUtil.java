package com.assets.store.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Map工具类
 * 
 * @author Yum
 */
public class MapUtil extends org.apache.commons.collections.MapUtils {


	/**
	 * 得到类中的方法的值
	 * 
	 * @author Yum
	 */
	private static String getClazzPropertyValue(Object object, String proName) throws IllegalAccessException, InvocationTargetException {
		Class<?> clazz = (Class<?>) object.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("get")) {

				if (StringUtils.equalsIgnoreCase(proName, StringUtils.substringAfter(method.getName(), "get"))) {

					// System.out.println("value:" + method.invoke(object));//
					// 得到get方法的值
					return method.invoke(object) != null ? method.invoke(object) + "" : "";
				}
			}
		}
		return null;
	}
	
	/**
	 * Map排序
	 * @author   Yum
	 */
	public static Map<String, Object> sortMap(Map<String, Object> oldMap) {
		ArrayList<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(oldMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
			@Override
			public int compare(Entry<java.lang.String, Object> o1, Entry<java.lang.String, Object> o2) {
				return new Integer(o1.getKey()).compareTo(new Integer(o2.getKey()));
			}
		});
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			newMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return newMap;
	}
	
	/**
	 * 将List<Map<String, Object>>中，将mapKeyName、mapValueName健的值转换成Map的key、value
	 * @author   Yum
	 */
	public static Map<String,Object> listMapTransMap(List<Map<String, Object>> mapList,String mapKeyName,String mapValueName) {
		Map<String,Object> newMap = new HashMap<String,Object>();
		for (int i = 0; i < mapList.size(); i++) {
			
			Map<String, Object> sourceMap = mapList.get(i);
			if(sourceMap==null){
				continue;
			}
			newMap.put(String.valueOf(sourceMap.get(mapKeyName)), sourceMap.get(mapValueName));
		}
		return newMap;
	}
	/**
	 * Map中所有的key转换为key-List
	 * @author   Yum
	 */
	public static <T, V> T mapKeysToList(Map<String, V> map) {
		
		if(map==null){
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		for (Entry<String, V> entry : map.entrySet()) {
			String key = entry.getKey();
			list.add(key);
		}
		return (T) list;
		
	}
	
	
	
	/*public static String map2xml(Map<String, String> map) {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("xml");
		Set<String> keys = map.keySet();
		for (String key : keys) {
			root.addElement(key).addText(map.get(key));
		}
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw);
		xw.setEscapeText(false);
		try {
			xw.write(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}

	
	public static Map<String, String> xml2Map(String xml) throws IOException {
		System.out.println(xml);
		try {
			Map<String, String> maps = new HashMap<String, String>();
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List<Element> eles = root.elements();
			for (Element e : eles) {
				maps.put(e.getName(), e.getTextTrim());
			}
			return maps;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("abc", "123");
		map.put("abc", "234");
		System.out.println(map.toString());
	}

}
