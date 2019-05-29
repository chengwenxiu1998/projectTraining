package com.timebank.buytimetask.buytimeservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.buytimetask.buytimedao.BuyTimeTaskDaoImpl;
import com.timebank.entity.BuyOrSellTime;
import com.timebank.entity.BuyTime;
import com.timebank.entity.BuyOrSellTime;
import com.timebank.entity.Task;

@Service
@Transactional(readOnly=true)
public class BuyTimeTaskServiceImpl {
	@Resource
	private BuyTimeTaskDaoImpl taskDaoImpl;
	//买时间
	public List<BuyTime> buyTimeTask(){
		return this.taskDaoImpl.findBuyTimeTask();
	}
//	//改变任务状态
//	public int changeTaskState(int uid) {
//		return this.taskDaoImpl.updateTaskState(uid);
//	}
}
