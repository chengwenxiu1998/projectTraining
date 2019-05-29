package com.timebank.Discuss.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.timebank.Discuss.service.DiscussServiceImpl;
import com.timebank.entity.Discuss;

@Controller
@RequestMapping("/discuss")
public class DiscussController {

	@Resource
	private DiscussServiceImpl discussServiceImpl;
	
	@RequestMapping("/alldis")
	@ResponseBody
	public String allDis() throws Exception {
		List<Discuss> discussList=discussServiceImpl.allDis();
		Gson gson = new Gson();
		String temp = gson.toJson(discussList);
		return temp;
	}
	
	@RequestMapping("/insertByUid")
	@ResponseBody
	public String inserByUid(@RequestParam("text") String content,@RequestParam("uid") Integer uid,HttpSession session) {
		int id=discussServiceImpl.insertByUid(content, uid);
		Gson gson=new Gson();
		String temp=gson.toJson(id);
		return temp;
		
	}
}
