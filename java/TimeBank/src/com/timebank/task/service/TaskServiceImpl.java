package com.timebank.task.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.entity.Task;
import com.timebank.task.dao.TaskDaoImpl;

@Service
@Transactional(readOnly=true)
public class TaskServiceImpl {
	@Resource
	private TaskDaoImpl taskDaoImpl;
	public List<Task> buyTimeTask(){
		return this.taskDaoImpl.findBuyTimeTask();
	}
//	public List<Task> tasksList(){
//		return this.taskDaoImpl.findTasks();
//	}
	public Boolean addTask(int uIdSend, int tcId, String tDesc, int tCoinCount, String tState, int uIdAccept, int tagId,
			String tEndtimeMonth, String tEndtimeDay, String tEndtimeHour, String tEndtimeMin, String t_imgurl) {
		return taskDaoImpl.addTask(uIdSend, tcId, tDesc, tCoinCount, tState, uIdAccept, tagId, tEndtimeMonth, tEndtimeDay, tEndtimeHour, tEndtimeMin, t_imgurl);
	}
}
