package com.timebank.user.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.timebank.entity.Tag;
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
	
	public int addUser(User user) {
		try {
			this.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer id = user.getuId();
		System.out.println("注册，插入一个user，返回userId" + id);
		return id;
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

	public JSONObject findSendByUid(int uid) {
		String sql = "select * from task where u_id_send=?";
		List<Map<String, Object>> list = null;
		try {
			list = this.findBySql(sql, new Object[] {uid});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = this.findUserByUid(uid);
		JSONObject  object = new JSONObject();
		JSONArray ready = new JSONArray();
		JSONArray doing = new JSONArray();
		JSONArray finish = new JSONArray();
		Iterator<Map<String, Object>> it = list.iterator();
		while(it.hasNext()) {
			Map map = it.next();
			JSONObject obj = new JSONObject();
			obj.put("tid", map.get("t_id"));
			obj.put("uIdSend", map.get("u_id_send"));
			obj.put("uTime", map.get("u_time"));
			obj.put("tcId", map.get("tc_id"));
			obj.put("tDesc", map.get("t_desc"));
			obj.put("tCoinCount", map.get("t_coin_count"));
			obj.put("tState", map.get("t_state"));
			obj.put("uIdAccept", map.get("u_id_accept"));
			obj.put("tagId", map.get("tag_id"));
			obj.put("tImgUrl", map.get("t_imgurl"));
			obj.put("tEndTime", map.get("t_endtime"));
			obj.put("tAcceptTime", map.get("t_accept_time"));
			obj.put("tFinishTime", map.get("t_finish_time"));
			
			String nickname = user.getuNickName();
			obj.put("sendNickname", nickname);
			String sendHeadImg = user.getuImage();
			obj.put("sendHeadImg",sendHeadImg);
			String address = user.getuArea();
			obj.put("sendAddress", address);
			
			Tag tag = this.sessionFactory.openSession().get(Tag.class, (Integer)map.get("tag_id"));
			
			obj.put("tag", tag.getTagText());
			String  state = (String)map.get("t_state");
			if(state.equals("进行中")) {
				doing.put(obj);
			}else if(state.equals("已完成")) {
				finish.put(obj);
			}else if(state.equals("待接收")) {
				ready.put(obj);
			}
		}
		System.out.println("ready"+ready.toString());
		object.put("ready", ready);
		object.put("doing", doing);
		object.put("finish", finish);
		
		return object;
	}
	
	public User findUserByUid(int uid) {
		System.out.println("uid" + uid);
		User user = null; 
		
		try {
			System.out.println("你好呀");
			user = this.get(uid);
			System.out.println("user111111111111"+user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("出现了异常，哈哈");
			e.printStackTrace();
			System.out.println("出现了异常，嘿嘿");
			
		}
			
		
		System.out.println("user22222222222"+user);
		return user;
	}
	
}
