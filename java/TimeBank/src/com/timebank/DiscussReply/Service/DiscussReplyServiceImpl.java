package com.timebank.DiscussReply.Service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.DiscussReply.Dao.DiscussReplyDaoImpl;
import com.timebank.entity.Discuss;
import com.timebank.entity.DiscussReply;
import com.timebank.entity.User;

@Service
@Transactional(readOnly=true)
public class DiscussReplyServiceImpl {
	@Resource
	private DiscussReplyDaoImpl discussReplyDaoImpl;
	
	public List<DiscussReply> findById(Integer id) throws Exception {
		return this.discussReplyDaoImpl.findByProperty(id);
	}

	public Serializable insertById(Integer did,Integer uid,String content,String time) {
		return this.discussReplyDaoImpl.insertById(did, uid, content, time);
		
	}
	
	

}
