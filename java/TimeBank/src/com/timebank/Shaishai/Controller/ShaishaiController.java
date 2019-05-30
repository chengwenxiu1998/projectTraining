package com.timebank.Shaishai.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.timebank.Shaishai.Service.ShaishaiServiceImpl;
import com.timebank.entity.Shaishai;

@Controller
@RequestMapping("/shaishai")
public class ShaishaiController {
	
	@Resource
	private ShaishaiServiceImpl shaishaiServiceImpl;
	@RequestMapping("/allshai")
	@ResponseBody
	public String allShai() throws Exception {
		List<Shaishai> shaiList=shaishaiServiceImpl.allShai();
		Gson gson = new Gson();
		String temp = gson.toJson(shaiList);
		return temp;
	}
	

}
