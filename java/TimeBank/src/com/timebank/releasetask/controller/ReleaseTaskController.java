package com.timebank.releasetask.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timebank.entity.Task;
import com.timebank.releasetask.service.ReleaseTaskService;

@Controller
public class ReleaseTaskController {
	@Resource 
	ReleaseTaskService releaseTaskService;
	Timestamp tEndtime;
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
		/*System.out.println("uIdSend="+uIdSend);
		System.out.println("tcId="+tcId);
		System.out.println("tDesc="+tDesc);
		System.out.println("tCoinCount="+tCoinCount);
		System.out.println("tState="+tState);
		System.out.println("tagId="+tagId);
		System.out.println("tEndtimeMonth="+tEndtimeMonth);
		System.out.println("tEndtimeDay="+tEndtimeDay);
		System.out.println("tEndtimeDay="+tEndtimeDay);*/
		
		Task task=new Task();
		task.setuIdSend(uIdSend);
		task.setuTime(new Timestamp(System.currentTimeMillis()));
		task.setTcId(tcId);
		task.settDesc(tDesc);
		task.settCoinCount(tCoinCount);
		task.settState(tState);
		task.setuIdAccept(uIdAccept);
		task.setTagId(tagId);
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String s=year+"-"+ tEndtimeMonth+"-"+tEndtimeDay+" "+tEndtimeHour+":"+tEndtimeMin+":00";
		System.out.println(s);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date d1=new java.util.Date();
		try {
			d1 = sdf.parse(s);
			System.out.println("d1="+d1);
			tEndtime= new Timestamp(d1.getTime());
			System.out.println(tEndtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		task.settEndTime(tEndtime);
		task.settAcceptTime(null);
		task.settFinishTime(null);
		task.settImgUrl(t_imgurl);
		
		Boolean boolean1=releaseTaskService.insertIntoDB(task);
		if (boolean1==true) {
			return "true";
		}else {
			return "false";
		}
		/*List<Task> list = taskServiceImpl.buyTimeTask();
		model.addAttribute("buyTimeTask", list);
		Gson gson = new Gson();
		String temp = gson.toJson(list);
		return temp;*/
	}
}
