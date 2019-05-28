package com.timebank.task.dao;

import java.util.List;

import javax.transaction.Transaction;
import javax.websocket.Session;

import org.springframework.stereotype.Repository;

import com.timebank.entity.BuyOrSellTime;
import com.timebank.entity.BuyTime;
import com.timebank.entity.Task;
import com.timebank.framework.BaseDao;

@Repository
public class TaskDaoImpl extends BaseDao<BuyTime, Integer>{
	//查询买时间任务
	public List<BuyTime> findBuyTimeTask(){
		try {
			return super.findByProperty("select t.tId,u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,"
					+ "tag.tagText,t.tState,t.uIdAccept,t.tEndTime,t.tImgUrl "
					+ "from Users u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tcId=1 and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//查询卖时间任务
	public List<BuyTime> findSellTimeTask(){
		try {
			return super.findByProperty("select u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,"
					+ "tag.tagText,t.tState,t.tId,t.uIdAccept,t.tEndTime,t.tImgUrl "
					+ "from Users u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tcId=2 and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
<<<<<<< HEAD
	//查看所有未被接收的任务
	public List<BuyTime> findAllNotAcceptTask(){
		try {
			return super.findByProperty("select u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,"
					+ "tag.tagText,t.tState,t.tId,t.uIdAccept,t.tEndTime,t.tImgUrl "
					+ "from Users u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and t.tState='待接收'", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//根据用户id改变任务状态
	public int updateTaskState(int uid) {
		try {
			return super.excuteBySql("update task set t_state='进行中'  where u_id_send="+1,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
=======
	
	
	
>>>>>>> 4e9bcc94b87b0cd9fd120d7b17221bd18421e005
}
