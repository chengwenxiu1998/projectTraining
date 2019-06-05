package com.timebank.coinrecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.timebank.entity.CoinTrageRecord;
import com.timebank.framework.BaseDao;

/**
* @author 程文秀
* @version 创建时间：2019年6月4日 上午11:14:44
* @ClassName 类名称
* @Description 类描述
*/
@Repository
public class CoinDaoImpl extends BaseDao<CoinTrageRecord, Integer>{

	public List<CoinTrageRecord> findCoinRecordByUid(int uid) {
		List<CoinTrageRecord> list = new ArrayList<CoinTrageRecord>();
		try {
			list = this.findByProperty("from CoinTrageRecord c where c.uId=?", new Object[] {uid});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

}
