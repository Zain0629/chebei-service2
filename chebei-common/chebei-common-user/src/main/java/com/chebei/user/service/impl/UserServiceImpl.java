package com.chebei.user.service.impl;

import com.chebei.ams.func.service.CommonExecuteService;
import com.chebei.ams.processor.ext.db.service.CommonMapperService;
import com.chebei.user.mapper.TsysUserMapper;
import com.chebei.user.service.PasswordValidator;
import com.chebei.user.service.UserService;
import com.chebei.user.utils.ResultUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	@Qualifier("commonMapperService")
	CommonMapperService commonMapperService;

	@Autowired
	PasswordValidator passwordValidator;

	@Autowired
	CommonExecuteService commonExecuteServiceImpl;

	@Autowired
	TsysUserMapper tsysUserMapper;

//	@Value("${user.useSSO}")
	String useSSO;

	String registerBizType = "YCB-REGISTER";
	String resetPasswordBizType = "YCB-RESETPASSWORD";
	String bindBizType = "YCB-BIND";

	/**
	 * 系统登录 功能号 1000300
	 *
	 * @return error_info 错误信息
	 * @param1 user_code
	 * 用户编号或者手机号（必传）
	 * @param1 login_pwd
	 * 登录密码（必传）
	 * @param1 terminal_type
	 * 设备类型（必传）
	 * @param1 terminal_version
	 * 版本号（必传）
	 * @param1 imei
	 * imei（选传）
	 */
	@Override
	public Map<String, Object> login(Map<String, Object> params) {
		params.put("error_no", "0");
		params.put("error_info", "");
		String userCode = MapUtils.getString(params, "user_code");
		String terminalType = MapUtils.getString(params, "terminal_type");
		String password = MapUtils.getString(params, "login_pwd");
		if (StringUtils.isBlank(userCode)) {
			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入登录账号或者手机号");
		}
		if (StringUtils.isBlank(terminalType) && StringUtils.isBlank(terminalType)) {
			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入设备类型");
		}
		if (StringUtils.isBlank(password)) {
			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入登录密码");
		}
		tsysUserMapper.selectByPrimaryKey("1") ;
		return params;
	}

