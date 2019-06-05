package com.timebank.changetask.changetaskdao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Task;
import com.timebank.framework.BaseDao;

@Repository
public class ChangeTaskDaoImpl extends BaseDao<Task, Integer>{
	//根据任务id改变任务状态、领取人id、领取时间
		public Boolean changeTask(int tId,int uId) {
			int result=0;
			Long time=System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
			Date date = new Date(time);
			System.out.println(formatter.format(date));
			try {
				result=this.excuteBySql("update task set t_state='进行中' ,u_id_accept=?,t_accept_time=? where t_id=?",new Object[]{uId,formatter.format(date),tId});
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
