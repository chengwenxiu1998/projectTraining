package com.timebank.ShaiReply.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.timebank.entity.ShaiReply;
import com.timebank.entity.Shaishai;
import com.timebank.entity.User;
import com.timebank.framework.BaseDao;

@Repository
public class ShaiReplyDaoImpl extends BaseDao<ShaiReply,Integer>{
	//将所有关于某一个晒晒的评论显示出来
	public List<ShaiReply> findAllById(Integer sid) throws Exception{
		
		String hql="from ShaiReply s where s.shaishai.sid=?";
		return super.findByProperty(hql,new Integer[] {sid});
	}

	//将所有的晒晒展示出来出来
		public List<ShaiReply> allDis() throws Exception{
			return super.findAll();
		}
		
	//添加晒晒的回复
		public int addSr(Integer sid,Integer uid,String time,String content) throws Exception {
			//根据uid获取到user的值
			Shaishai shaishai=super.sessionFactory.openSession().get(Shaishai.class, sid);
			User user=super.sessionFactory.openSession().get(User.class, uid);
			//将这些信息添加进去
			ShaiReply shaiReply=new ShaiReply();
			shaiReply.setUser(user);
			shaiReply.setRcontent(content);
			shaiReply.setRtime(time);
			shaiReply.setShaishai(shaishai);
			return (int) super.sessionFactory.openSession().save(shaiReply);
			
		}
}
