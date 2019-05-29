package com.timebank.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.timebank.entity.User;
import com.timebank.user.dao.UserDaoImpl;

/**
* @author 程文秀
* @version 创建时间：2019年5月29日 下午2:54:59
* @ClassName 类名称
* @Description 类描述
*/
@Service
public class UserServiceImpl {
	
	@Resource
	private UserDaoImpl userDaoImpl;

	public User login(String phoneNum, String upwd) {
		return userDaoImpl.findByNameAndPwd(phoneNum, upwd);
	}

	public User findPhone(String phone) {
		return this.userDaoImpl.findPhone(phone);
	}

	public int updatePhoneByUid(String uid, String phone) {
		return this.userDaoImpl.updatePhoneByUid(uid,phone);
		
	}
	
}
