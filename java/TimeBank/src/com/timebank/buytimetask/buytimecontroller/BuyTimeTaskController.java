package com.timebank.buytimetask.buytimecontroller;

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
import com.timebank.buytimetask.buytimeservice.BuyTimeTaskServiceImpl;
import com.timebank.entity.BuyOrSellTime;
import com.timebank.entity.BuyTime;
import com.mysql.jdbc.log.Log;

import com.timebank.entity.Task;

@Controller
public class BuyTimeTaskController {
	@Resource
	private BuyTimeTaskServiceImpl taskServiceImpl;
	
	//买时间任务列表
	@RequestMapping(value = "buytimetask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String buyTime(Model model) {
		List<BuyTime> list=taskServiceImpl.buyTimeTask();
		model.addAttribute("buyTimeTask",list);
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String temp=gson.toJson(list);
		System.out.println(list);
		return temp;
	}
}
