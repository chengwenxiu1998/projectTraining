package com.timebank.releasetask.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.log.Log;

import com.timebank.entity.Task;
import com.timebank.entity.User;
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

		Task task = new Task();

		task.setuTime(new Timestamp(System.currentTimeMillis()));
		task.setTcId(tcId);
		task.settDesc(tDesc);
		task.settCoinCount(tCoinCount);
		task.settState(tState);
		task.setuIdAccept(uIdAccept);
		task.setTagId(tagId);

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String s = year + "-" + tEndtimeMonth + "-" + tEndtimeDay + " " + tEndtimeHour + ":" + tEndtimeMin + ":00";
		System.out.println(s);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date d1 = new java.util.Date();
		try {
			d1 = sdf.parse(s);
			System.out.println("d1=" + d1);
			tEndtime = new Timestamp(d1.getTime());
			System.out.println(tEndtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		task.settEndTime(tEndtime);
		task.settAcceptTime(null);
		task.settFinishTime(null);
		task.settImgUrl(t_imgurl);

		Boolean boolean1 = releaseTaskService.insertIntoDB(task, uIdSend);
		if (boolean1 == true) {
			return "true";
		} else {
			return "false";
		}

	}
	 
	@RequestMapping(value = "upload", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam("name") String name,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("name="+name);
		response.setContentType("charset=utf-8");
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		// 获取输入流
		InputStream in;
		System.out.println("107");
		try {
			in = request.getInputStream();
			System.out.println("110");
			// 创建一个文件输出流
		/*	String file = servletContext.getRealPath("/META-INF/images/Img.jpg");*/
			String file="F:/projectTraining/java/TimeBank/WebContent/META-INF/taskimg/"+name;
			System.out.println(file);
			FileOutputStream out = new FileOutputStream(file);
			if (in.available() > 0) {
				int b = -1;
				while ((b = in.read()) != -1) {
					out.write(b);
					out.flush();
				}
				System.out.println("客户端上传图片完成");
				
			} else {
				System.out.println("客户端上传图片失败");
				
			}
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "aa";
	}

}
