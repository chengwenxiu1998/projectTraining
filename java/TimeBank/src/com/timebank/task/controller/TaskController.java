package com.timebank.task.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mysql.jdbc.log.Log;
import com.timebank.entity.Task;
import com.timebank.task.service.TaskServiceImpl;

@Controller
public class TaskController {
	@Resource
	private TaskServiceImpl taskServiceImpl;

	// 买时间任务列表
	@RequestMapping(value = "task", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String buyTime(Model model) {
		List<Task> list = taskServiceImpl.buyTimeTask();
		model.addAttribute("buyTimeTask", list);
		Gson gson = new Gson();
		String temp = gson.toJson(list);
		return temp;
	}

//	public String tasks(Model model) {
//		List<Task> list=taskServiceImpl.tasksList();
//		model.addAttribute("tasks",list);
//		Gson gson=new Gson();
//		String temp=gson.toJson(list);
//		return temp;
//	}
	/*
	 * 向数据库插入
	 */
	@RequestMapping(value = "insertTaskIntoDB", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String insertTaskIntoDB(@RequestParam("uIdSend") int uIdSend, @RequestParam("tcId") int tcId,
			@RequestParam("tDesc") String tDesc, @RequestParam("tCoinCount") int tCoinCount,
			@RequestParam("tState") String tState, @RequestParam("uIdAccept") int uIdAccept,
			@RequestParam("tagId") int tagId, @RequestParam("tEndtimeMonth") String tEndtimeMonth,
			@RequestParam("tEndtimeDay") String tEndtimeDay, @RequestParam("tEndtimeHour") String tEndtimeHour,
			@RequestParam("tEndtimeMin") String tEndtimeMin, @RequestParam("t_imgurl") String t_imgurl) {
		System.out.println("uIdSend="+uIdSend);
		System.out.println("tcId="+tcId);
		System.out.println("tDesc="+tDesc);
		System.out.println("tCoinCount="+tCoinCount);
		System.out.println("tState="+tState);
		System.out.println("tagId="+tagId);
		System.out.println("tEndtimeMonth="+tEndtimeMonth);
		System.out.println("tEndtimeDay="+tEndtimeDay);
		System.out.println("tEndtimeDay="+tEndtimeDay);
		return "index";
		/*List<Task> list = taskServiceImpl.buyTimeTask();
		model.addAttribute("buyTimeTask", list);
		Gson gson = new Gson();
		String temp = gson.toJson(list);
		return temp;*/
	}
}
