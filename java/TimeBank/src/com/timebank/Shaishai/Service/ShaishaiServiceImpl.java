package com.timebank.Shaishai.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timebank.Shaishai.Dao.ShaishaiDaoImpl;
import com.timebank.entity.Shaishai;

@Service
@Transactional(readOnly=true)
public class ShaishaiServiceImpl {
	@Resource
	private ShaishaiDaoImpl shaishaiDaoImpl;
	
	public List<Shaishai> allShai() throws Exception{
		return shaishaiDaoImpl.allShai();
	}
	
	public Object updateShai(Integer sid,Integer count) {
		return shaishaiDaoImpl.updateShai(sid, count);
	}
	

}
