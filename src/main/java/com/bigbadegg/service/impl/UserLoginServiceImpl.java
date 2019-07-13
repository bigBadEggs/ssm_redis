package com.bigbadegg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bigbadegg.dao.UserLoginDao;
import com.bigbadegg.entity.UserPo;
import com.bigbadegg.exception.UserLoginException;
import com.bigbadegg.service.UserLoginService;
import com.bigbadegg.utils.JsonUtil;
import com.bigbadegg.utils.RedisCacheUtil;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private RedisCacheUtil redisCacheUtil;

	@Override
	public boolean checkUser(UserPo po) {
		// 缓存中查找
		// UserPo user = (UserPo) redisCacheUtil.get(po.getName());
		// json字符串转UserPo对象
		UserPo user = JsonUtil.jsonToObj(redisCacheUtil.get(po.getName()), UserPo.class);
		if (user == null) {
			// 数据库中查找
			int count = userLoginDao.checkUser(po);
			if (count > 0) {
				// 将数据放入缓存并设置过期时间600秒
				//boolean bool = redisCacheUtil.set(po.getName(), po, 600);
				// 对象转json字符串存入
				boolean bool = redisCacheUtil.set(po.getName(), JsonUtil.objToString(po), 600);
				
				System.out.println(bool == false ? "添加缓存失败" : "添加缓存成功！");
				return true;
			}
			return false;
		}
		
		if (user.getPassword().equals(user.getPassword())) {
			System.out.println("用户名:" + user.getName() + " 密码:" + user.getPassword());
			return true;
		}
		
		return false;
	}

	/**
	 * 用@Transactional实现spring事务管理和redis事务管理，为了验证事务的回滚，定义添加超过两笔记录即抛出自定义异常
	 * @throws UserLoginException 
	 * @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addUser(UserPo po) throws UserLoginException {
		for (int i = 0; i < 5; i++) {
			userLoginDao.addUser(po);
			redisCacheUtil.set(po.getName(), po);
			po.setName(po.getName() + i);
			if (i >= 2) {
				// throw new RuntimeException("最多只能插入两笔记录");
				throw new UserLoginException("最多只能插入两笔记录");
			}
		}
	}

}
