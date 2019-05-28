package com.timebank.task.controller;

import java.util.List;

import javax.annotation.Resource;

import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.timebank.entity.BuyOrSellTime;
import com.timebank.entity.BuyTime;

import com.mysql.jdbc.log.Log;

import com.timebank.entity.Task;
import com.timebank.task.service.TaskServiceImpl;

@Controller
public class TaskController {
	@Resource
	private TaskServiceImpl taskServiceImpl;
	
	//买时间任务列表
	@RequestMapping(value = "buytimetask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String buyTime(Model model) {
		List<BuyTime> list=taskServiceImpl.buyTimeTask();
		model.addAttribute("buyTimeTask",list);
		Gson gson = new Gson();

		String temp=gson.toJson(list);
		System.out.println(list.get(0).toString());

		return temp;
	}
	//卖时间任务列表
	@RequestMapping(value = "selltimetask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String sellTime(Model model) {
		List<BuyTime> list=taskServiceImpl.sellTimeTask();
		model.addAttribute("sellTimeTask",list);
		Gson gson=new Gson();
		String temp=gson.toJson(list);
		return temp;
	}
	//未接收的任务列表
	@RequestMapping(value = "notaccepttask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String notAccept(Model model) {
		List<BuyTime> list=taskServiceImpl.allNotAcceptTask();
		model.addAttribute("notAcceptTask",list);
		Gson gson=new Gson();
		String temp=gson.toJson(list);
		return temp;
	}
	//改变任务状态
	@RequestMapping(value = "changetaskstate", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String notAccept(Model model,int uid) {
		int list=taskServiceImpl.changeTaskState(uid);
		model.addAttribute("notAcceptTask",list);
		Gson gson=new Gson();
		String temp=gson.toJson(list);
		return temp;
	}

//	public String tasks(Model model) {
//		List<Task> list=taskServiceImpl.tasksList();
//		model.addAttribute("tasks",list);
//		Gson gson=new Gson();
//		String temp=gson.toJson(list);
//		return temp;
//	}
}
