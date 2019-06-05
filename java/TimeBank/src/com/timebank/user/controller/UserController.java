package com.timebank.user.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.timebank.entity.Task;
import com.timebank.entity.User;
import com.timebank.user.dao.UserDaoImpl;
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
	
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(@RequestParam("petName") String petName,
			@RequestParam("phone") String phone,@RequestParam("password") String password) {
		int userId = this.userServiceImpl.regist(petName,phone,password);
		
		Gson gson = new Gson();
		String json = gson.toJson(userId);
		
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
	
	@RequestMapping("/findMySend")
	@ResponseBody
	public String findMySendTask(@RequestParam("uid") String uid) {
		JSONObject object = this.userServiceImpl.findMySendByUid(Integer.parseInt(uid));
		
		System.out.println("object" + object);
		return object.toString();
		
	}
	@RequestMapping("/findMyReceive")
	@ResponseBody
	public String findMyReceiveTask(@RequestParam("uid") String uid) {
		JSONObject object = this.userServiceImpl.findMyReceiveByUid(Integer.parseInt(uid));
		
		System.out.println("object" + object);
		return object.toString();
		
	}
	
	@RequestMapping("/updatePassword")
	@ResponseBody
	public String updatePassword(@RequestParam("uid") String uid,
			@RequestParam("password") String password) {
		int update = this.userServiceImpl.updatePasswordByUid(Integer.parseInt(uid),password);
		
		Gson gson = new Gson();
		String json = gson.toJson(update);
		return json;
	}
	
	@RequestMapping("/updateArea")
	@ResponseBody
	public String updateArea(@RequestParam("uid") String uid,@RequestParam("area") String area) {
		int update = this.userServiceImpl.updateAreaByUid(Integer.parseInt(uid),area);
		
		Gson gson = new Gson();
		String json = gson.toJson(update);
		return json;
		
	}
	
	@RequestMapping("/realNameAuthentication")
	@ResponseBody
	public String realNameAuthentication(@RequestParam("uid") String uid,
			@RequestParam("name") String name,@RequestParam("idCard") String idCard) {
		int update = this.userServiceImpl.realNameAuthentication(Integer.parseInt(uid),name,idCard);
		
		Gson gson = new Gson();
		String json = gson.toJson(update);
		return json;
		
		
	}
}
