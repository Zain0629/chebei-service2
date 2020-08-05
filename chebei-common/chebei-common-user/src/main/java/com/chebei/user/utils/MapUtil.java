package com.chebei.user.utils;

import com.chebei.ams.utils.MapFactory;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标题：MapUtils.java<br>
 * 功能说明：<br>
 * 系统版本：v1.0<br>
 * 开发人员：kinkaid.yu<br>
 * 开发时间：2016年5月25日 下午5:04:36<br>
 * 功能描述：<br>
 */
public class MapUtil extends MapUtils {

	public static Map<String, String> put(Map<String, String> map, String... args) {
		if (args.length % 2 != 0) {
			return map;
		}
		int c = args.length / 2;
		for (int i = 0; i < c; i++) {
			map.put(args[i], args[c + i]);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> newHashMap(Object... args) {
		HashMap<String, T> ret = new HashMap<>();
		int c = args.length / 2;
		for (int i = 0; i < c; i++) {
			ret.put((String) args[i], (T) args[c + i]);
		}
		return ret;
	}

	public static Map<String, Object> copyMap(Map<String, Object> from, String... keys) {
		Map<String, Object> map = newHashMap();
		if (keys.length == 0) {
			map.putAll(from);
		} else {
			for (String key : keys) {
				map.put(key, from.get(key));
			}
		}
		return map;
	}

	public static Map<String, Object> copyMap(Map<String, Object> from, Map<String, Object> to, String... keys) {
		Map<String, Object> map = to;
		if (keys.length == 0) {
			map.putAll(from);
		} else {
			for (String key : keys) {
				map.put(key, from.get(key));
			}
		}
		return map;
	}

	/**
	 * 只比较基本数据类型
	 * @param changeMap 修改后参数
	 * @param oldMap    原参数
	 * @param keyMap    具体需要比较的参数，{key:参数名称}
	 * @return
	 */
	public static  List<Map<String, Object>> getChangeList(Map<String, Object> changeMap, Map<String, Object> oldMap, Map<String, Object> keyMap) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (Map.Entry<String, Object> entry : keyMap.entrySet()) {
			String key = entry.getKey();
			String value1Str = MapUtils.getString(changeMap, entry.getKey());
			String value2Str = MapUtils.getString(oldMap, entry.getKey());
			if (null == value1Str) {
				value1Str = "";
			}
			if (null == value2Str) {
				value2Str = "";
			}
			if (!value1Str.equals(value2Str)) {
				list.add(MapFactory.map("code", key, "name", entry.getValue(), "before", value2Str, "after", value1Str));
			}
		}
		return list;
	}

}
