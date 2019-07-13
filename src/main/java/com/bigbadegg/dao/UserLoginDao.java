package com.bigbadegg.dao;

import com.bigbadegg.entity.UserPo;

public interface UserLoginDao {

	public int checkUser(UserPo user);

	public void addUser(UserPo user);
}
