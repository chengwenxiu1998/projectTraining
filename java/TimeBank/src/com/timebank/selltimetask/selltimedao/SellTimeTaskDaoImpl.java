package com.timebank.selltimetask.selltimedao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.SellTime;
import com.timebank.framework.BaseDao;

@Repository
public class SellTimeTaskDaoImpl extends BaseDao<SellTime, Integer>{
	//查询卖时间任务
	public List<SellTime> findSellTimeTask(){
		try {
			return super.findByProperty("select new com.timebank.entity.SellTime(t.tId,t.uIdSend,u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,tag.tagText,t.tState,t.uIdAccept,t.tEndTime,t.tImgUrl)"
					+ "from User u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tcId=2 and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
