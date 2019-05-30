package com.timebank.ShaiReply.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Discuss;
import com.timebank.entity.ShaiReply;
import com.timebank.framework.BaseDao;

@Repository
public class ShaiReplyDaoImpl extends BaseDao<ShaiReply,Integer>{
	//将所有关于某一个晒晒的评论显示出来
	public List<ShaiReply> findAllById(Integer sid) throws Exception{
		String hql="from ShaiReply s where s.shaishai.sid=?";
		return super.findByProperty(hql,new Integer[] {sid});
	}

}
