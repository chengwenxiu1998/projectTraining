package com.timebank.changetask.changetaskcontroller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timebank.changetask.changetaskservice.ChangeTaskServiceImpl;

@Controller
public class ChangeTaskController {
	@Resource
	private ChangeTaskServiceImpl changeTaskServiceImpl;
	//改变任务状态、领取人id、领取时间
		@RequestMapping(value ="changetask1", produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String changetask(@RequestParam("tId")int tId,@RequestParam("uId")int uId) {
			System.out.println(uId+" "+tId);
			Boolean boolean1=changeTaskServiceImpl.changeTask(tId, uId);
			if (boolean1==true) {
				return "true";
			}else {
				return "false";
			}
			
		}

}
