package com.timebank.releasetask.dao;

import org.springframework.stereotype.Repository;


import com.timebank.entity.Task;
import com.timebank.framework.BaseDao;

@Repository
public class ReleaseTaskDao extends BaseDao<Task,Integer>{
	/*
	 * 向数库插入
	 */
	public Boolean insertIntoDB(Task task,int id) {
		/*int result=0;
		try {
			result=this.excuteBySql("insert into task(u_id_send,u_time,tc_id,t_desc,t_coin_count,t_state,u_id_accept,tag_id,t_endtime,t_accept_time,t_finish_time,t_imgurl) values(?"
					+ ",?,?,?,?,?,?,?,?,?,?,?)",new Object[] {task.getUser().getuId(),task.getuTime(),task.getTcId(),task.gettDesc(),task.gettCoinCount(),task.gettState(),
					task.getuIdAccept(),task.getTagId(),task.gettEndTime(),task.gettAcceptTime(),task.gettFinishTime(),task.gettImgUrl()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result!=0) {
			return true;
		}else {
			return false;
		}*/
	
		String sql="INSERT INTO task(u_id_send,u_time,tc_id,t_desc,t_coin_count,t_state,u_id_accept,tag_id,t_endtime,t_accept_time,t_finish_time,t_imgurl) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			result=this.excuteBySql(sql,new Object[] {id,task.getuTime()
					,task.getTcId(),task.gettDesc(),task.gettCoinCount(),task.gettState(),task.getuIdAccept(),task.getTagId(),task.gettEndTime()
					,task.gettAcceptTime(),task.gettFinishTime(),task.gettImgUrl()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(result!=0) {
			return true;
		}else {
			return false;
		}
	}
}
