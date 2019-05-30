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
@RequestMapping("/sreply")
public class ShaiReplyController {
	@Resource
	private ShaiReplyServiceImpl shaiReplyServiceImpl; 
	
	@RequestMapping("/allres")
	@ResponseBody
	public String findAllBySid(@RequestParam("sid") Integer sid,HttpSession session) throws Exception {
		List<ShaiReply> shaiReplyList=shaiReplyServiceImpl.findAllBySid(sid);
		Gson gson = new Gson();
		String temp = gson.toJson(shaiReplyList);
		return temp;
	}

}
