package com.timebank.releasetask.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timebank.releasetask.service.ReleaseTaskService;

@Controller
public class ReleaseTaskController {
	@Resource 
	ReleaseTaskService releaseTaskService;
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
