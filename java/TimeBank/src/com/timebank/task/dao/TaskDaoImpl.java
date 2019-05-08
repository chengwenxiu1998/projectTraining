package com.timebank.task.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Task;
import com.timebank.framework.BaseDao;

@Repository
public class TaskDaoImpl extends BaseDao<Task, Integer>{
	//查询全部任务
//	public List<Task> findTasks(){
//		try {
//			return super.findAll();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	//查询买时间任务
	public List<Task> findBuyTimeTask(){
		try {
			return super.findByProperty("select u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,"
					+ "tag.tagText,t.tState,t.tId,t.uIdAccept,t.tEndTime,t.tImgUrl "
					+ "from Users u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tcId=1 and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
