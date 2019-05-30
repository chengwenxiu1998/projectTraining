package com.timebank.notaccepttask.notacceptcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.timebank.entity.NotAccept;
import com.timebank.notaccepttask.notacceptservice.NotAcceptServiceImpl;

@Controller
public class NotAcceptController {
	@Resource
	private NotAcceptServiceImpl notAcceptServiceImpl;
	//未接收的任务列表
	@RequestMapping(value = "notaccepttask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String notAccept(Model model) {
		List<NotAccept> list=notAcceptServiceImpl.allNotAcceptTask();
		model.addAttribute("notAcceptTask",list);
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String temp=gson.toJson(list);
		return temp;
	}

}
