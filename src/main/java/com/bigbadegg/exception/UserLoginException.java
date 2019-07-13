package com.bigbadegg.exception;

/**
 * 自定义用户登陆异常
 * @author bigBa
 *
 */
public class UserLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9210777903745546773L;
	
	private String message;
	
	public UserLoginException(String message){
		this.message = message;
	}
	
	public String toString(){
		return message;
	}
	
}
