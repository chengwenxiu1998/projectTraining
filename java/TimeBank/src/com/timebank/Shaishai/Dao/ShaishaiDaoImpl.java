package com.timebank.Shaishai.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.Discuss;
import com.timebank.entity.Shaishai;
import com.timebank.framework.BaseDao;

@Repository
public class ShaishaiDaoImpl extends BaseDao<Shaishai,Integer>{
	//将所有晒晒展示出来出来
		public List<Shaishai> allShai() throws Exception{
			return super.findAll();
		}

}
