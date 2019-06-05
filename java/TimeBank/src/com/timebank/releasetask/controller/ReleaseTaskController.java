package com.timebank.releasetask.controller;

import java.awt.event.FocusAdapter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String insertTaskIntoDB( @RequestParam("tcId") int tcId,
			@RequestParam("tDesc") String tDesc, @RequestParam("tCoinCount") int tCoinCount,
			@RequestParam("tState") String tState, @RequestParam("uIdAccept") int uIdAccept,
			@RequestParam("tagId") int tagId, @RequestParam("tEndtimeMonth") String tEndtimeMonth,
			@RequestParam("tEndtimeDay") String tEndtimeDay, @RequestParam("tEndtimeHour") String tEndtimeHour,
			@RequestParam("tEndtimeMin") String tEndtimeMin, @RequestParam("t_imgurl") String t_imgurl
			) {
			
		Task task=new Task();
		
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
		
	}
	@RequestMapping(value = "upload", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam(value="upfile") MultipartFile file,
            HttpServletRequest request) {
		//文件上传
		String rootPath=request.getServletContext().getRealPath("/");
		if(!file.isEmpty()){
		try {
			InputStream is=file.getInputStream();
			FileOutputStream fos=new FileOutputStream(
					rootPath+"/"+"upload/"
					+file.getOriginalFilename());
			System.out.println(fos.toString());
			byte []cache=new byte[is.available()];
			is.read(cache);
			fos.write(cache);
			is.close();
			fos.flush();
			fos.close();
			System.out.println("文件上传成功");
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		return rootPath;
	}
}
