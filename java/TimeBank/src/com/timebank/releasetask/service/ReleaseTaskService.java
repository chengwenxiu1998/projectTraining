package com.timebank.releasetask.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.timebank.releasetask.dao.ReleaseTaskDao;

@Service
public class ReleaseTaskService {
	@Resource
	ReleaseTaskDao releaseTaskDao;
}
