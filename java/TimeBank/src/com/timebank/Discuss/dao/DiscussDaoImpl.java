package com.timebank.Discuss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Discuss;
import com.timebank.entity.Tag;
import com.timebank.entity.User;
import com.timebank.framework.BaseDao;

@Repository
public class DiscussDaoImpl extends BaseDao<Discuss,Integer>{
	
	//将所有的讨论展示出来出来
	public List<Discuss> allDis() throws Exception{
		return super.findAll();
	}
	
	//添加讨论
	public int insertByUid(String content,Integer uid) {
		//根据uid查到user的内容
		User user=super.sessionFactory.openSession().get(User.class, uid);
		//在Discuss中插入内容
		Discuss discuss=new Discuss();
		discuss.setdTopicCoutent(content);
		Tag tag=null;
		if(content.contains("代取快递")) {
			tag=super.sessionFactory.openSession().get(Tag.class,1);
		}else if(content.contains("代买饭")) {
			tag=super.sessionFactory.openSession().get(Tag.class,2);
		}else if(content.contains("代接水")) {
			tag=super.sessionFactory.openSession().get(Tag.class,3);
		}else if(content.contains("约逛街")) {
			tag=super.sessionFactory.openSession().get(Tag.class,4);
		}else if(content.contains("约运动")) {
			tag=super.sessionFactory.openSession().get(Tag.class,5);
		}else if(content.contains("约学习")) {
			tag=super.sessionFactory.openSession().get(Tag.class, 6);
		}else {
			tag=super.sessionFactory.openSession().get(Tag.class,7);
		}
		discuss.setTag(tag);
		discuss.setUser(user);
		return (int) super.sessionFactory.openSession().save(discuss);
		
		
	}
	

}
