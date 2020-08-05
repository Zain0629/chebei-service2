package com.chebei.user.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
    /**
     * 系统登录
     * @param map
     * @return
     */
    public Map<String,Object> login(Map<String,Object> map);
    
//    /**
//     * 系统登出
//     * @param map
//     * @return
//     */
//    public Map<String,Object> logout(Map<String,Object> map);
//
//    /**
//     * 查询用户信息
//     * @param map
//     * @return
//     */
//    public Map<String,Object> queryUserByToken(Map<String,Object> map);
//
//    /**
//     * 查询用户角色
//     * @param map
//     * @return
//     */
//    public List<Map<String, Object>> queryUserRole(Map<String,Object> map);
//
//    /**
//     * 查询用户在线的登录token
//     * @param params
//     * @return
//     */
//	List<Map<String, Object>> queryOnlineUserToken(Map<String, Object> params);
//
//    /**
//     * 查询用户所有的登录token
//     * @param params
//     * @return
//     */
//    public List<Map<String, Object>> queryAllUserToken(Map<String, Object> params);
//
//    /**
//     * 查询用户权限
//     * @param params
//     * @return
//     */
//    public List<Map<String, Object>> queryUserRight(Map<String, Object> params);
//
//
//
//
//
//    /**
//     * 会话校验接口
//     * @param map
//     * @return
//     */
//    public Map<String,Object> checkSession(Map<String,Object> map);
//
//
//	/**
//	 * 客户端免登录接口(1000307)
//	 *
//	 * @param map
//	 * @return
//	 */
//	public Map<String, Object> checkSessionByToken(Map<String, Object> map);

}
