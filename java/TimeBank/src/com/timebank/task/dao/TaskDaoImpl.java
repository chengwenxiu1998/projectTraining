package com.timebank.task.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Task;
import com.timebank.framework.BaseDao;

@Repository
public class TaskDaoImpl extends BaseDao<Task, Integer> {
	Timestamp tEndtime;

	// 查询全部任务
//	public List<Task> findTasks(){
//		try {
//			return super.findAll();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	// 查询买时间任务
	public List<Task> findBuyTimeTask() {
		try {
			return super.findByProperty("select u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,"
					+ "tag.tagText,t.tState,t.tId,t.uIdAccept,t.tEndTime,t.tImgUrl " + "from Users u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tcId=1 and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 向数据库插入发布的任务
	 */
	public Boolean addTask(int uIdSend, int tcId, String tDesc, int tCoinCount, String tState, int uIdAccept, int tagId,
			String tEndtimeMonth, String tEndtimeDay, String tEndtimeHour, String tEndtimeMin, String t_imgurl) {
		
		// 封装好结束时间
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
		//任务对象
		Task aTask=new Task(uIdSend,new Timestamp(System.currentTimeMillis()), tcId, tDesc, tCoinCount, tState, uIdAccept, tagId, tEndtime,t_imgurl,null,null);
		try {
			super.save(aTask);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
