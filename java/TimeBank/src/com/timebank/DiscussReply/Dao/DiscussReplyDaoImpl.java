package com.timebank.DiscussReply.Dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.timebank.entity.Discuss;
import com.timebank.entity.DiscussReply;
import com.timebank.entity.User;
import com.timebank.framework.BaseDao;

@Repository
public class DiscussReplyDaoImpl extends BaseDao<DiscussReply,Integer>{

	//根据讨论id查找讨论回复
	public List<DiscussReply> findByProperty(Integer id) throws Exception{
		String hql="from DiscussReply dr where dr.discuss.dId=?";
		return super.findByProperty(hql,new Integer[] {id});	
	}
	
	//根据用户人id，讨论id插入新的讨论回复
	public Serializable insertById(Integer did,Integer uid,String content,String time) {
		//根据did找到Discuss类
		Discuss discuss=super.sessionFactory.openSession().get(Discuss.class,did);
		User user=super.sessionFactory.openSession().get(User.class, uid);
		DiscussReply discussReply=new DiscussReply(uid,content,time);
		discuss.setUser(user);
		discussReply.setDiscuss(discuss);
		System.out.println("插入的内容是"+content+"发布人"+did+"时间"+time);
		
		return super.sessionFactory.openSession().save(discussReply);
		
	}
	
}
