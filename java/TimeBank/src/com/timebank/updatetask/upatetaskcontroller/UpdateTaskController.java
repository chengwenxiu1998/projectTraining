package com.timebank.updatetask.upatetaskcontroller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timebank.updatetask.updatetaskservice.UpdateTaskServiceImpl;

@Controller
public class UpdateTaskController {
	@Resource
	private UpdateTaskServiceImpl updateTaskServiceImpl;
	//改变任务状态
	@RequestMapping(value = "changetask", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public int notAccept(Model model,@RequestParam("uId")int uId,@RequestParam("tId")int tId) {
		int res=updateTaskServiceImpl.updateTask(uId, tId);
//		model.addAttribute("updateTask",list);
//		Gson gson=new Gson();
//		String temp=gson.toJson(list);
//		return temp;
		return res;
	}
}
 