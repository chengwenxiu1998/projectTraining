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
		//将对应的晒晒的个数进行更改
		public Object updateShai(Integer sid,Integer count) {
			//根据sid将晒晒找出来
			Shaishai shaishai=super.sessionFactory.openSession().get(Shaishai.class,sid);
			shaishai.setScount(count);
			//将晒晒进行更新
			super.sessionFactory.openSession().update(shaishai);
			return shaishai;
		}

}
