package com.timebank.coinrecord;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.timebank.entity.CoinTrageRecord;

/**
* @author 程文秀
* @version 创建时间：2019年6月4日 上午11:15:06
* @ClassName 类名称
* @Description 类描述
*/
@Service
public class CoinServiceImpl {

	@Resource
	private CoinDaoImpl coinDaoImpl;
	public List<CoinTrageRecord> findCoinRecordByUid(int uid) {
		return coinDaoImpl.findCoinRecordByUid(uid);
	}
	

}
