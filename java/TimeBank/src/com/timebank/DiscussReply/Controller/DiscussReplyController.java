package com.timebank.DiscussReply.Controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.timebank.DiscussReply.Service.DiscussReplyServiceImpl;
import com.timebank.entity.DiscussReply;

@Controller
@RequestMapping("/reply")
public class DiscussReplyController {
	@Resource
	private DiscussReplyServiceImpl discussReplyServiceImpl;

	@RequestMapping("/alldis")
	@ResponseBody
	public String findById(@RequestParam("uId") Integer uid,
			HttpSession session) throws Exception {
		List<DiscussReply> discussReplyList=discussReplyServiceImpl.findById(uid);
		Gson gson = new Gson();
		String temp = gson.toJson(discussReplyList);
		System.out.println("回复的信息"+temp);
		return temp;
		
	}
	@RequestMapping("/insertI")
	@ResponseBody
	public String insertById(@RequestParam("did") Integer did,@RequestParam("uid") Integer uid,
			@RequestParam("text") String content,@RequestParam("time") String time,HttpSession session) {
		int id=(int) discussReplyServiceImpl.insertById(did, uid, content, time);
		Gson gson=new Gson();
		String temp=gson.toJson(id);
		return temp;
		
	}
	
}
