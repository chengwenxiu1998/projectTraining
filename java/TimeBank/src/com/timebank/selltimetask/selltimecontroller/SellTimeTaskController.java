package com.timebank.selltimetask.selltimecontroller;

import java.util.Collection;
import java.util.Collections;
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
import com.timebank.entity.SellTime;
import com.timebank.selltimetask.selltimeservice.SellTimeTaskServiceImpl;
import com.mysql.jdbc.log.Log;

@Controller
public class SellTimeTaskController {
	@Resource
	private SellTimeTaskServiceImpl taskServiceImpl;
	
	//卖时间任务列表
	@RequestMapping(value = "selltimetask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String sellTime(Model model) {
		List<SellTime> list=taskServiceImpl.sellTimeTask();
		System.out.println(list);
		Collections.reverse(list);
		
		model.addAttribute("sellTimeTask",list);
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String temp=gson.toJson(list);
		System.out.println(list);
		return temp;
	}
}
