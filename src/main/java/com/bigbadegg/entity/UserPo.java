package com.bigbadegg.entity;

import java.io.Serializable;

public class UserPo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8984601722631724860L;

	private Long id;

	private String name;
	
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
