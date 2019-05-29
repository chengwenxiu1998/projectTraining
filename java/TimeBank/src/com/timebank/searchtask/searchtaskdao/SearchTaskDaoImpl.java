package com.timebank.searchtask.searchtaskdao;

import java.util.List;

import javax.management.Query;

import org.springframework.stereotype.Repository;

import com.timebank.entity.BuyTime;
import com.timebank.entity.SearchTask;
import com.timebank.framework.BaseDao;

@Repository
public class SearchTaskDaoImpl extends BaseDao<SearchTask, Integer>{
	public List<SearchTask> findTaskByName(String searchContent){
		try {
			return super.findByProperty("select new com.timebank.entity.SearchTask(t.tId,u.uNickName,u.uImage,t.uTime,t.tDesc,t.tCoinCount,tag.tagText,t.tState,t.uIdAccept,t.tEndTime,t.tImgUrl,t.tcId)"
					+ "from User u,Task t,Tag tag "
					+ "where u.uId=t.uIdSend and t.tagId=tag.tagId and tag.tagText=?",new Object[]{searchContent});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
