package com.timebank.notaccepttask.notacceptservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.buytimetask.buytimedao.BuyTimeTaskDaoImpl;
import com.timebank.entity.NotAccept;
import com.timebank.notaccepttask.notacceptdao.NotAcceptTaskDaoImpl;

@Service
@Transactional(readOnly=true)
public class NotAcceptServiceImpl {
	@Resource
	private NotAcceptTaskDaoImpl notAcceptTaskDaoImpl;
	//未接收的任务
	public List<NotAccept> allNotAcceptTask(){
		return this.notAcceptTaskDaoImpl.findAllNotAcceptTask();
	}
}
