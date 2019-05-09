package com.timebank.Discuss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Discuss;
import com.timebank.framework.BaseDao;

@Repository
public class DiscussDaoImpl extends BaseDao<Discuss,Integer>{
	
	//将所有的讨论展示出来出来
	public List<Discuss> allDis() throws Exception{
		return super.findAll();
	}
	

}
