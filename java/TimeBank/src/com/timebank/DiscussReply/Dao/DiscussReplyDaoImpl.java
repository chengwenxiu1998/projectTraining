package com.timebank.DiscussReply.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.DiscussReply;
import com.timebank.framework.BaseDao;

@Repository
public class DiscussReplyDaoImpl extends BaseDao<DiscussReply,Integer>{

	//根据讨论id查找讨论回复
	public List<DiscussReply> findByProperty(Integer id) throws Exception{
		String hql="from DiscussReply dr where dr.discuss.dId=?";
		return super.findByProperty(hql,new Integer[] {id});
		
	}
	
}
