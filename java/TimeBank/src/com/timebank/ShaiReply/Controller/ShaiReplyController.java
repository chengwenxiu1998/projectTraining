package com.timebank.ShaiReply.Controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.timebank.ShaiReply.Service.ShaiReplyServiceImpl;
import com.timebank.entity.ShaiReply;

@Controller
@RequestMapping("/shaireply")
public class ShaiReplyController {
	@Resource
	private ShaiReplyServiceImpl shaiReplyServiceImpl; 
	
	@RequestMapping("/allres")
	@ResponseBody
	public String findAllBySid(@RequestParam("sid") Integer sid,HttpSession session) throws Exception {
		List<ShaiReply> shaiReplyList=shaiReplyServiceImpl.findAllBySid(sid);
		System.out.println("运行了吗");
		Gson gson = new Gson();
		String temp = gson.toJson(shaiReplyList);
		return temp;
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public String allDis() throws Exception {
		List<ShaiReply> discussList=shaiReplyServiceImpl.findAll();
		Gson gson = new Gson();
		String temp = gson.toJson(discussList);
		return temp;
	}

	@RequestMapping("/addsr")
	@ResponseBody
	public String addSr(@RequestParam("sid") Integer sid,@RequestParam("uid") Integer uid,
			@RequestParam("time") String time,@RequestParam("content") String content,HttpSession session) throws Exception {
		int count=shaiReplyServiceImpl.addSr(sid, uid, time, content);
		Gson gson=new Gson();
		String temp=gson.toJson(count);
		return temp;
		
	}
}
