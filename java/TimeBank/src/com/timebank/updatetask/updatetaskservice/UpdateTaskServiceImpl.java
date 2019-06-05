package com.timebank.updatetask.updatetaskservice;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.timebank.updatetask.updatetaskdao.UpdateTaskDaoImpl;

@Service
@Transactional(readOnly=true)
public class UpdateTaskServiceImpl {
	@Resource
	private UpdateTaskDaoImpl updateTaskDaoImpl;
	//改变任务状态
	public int updateTask(int uId,int tId) {
		return this.updateTaskDaoImpl.updateTask(uId, tId);
	}
}
