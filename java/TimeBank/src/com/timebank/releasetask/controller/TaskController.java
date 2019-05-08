package com.timebank.releasetask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class TaskController {
	@RequestMapping(value = "login", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String index() {
		return "index";
	}

}
