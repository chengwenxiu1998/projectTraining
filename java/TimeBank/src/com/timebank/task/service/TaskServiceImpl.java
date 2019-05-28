package com.timebank.task.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.entity.BuyOrSellTime;
import com.timebank.entity.BuyTime;
import com.timebank.entity.Task;
import com.timebank.task.dao.TaskDaoImpl;

@Service
@Transactional(readOnly=true)
public class TaskServiceImpl {
	@Resource
	private TaskDaoImpl taskDaoImpl;
	//买时间
	public List<BuyTime> buyTimeTask(){
		return this.taskDaoImpl.findBuyTimeTask();
	}
	//卖时间
	public List<BuyTime> sellTimeTask(){
		return this.taskDaoImpl.findSellTimeTask();
	}
	//未接收的任务
	public List<BuyTime> allNotAcceptTask(){
		return this.taskDaoImpl.findAllNotAcceptTask();
	}
	//改变任务状态
	public int changeTaskState(int uid) {
		return this.taskDaoImpl.updateTaskState(uid);
	}
}
