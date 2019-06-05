package com.timebank.user.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.timebank.entity.Task;
import com.timebank.entity.User;
import com.timebank.user.service.UserServiceImpl;

/**
* @author 程文秀
* @version 创建时间：2019年5月29日 下午2:54:12
* @ClassName 类名称
* @Description 类描述
*/
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam("phoneNum") String phoneNum,
			@RequestParam("password") String pwd,HttpSession session){
		
		System.out.println("loginController");
		User user = this.userServiceImpl.login(phoneNum, pwd);
		
		Gson gson = new Gson();
		String json = gson.toJson(user);
		System.out.println("登录" + json);
		
		return json;
	}
	
	@RequestMapping("/findPhone")
	@ResponseBody
	public String findPhone(@RequestParam("phone") String phone) {
		User u = this.userServiceImpl.findPhone(phone);
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		System.out.println("是否有该手机号"+json);
		return json;
	}
	
	@RequestMapping("/updatePhoneByUid")
	@ResponseBody
	public String updatePhoneByUid(@RequestParam("uid") String uid,@RequestParam("phone") String phone) {
		int updatePhoneByUid = this.userServiceImpl.updatePhoneByUid(uid,phone);
		Gson gson = new Gson();
		String json = gson.toJson(updatePhoneByUid);
		return json;
	}
	/*
	@RequestMapping("/findMySend")
	@ResponseBody
	public String findMySendTask(@RequestParam("uid") String uid) {
		User user = this.userServiceImpl.findMySendByUid(Integer.parseInt(uid));
		Gson gson = new Gson();
		Set<Task> sendTaskSet = user.getSendTaskSet();	
		String json = gson.toJson(sendTaskSet);
		return json;
		
	}*/
}
