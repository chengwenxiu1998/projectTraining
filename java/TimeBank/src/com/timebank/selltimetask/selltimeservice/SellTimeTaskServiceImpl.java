package com.timebank.selltimetask.selltimeservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.timebank.entity.SellTime;
import com.timebank.selltimetask.selltimedao.SellTimeTaskDaoImpl;


@Service
@Transactional(readOnly=true)
public class SellTimeTaskServiceImpl {
	@Resource
	private SellTimeTaskDaoImpl taskDaoImpl;
	//卖时间
	public List<SellTime> sellTimeTask(){
		return this.taskDaoImpl.findSellTimeTask();
	}
	
}
