package com.timebank.searchtask.searchtaskcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.Parameter;
import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.timebank.entity.BuyTime;
import com.timebank.entity.SearchTask;
import com.timebank.searchtask.searchtaskservice.SearchTaskServiceImpl;

@Controller
public class SearchTaskController {
	@Resource
	private SearchTaskServiceImpl searchTaskServiceImpl;
	@RequestMapping(value = "searchtask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String searchTask(Model model,@RequestParam("searchContent")String searchContent) {
		System.out.println(searchContent);
		List<SearchTask> list=searchTaskServiceImpl.findTask(searchContent);
		model.addAttribute("searchTask",list);
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String temp=gson.toJson(list);
		return temp;
	}
}
