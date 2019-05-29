package com.timebank.searchtask.searchtaskservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.entity.SearchTask;
import com.timebank.searchtask.searchtaskdao.SearchTaskDaoImpl;

@Service
@Transactional(readOnly=true)
public class SearchTaskServiceImpl {
	@Resource
	private SearchTaskDaoImpl searchTaskDaoImpl;
	public List<SearchTask> findTask(String searchContent){
		return searchTaskDaoImpl.findTaskByName(searchContent);
	}
}
