package com.timebank.Discuss.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.Discuss.dao.DiscussDaoImpl;
import com.timebank.entity.Discuss;

@Service
@Transactional(readOnly=true)
public class DiscussServiceImpl {
	@Resource
	private DiscussDaoImpl discussDaoImpl;
	
	//展示全部讨论
	public List<Discuss> allDis() throws Exception{
		return discussDaoImpl.allDis();
	}

}
