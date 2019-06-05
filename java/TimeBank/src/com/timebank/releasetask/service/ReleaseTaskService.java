package com.timebank.releasetask.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.entity.Task;
import com.timebank.releasetask.dao.ReleaseTaskDao;

@Service
@Transactional(readOnly=true)
public class ReleaseTaskService {
	@Resource
	ReleaseTaskDao releaseTaskDao;
	public Boolean insertIntoDB(Task task,int id) {
		return releaseTaskDao.insertIntoDB(task,id);
	}
}
