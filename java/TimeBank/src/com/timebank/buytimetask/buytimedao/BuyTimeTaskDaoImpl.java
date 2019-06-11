package com.timebank.buytimetask.buytimedao;

import java.util.List;

import javax.transaction.Transaction;
import javax.websocket.Session;

import org.springframework.stereotype.Repository;
import com.timebank.entity.BuyTime;
import com.timebank.framework.BaseDao;

@Repository
public class BuyTimeTaskDaoImpl extends BaseDao<BuyTime, Integer>{
	//查询买时间任务
	public List<BuyTime> findBuyTimeTask(){
		try {
			return super.findByProperty("select new com.timebank.entity.BuyTime(t.tId,t.uIdSend,u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,tag.tagText,t.tState,t.uIdAccept,t.tEndTime,t.tImgUrl)"
					+ "from User u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tcId=1 and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