//	/**
//	 * 系统登出 功能号 1000301
//	 *
//	 * @return error_info 错误信息
//	 * @param1 user_code
//	 * 用户编号（必传）
//	 * @param1 token
//	 * 会话token（必传）
//	 */
//	@Override
//	public Map<String, Object> logout(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		String token = MapUtils.getString(params, "token");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户账号");
//		}
//		if (StringUtils.isBlank(token)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户token");
//		}
//
//		commonMapperService.updateObject("TsysUser.userLogout", params);
//		return params;
//	}
//
//	/**
//	 * 查询用户信息 功能号 1000302
//	 *
//	 * @return error_info 错误信息
//	 * @param1 token
//	 * 会话TOKEN（必传）
//	 */
//	@Override
//	public Map<String, Object> queryUserByToken(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		Map<String, Object> tokenMap = checkSession(params);
//		if (!ResultUtil.check(tokenMap)) {
//			return tokenMap;
//		}
//		params.put("user_code", tokenMap.get("user_code"));
//		params.put("lock_status", "0");
//		Map<String, Object> ret = null;
//		Map<String, Object> user = commonMapperService.queryForMap("TsysUser.userSearch", params);
//		if (user == null) {
//			ret = ResponseUtil.fail("-1", "系统用户不存在");
//		} else {
//			ret = new HashMap<>();
//			ret.putAll(tokenMap);
//			ret.putAll(user);
//			ret.put("roleList", JSONArray.toJSONString(queryUserRole(params)));
//			ret.put("error_no", "0");
//			ret.put("error_info", "");
//			ret.put("token", tokenMap.get("token"));
//			String currRoleCode = MapUtils.getString(tokenMap, "curr_role_code");
//			ret.put("curr_role_code", currRoleCode);
//			RoleEnum role = RoleEnum.from(currRoleCode);
//			if (role != null) {
//				ret.put("curr_role_type", role.getRoleType().getName());
//				ret.put("curr_role_name", role.getName());
//			}
//		}
//		return ret;
//	}
//
//	@Override
//	public List<Map<String, Object>> queryUserRole(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failList("error_no", "error_info", "-1", "请输入用户编号");
//		}
//		return commonMapperService.queryForList("TsysUser.userRoleSearch", params);
//	}
//
//	@Override
//	public List<Map<String, Object>> queryAllUserToken(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failList("error_no", "error_info", "-1", "请输入用户编号");
//		}
//		return commonMapperService.queryForList("TsysUser.userTokenAllSearch", params);
//	}
//
//	@Override
//	public List<Map<String, Object>> queryOnlineUserToken(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		String terminalType = MapUtils.getString(params, "terminal_type");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failList("error_no", "error_info", "-1", "请输入用户编号");
//		}
//		if (!"16".equals(terminalType)) {
//			return commonMapperService.queryForList("TsysUser.onlineUserTokenSearchNoWeb", params);
//		} else {
//			return commonMapperService.queryForList("TsysUser.onlineUserTokenSearch", params);
//		}
//	}
//
//	private Map<String, Object> queryUserTokenByToken(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		// String userCode = MapUtils.getString(params, "user_code");
//		// if(StringUtils.isBlank(userCode)){
//		// return ResultUtil.failList("error_no","error_info","-1","请输入用户编号");
//		// }
//		String token = MapUtils.getString(params, "token");
//		if (StringUtils.isBlank(token)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户token");
//		}
//		return commonMapperService.queryForMap("TsysUser.userTokenSearchByToken", params);
//	}
//
//	/**
//	 * 查询用户权限 功能号 1000307
//	 *
//	 * @return error_info 错误信息
//	 * @param1 user_code
//	 * 用户编号（必传）
//	 */
//	@Override
//	public List<Map<String, Object>> queryUserRight(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failList("error_no", "error_info", "-1", "请输入用户编号");
//		}
//		List<Map<String, Object>> list = commonMapperService.queryForList("TsysUser.userRightSearch", params);
//		if (CollectionUtils.isEmpty(list)) {
//			return ResultUtil.failList("error_no", "error_info", "-1", "未查询到用户权限");
//		}
//		return list;
//	}
//
//	/**
//	 * 用户修改密码 功能号 1000303
//	 *
//	 * @return error_info 错误信息
//	 * @param1 user_code
//	 * 用户编号/手机号（必传）
//	 * @param1 login_pwd
//	 * 原登录密码（必传）
//	 * @param1 login_pwd_new
//	 * 新登录密码（必传）
//	 */
//	@Override
//	public Map<String, Object> modifyPwd(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		String password = MapUtils.getString(params, "login_pwd");
//		String passwordNew = MapUtils.getString(params, "login_pwd_new");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户编号");
//		}
//		if (StringUtils.isBlank(password)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入密码");
//		}
//		password = passwordValidator.digest(password);
//		params.put("login_pwd", password);
//		params.put("lock_status", "0");
//		Map<String, Object> result = commonMapperService.queryForMap("TsysUser.userSearch", params);
//		if (MapUtils.isEmpty(result)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "原始密码错误！");
//		} else {
//			password = passwordValidator.digest(passwordNew);
//			params.put("login_pwd", password);
//			int count1 = commonMapperService.updateObject("TsysUser.resetPwd", params);
//			if (count1 == 0) {
//				return ResultUtil.failMap("error_no", "error_info", "-1", "修改密码失败！");
//			}
//			/* 下线其他/所有设备 */
//			// int count2 =
//			commonMapperService.updateObject("TsysUser.updateUserOtherToken", params);
//		}
//		return params;
//	}
//
//	/**
//	 * 重置密码验证码获取 功能号 1000304
//	 *
//	 * @return error_info 错误信息
//	 * @param1 user_code
//	 * 用户编号（必传）
//	 * @param1 client_ip
//	 * 客户端IP地址（选传）
//	 */
//	@Override
//	public Map<String, Object> sendResetSmsCode(Map<String, Object> params) {
//		String userCode = MapUtils.getString(params, "user_code");
//		String clientIp = MapUtils.getString(params, "client_ip");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户账号或者手机号");
//		}
//		if (StringUtils.isBlank(clientIp)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入客户端IP地址");
//		}
//		params.put("reset_password", "1");
//		Map<String, Object> user = commonMapperService.queryForMap("TsysUser.userSearch", params);
//		if (MapUtils.isEmpty(user)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "未找到用户记录或者用户被禁用");
//		}
//		String mobile = MapUtils.getString(user, "mobile_tel");
//		if (StringUtils.isBlank(mobile)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "该用户尚未设置手机号码");
//		}
//		try {
//			Map<String, Object> callMap = MapFactory.map("channel_id", SystemConfig.instance().getSmsChannel(),
//					"biz_type", resetPasswordBizType, "mobile", mobile, "content",
//					"您好！重置密码：${code}。请保密并确认本人操作！如有疑问请联系管理员", "client_ip", clientIp);
//
//			Map<String, Object> res = commonExecuteServiceImpl.executeMap(1800002, callMap);
//			String errorNo = MapUtils.getString(res, ColumnConst.ERROR_NO);
//			if (!"0".equals(errorNo)) {
//				return ResponseUtil.fail(errorNo, MapUtils.getString(res, ColumnConst.ERROR_INFO));
//			}
//			return res;
//		} catch (FuncCallException e) {
//			log.error(e.getMessage());
//			return ResultUtil.failMap("error_no", "error_info", e.getErrorNo(), e.getMessage());
//		} catch (Exception e1) {
//			log.error(e1.getMessage());
//			return ResultUtil.failMap("error_no", "error_info", "-1", "短信发送服务失败");
//		}
//	}
//
//	/**
//	 * 用户重置密码 功能号 1000305
//	 *
//	 * @return error_info 错误信息
//	 * @param1 user_code
//	 * 用户编号（必传）
//	 * @param1 login_pwd
//	 * 新登录密码（必传）
//	 * @param1 captcha
//	 * 短信验证码（必传）
//	 * @param1 unified_id
//	 * 统一认证标识（必传）
//	 */
//	@Override
//	public Map<String, Object> resetPwd(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String userCode = MapUtils.getString(params, "user_code");
//		String loginPwd = MapUtils.getString(params, "login_pwd");
//		params.remove("login_pwd");
//		if (StringUtils.isBlank(userCode)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户账号");
//		}
//		if (StringUtils.isBlank(loginPwd)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入新的密码");
//
//		}
//		String captcha = MapUtils.getString(params, "captcha");
//		if (StringUtils.isBlank(captcha)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "验证码参数为空");
//		}
//		String unifiedId = MapUtils.getString(params, "unified_id");
//		if (StringUtils.isBlank(unifiedId)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "统一认证标识参数为空");
//		}
//		params.put("reset_password", "1");
//		List<Map<String, Object>> userList = commonMapperService.queryForList("TsysUser.userSearch", params);
//		if (userList == null || userList.size() == 0) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "未找到用户记录或者用户被禁用");
//		}
//		if (userList.size() > 1) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "绑定手机号码重复，不能使用手机号重置密码");
//		}
//		Map<String, Object> user = userList.get(0);
//		String mobile = MapUtils.getString(user, "mobile_tel");
//		if (StringUtils.isBlank(mobile)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "该用户尚未设置手机号码");
//		}
//		try {
//			Map<String, Object> callMap = MapFactory.map("channel_id", SystemConfig.instance().getSmsChannel(),
//					"biz_type", resetPasswordBizType, "mobile", mobile, "unified_id", unifiedId, "unified_value",
//					captcha);
//
//			Map<String, Object> res = commonExecuteServiceImpl.executeMap(1800003, callMap);
//			String errorNo = MapUtils.getString(res, ColumnConst.ERROR_NO);
//			if (!"0".equals(errorNo)) {
//				return ResponseUtil.fail(errorNo, MapUtils.getString(res, ColumnConst.ERROR_INFO));
//			}
//		} catch (FuncCallException e) {
//			log.error(e.getMessage());
//			return ResultUtil.failMap("error_no", "error_info", e.getErrorNo(), e.getMessage());
//		} catch (Exception e1) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "短信验证服务失败：" + e1.getMessage());
//		}
//		String userCode1 = MapUtils.getString(user, "user_code");
//		params.put("user_code", userCode1);
//		String password = passwordValidator.digest(loginPwd);
//		params.put("login_pwd", password);
//		int count1 = commonMapperService.updateObject("TsysUser.resetPwd", params);
//		if (count1 == 0) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "重置密码失败！");
//		}
//		// 设置最后一个登录记录的状态为登录正常
//		commonMapperService.updateObject("TsysUser.updateLoginLogFirstRecord", params);
//
//		/* 下线其他/所有设备 */
//		// int count2 =
//		commonMapperService.updateObject("TsysUser.updateUserOtherToken", params);
//		return params;
//	}
//
//	/**
//	 * 会话校验接口 功能号 1000306
//	 *
//	 * @return error_info 错误信息
//	 * @param1 token
//	 * 会员token（必传）
//	 */
//	@Override
//	public Map<String, Object> checkSession(Map<String, Object> params) {
//		params.put("error_no", "0");
//		params.put("error_info", "");
//		String token = MapUtils.getString(params, "token");
//		if (StringUtils.isBlank(token)) {
//			return ResultUtil.failMap("error_no", "error_info", "-1", "请输入用户token");
//		}
//		/* 查询登录token */
//		Map<String, Object> tokenMap = queryUserTokenByToken(params);
//		if (!ResultUtil.check(tokenMap)) {
//			return ResultUtil.failMap("error_no", "error_info", "-403", "尚未登录");
//		} else {
//			String status = MapUtils.getString(tokenMap, "status");
//			long differ = MapUtils.getLongValue(tokenMap, "gmt_differ");
//			if ("1".equals(status) && differ <= 0) {
//				/* token失效 */
//				int count = commonMapperService.updateObject("TsysUser.userLogout", tokenMap);
//				if (count == 0) {
//					log.info("userLogout update ret = " + count);
//				}
//				return ResultUtil.failMap("error_no", "error_info", "-403", "登录过期");
//			} else if ("0".equals(status)) {
//				return ResultUtil.failMap("error_no", "error_info", "-403", "登录过期");
//			}
//		}
//		params.put("user_code", tokenMap.get("user_code"));
//		params.put("curr_role_code", tokenMap.get("curr_role_code"));
//		params.put("terminal_type", tokenMap.get("terminal_type"));
//		params.put("terminal_version", tokenMap.get("terminal_version"));
//		return params;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * com.chebei.carloan.service.user.UserService#checkSessionByToken(java.util.
//	 * Map)
//	 */
//	@Override
//	public Map<String, Object> checkSessionByToken(Map<String, Object> params) {
//		Map<String, Object> ret = new HashMap<String, Object>();
//		ret.put("error_no", "0");
//		ret.put("error_info", "");
//
//		String token = MapUtils.getString(params, "token");
//		String terminalType = MapUtils.getString(params, "terminal_type");
//		ret.put("update_url",
//				TerminalTypeEnum.Android.getCode().equals(terminalType)
//						? SystemConfig.instance().get("version.download.android")
//						: SystemConfig.instance().get("version.download.iphone"));
//
//		Version terminalVersion = new Version(MapUtils.getString(params, "terminal_version"));
//		Version newVersion = new Version(TerminalTypeEnum.Android.getCode().equals(terminalType)
//				? SystemConfig.instance().get("version.upgrade.android")
//				: SystemConfig.instance().get("version.upgrade.iphone"));
//		Version unsupportedVersion = new Version(TerminalTypeEnum.Android.getCode().equals(terminalType)
//				? SystemConfig.instance().get("version.enforce.android")
//				: SystemConfig.instance().get("version.enforce.iphone"));
//		if (terminalVersion.compareTo(unsupportedVersion) < 0) {
//			ret.put("update_type", "2");
//		} else if (terminalVersion.compareTo(newVersion) < 0) {
//			ret.put("update_type", "1");
//		} else {
//			ret.put("update_type", 0);
//		}
//
//		if (StringUtils.isBlank(token)) {
//			ret.put("error_no", "-403");
//			ret.put("error_info", "用户会话超时");
//			return ret;
//		}
//		/* 查询登录token */
//		Map<String, Object> tokenMap = queryUserTokenByToken(params);
//		if (!ResultUtil.check(tokenMap)) {
//			// return ResultUtil.failMap("error_no", "error_info", "-1", "尚未登录");
//			ret.put("error_no", "-403");
//			ret.put("error_info", "用户会话超时");
//			return ret;
//		}
//
//		String status = MapUtils.getString(tokenMap, "status");
//		long differ = MapUtils.getLongValue(tokenMap, "gmt_differ");
//		if ("1".equals(status) && differ <= 0) {
//			/* token失效 */
//			int count = commonMapperService.updateObject("TsysUser.userLogout", tokenMap);
//			if (count == 0) {
//				log.info("userLogout update ret = " + count);
//			}
//			// return ResultUtil.failMap("error_no", "error_info", "-1", "登录过期");
//			ret.put("error_no", "-403");
//			ret.put("error_info", "用户会话超时");
//			return ret;
//		} else if ("0".equals(status)) {
//			// return ResultUtil.failMap("error_no", "error_info", "-1", "登录过期");
//			ret.put("error_no", "-403");
//			ret.put("error_info", "用户会话超时");
//			return ret;
//		}
//		Map<String, Object> map = MapFactory
//				.map("user_code", MapUtils.getString(tokenMap, "user_code"), "lock_status", "0");
//		List<Map<String, Object>> userList = commonMapperService.queryForList("TsysUser.userSearch", map);
//		if (userList == null || userList.size() == 0) {
//			// return ResultUtil.failMap("error_no", "error_info", "-1", "无效的用户编号或手机号");
//			ret.put("error_no", "-403");
//			ret.put("error_info", "无效的用户编号或手机号");
//			return ret;
//		}
//
//		if (userList.size() > 1) {
//			// return ResultUtil.failMap("error_no", "error_info", "-1",
//			// "绑定手机号码重复，不能使用手机号登录");
//			ret.put("error_no", "-403");
//			ret.put("error_info", "绑定手机号码重复，不能使用手机号登录");
//			return ret;
//		}
//
//		Map<String, Object> user = userList.get(0);
//		String lockStatus = MapUtils.getString(user, "lock_status");
//		if (!"0".equals(lockStatus)) {
//			// String errorInfo = SystemDict.instance().get("SYS_LOCK_STATUS", lockStatus);
//			// return ResultUtil.failMap("error_no", "error_info", "-1", errorInfo);
//			ret.put("error_no", "-403");
//			ret.put("error_info", SystemDict.instance().get("SYS_LOCK_STATUS", lockStatus));
//			return ret;
//		}
//
//		String userStatus = MapUtils.getString(user, "user_status");
//		if (!"0".equals(userStatus)) {
//			// String errorInfo = SystemDict.instance().get("SYS_USER_STATUS", userStatus);
//			// return ResultUtil.failMap("error_no", "error_info", "-1", errorInfo);
//			ret.put("error_no", "-403");
//			ret.put("error_info", SystemDict.instance().get("SYS_USER_STATUS", userStatus));
//			return ret;
//		}
//		/* 登陆日志 */
//		params.put("mobile_tel", MapUtils.getString(user, "mobile_tel"));
//		commonMapperService.insertObject("TsysUser.insertMobileLoginLog", params);
//
//		ret.put("token", token);
//		ret.put("mobile_tel", MapUtils.getString(user, "mobile_tel"));
//		ret.put("role_code", tokenMap.get("curr_role_code"));
//		ret.put("user_code", MapUtils.getString(user, "user_code"));
//		ret.put("user_name", MapUtils.getString(user, "user_name"));
//		ret.put("user_status", MapUtils.getString(user, "user_status"));
//		ret.put("lock_status", MapUtils.getString(user, "lock_status"));
//		ret.put("invite_code", MapUtils.getString(user, "invite_code"));
//
//		String orgId = MapUtils.getString(user, "org_id");
//		Map<String, Object> orgInfo = commonMapperService.queryForObject("TsysUser.getOrgInfo", MapFactory.map("org_id", orgId));
//		ret.put("branch_no", orgId);
//		ret.put("branch_name", MapUtils.getString(orgInfo, "org_name"));
//		//取担保公司
//		String guaranteeNo = MapUtils.getString(orgInfo, "guarantee_no");
//		if (StringUtil.isNotBlank(guaranteeNo)) {
//			Map<String, Object> guaranteeMap = userDaoImpl.getGuaranteeMap(guaranteeNo);
//			ret.put("guarantee_name", guaranteeMap.get("grant_name"));
//		} else {
//			ret.put("guarantee_name", "系统管理");
//		}
//
//		List<Map<String, Object>> roleList = queryUserRole(
//				MapFactory.map("user_code", MapUtils.getString(tokenMap, "user_code")));
//		ret.put("roleList", roleList);
//
//		if (!"false".equals(useSSO)) {
//			/* 查询所有在线登录设备 */
//			params.put("tokenList", queryOnlineUserToken(
//					MapFactory.map("user_code", MapUtils.getString(ret, "user_code"), "terminal_type", terminalType)));
//		}
//
//		return ret;
//	}
//
//	@Override
//	public Map<String, Object> setUserRole(Map<String, Object> map) {
//		String token = MapUtils.getString(map, "token");
//		String userRole = MapUtils.getString(map, "user_role");
//		if (commonMapperService.updateObject("TsysUser.updateUserTokenRole",
//				MapFactory.map("token", token, "user_role", userRole)) > 0) {
//			return ResponseUtil.success();
//		}
//		return ResponseUtil.fail("-1", "设置角色失败");
//	}
//
//	@Override
//	public Map<String, Object> queryLoginUser(Map<String, Object> params) {
//		UserInfo userInfo = UserInfo.getUserInfo(params);
//		Map<String, Object> param = new HashMap<>();
//		param.put("user_id", userInfo.getUserId());
//		param.put("user_name", userInfo.getUserName());
//		param.put("branch_no", userInfo.getBranchNo());
//		param.put("user_role", userInfo.getUserRole());
//		param.put("user_role_name", userInfo.getUserRoleName());
//		Map<String, Object> map = commonMapperService.queryForMap("BranchMapper.queryPartner", param);
//		param.put("branch_name", MapUtils.getString(map, "branch_name"));
//		return param;
//	}
//
//	@Override
//	public List<Map<String, Object>> getUserList(Map<String, Object> params) {
//		UserInfo userInfo = UserInfo.getUserInfo(params);
//		RoleEnum userRole = RoleEnum.from(userInfo.getUserRole());
//		if (userRole == null || (userRole.getRoleType() != RoleTypeEnum.PLATFORM && userRole.getRoleType() != RoleTypeEnum.SYSTEM)) {
//			putLoanBranch(params, userInfo);
//		}
//		return userDaoImpl.getUserList(params);
//
//	}
//
//	private void putLoanBranch(Map<String, Object> params, UserInfo userInfo) {
//		String branchNo = userInfo.getBranchNo();
//		Map<String, Object> map = userDaoImpl.queryLoanBranch(branchNo);
//		params.put("loan_branch", MapUtils.getString(map, "org_id"));
//	}
//
//	@Override
//	public List<Map<String, Object>> queryPtnrOffice(Map<String, Object> params) {
//		String branchNo = MapUtils.getString(params, "branch_no");
//		String guarantorNo = userDaoImpl.getGuarantorNo(branchNo);
//		//达铭
//		if ("10000741".equals(guarantorNo)) {
//			params.put("role_code", "DM_PTNR_OFFICE");
//		} else {
//			params.put("role_code", "PTNR_OFFICE");
//		}
//		return userDaoImpl.queryRole(params);
//	}
}