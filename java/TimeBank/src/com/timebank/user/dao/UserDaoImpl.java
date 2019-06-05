package com.timebank.user.dao;

import org.springframework.stereotype.Repository;

import com.timebank.entity.User;
import com.timebank.framework.BaseDao;

/**
* @author 程文秀
* @version 创建时间：2019年5月29日 下午2:54:44
* @ClassName 类名称
* @Description 类描述
*/
@Repository
public class UserDaoImpl extends BaseDao<User, Integer> {

	public User findByNameAndPwd(String phone,String pwd) {
		User user = null;
		try {
			user = this.findOne("from User u where u.uPhone=? and u.uPassword=?", new Object[]{phone,pwd});
			System.out.println("UserDaoImpl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;		
	}
	
	public User findPhone(String phone) {
		User user = null;
		try {
			user = this.findOne("from User u where u.uPhone=?",new Object[] {phone});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public int updatePhoneByUid(String uid, String phone) {
		String sql = "update users set u_phone =? where u_id=?";
		int bySql=0;
		try {
			bySql = this.excuteBySql(sql, new Object[] {phone,Integer.parseInt(uid)});
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bySql;
		
	}

	public User findSendByUid(int uid) {
		User user = null;
		try {
			user = this.findOne("from User u where u.uId=?",new Object[] {uid});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
