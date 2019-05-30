package com.timebank.updatetask.updatetaskdao;


import org.springframework.stereotype.Repository;
import com.timebank.entity.Task;
import com.timebank.framework.BaseDao;

@Repository
public class UpdateTaskDaoImpl extends BaseDao<Task, Integer>{
	//根据任务id改变任务状态和领取人id
	public int updateTask(int uId,int tId) {
		try {
			return super.excuteBySql("update task set t_state='进行中',u_id_accept="+uId+" where t_id="+tId,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
