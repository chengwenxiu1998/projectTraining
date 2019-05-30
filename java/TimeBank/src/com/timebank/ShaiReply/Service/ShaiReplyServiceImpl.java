package com.timebank.ShaiReply.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.ShaiReply.Dao.ShaiReplyDaoImpl;
import com.timebank.entity.ShaiReply;

@Service
@Transactional(readOnly=true)
public class ShaiReplyServiceImpl {
	@Resource
	private ShaiReplyDaoImpl shaiReplyDaoImpl;
	
	public List<ShaiReply> findAllBySid(Integer sid) throws Exception{
		return this.shaiReplyDaoImpl.findAllById(sid);
	}
	

}
