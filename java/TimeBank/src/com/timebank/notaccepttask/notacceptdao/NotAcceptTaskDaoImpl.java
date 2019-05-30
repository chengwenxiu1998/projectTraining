package com.timebank.notaccepttask.notacceptdao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.BuyTime;
import com.timebank.entity.NotAccept;
import com.timebank.framework.BaseDao;
@Repository
public class NotAcceptTaskDaoImpl extends BaseDao<NotAccept, Integer>{
	//查看所有未被接收的任务
	public List<NotAccept> findAllNotAcceptTask(){
		try {
			return super.findByProperty("select new com.timebank.entity.NotAccept(t.tId,u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,tag.tagText,t.tState,t.uIdAccept,t.tEndTime,t.tImgUrl,t.tcId,u.uId)"
					+ "from User u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
