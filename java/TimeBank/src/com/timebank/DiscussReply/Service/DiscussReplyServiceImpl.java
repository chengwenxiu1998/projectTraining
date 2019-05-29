package com.timebank.DiscussReply.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.DiscussReply.Dao.DiscussReplyDaoImpl;
import com.timebank.entity.DiscussReply;

@Service
@Transactional(readOnly=true)
public class DiscussReplyServiceImpl {
	@Resource
	private DiscussReplyDaoImpl discussReplyDaoImpl;
	
	public List<DiscussReply> findById(Integer id) throws Exception {
		return this.discussReplyDaoImpl.findByProperty(id);
	}

	

}
