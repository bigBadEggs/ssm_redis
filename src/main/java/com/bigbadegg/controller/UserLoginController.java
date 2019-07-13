package com.bigbadegg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigbadegg.entity.UserPo;
import com.bigbadegg.exception.UserLoginException;
import com.bigbadegg.service.UserLoginService;

@Controller
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/chechUser", method = {RequestMethod.POST, RequestMethod.GET})
	public void checkUser(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if(name == null || password == null){
			throw new UserLoginException("用户或密码错误");
		}
		
		UserPo po = new UserPo();
		po.setName(name);
		po.setPassword(password);
		boolean flag = userLoginService.checkUser(po);
		PrintWriter pw = response.getWriter();
		if(flag){
			pw.write("登陆成功");
		}else{
			pw.write("登陆失败");
		}
		
		pw.flush();
		pw.close();	
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addUser", method = {RequestMethod.POST, RequestMethod.GET})
	public void addUser(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if(name == null || password == null){
			throw new UserLoginException("用户或密码不能为空");
		}
		
		UserPo po = new UserPo();
		po.setName(name);
		po.setPassword(password);
		
		userLoginService.addUser(po);
		PrintWriter pw = response.getWriter();
		pw.write("添加成功");
		pw.flush();
		pw.close();	
	}
}
