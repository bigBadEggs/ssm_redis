package com.bigbadegg.service;

import com.bigbadegg.entity.UserPo;

public interface UserLoginService {

	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	boolean checkUser(UserPo po);
	
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(UserPo po) throws Exception;
	
}
