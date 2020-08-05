package com.chebei.user.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class ResultUtil {
	public static List<Map<String, Object>> failList(String... args) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(MapUtil.newHashMap(args));
		return list;
	}

	public static Map<String, Object> failMap(String... args) {
		return MapUtil.newHashMap(args);
	}

	public static boolean check(List<Map<String, Object>> list) {
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		if (list.size() == 0 && !"0".equals(list.get(0).get("error_no"))) {
			return false;
		}
		return true;
	}

	public static boolean check(Map<String, Object> map) {
		if (map== null || map.size() == 0) {
			return false;
		}
		if (!"0".equals(map.get("error_no")) && StringUtils.isNotBlank((String) map.get("error_no"))) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(failList("error_no", "error_info", "-1", "未查询到数据"));
		System.out.println(failMap("error_no", "error_info", "-1", "未查询到数据"));
	}

	public static Map<String, Object> success(String retMsg) {
		return MapUtil.newHashMap("error_no", "error_info", "0", retMsg);
	}
}
