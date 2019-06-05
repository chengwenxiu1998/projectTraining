package com.timebank.changetask.changetaskservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.changetask.changetaskdao.ChangeTaskDaoImpl;
@Service
@Transactional(readOnly=true)
public class ChangeTaskServiceImpl {
	@Resource
	private ChangeTaskDaoImpl changeTaskDaoImpl;
	//改变任务状态、领取人id、领取时间
	public Boolean changeTask(int tId,int uId) {
		return changeTaskDaoImpl.changeTask(tId, uId);
	}
}
