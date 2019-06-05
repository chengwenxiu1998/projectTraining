package com.timebank.coinrecord;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timebank.entity.CoinTrageRecord;

/**
* @author 程文秀
* @version 创建时间：2019年6月4日 上午11:14:30
* @ClassName 类名称
* @Description 类描述
*/
@Controller
@RequestMapping("/coin")
public class CoinController {
	
	@Resource
	private CoinServiceImpl coinServiceImpl;
	
	@RequestMapping("/findCoinRecord")
	@ResponseBody
	public String findCoinRecordByUid(@RequestParam("uid") String uid) {
		List<CoinTrageRecord> records = this.coinServiceImpl.findCoinRecordByUid(Integer.parseInt(uid));
		JSONObject  object = new JSONObject();
		JSONArray all = new JSONArray();
		for(int i=0;i<records.size();i++) {
			JSONObject obj = new JSONObject();
			CoinTrageRecord record = records.get(i);
			Date ctrFinishTime = record.getCtrFinishTime();
			int ctrCount = record.getCtrCount();
			byte addOrReduce = record.getAddOrReduce();
			obj.put("ctrFinishTime",ctrFinishTime);
			obj.put("ctrCount",ctrCount);
			obj.put("addOrReduce",addOrReduce==1?"+":"-");
			
			all.put(obj);
		}
		
		object.put("records", all);
		System.out.println("我的交易记录" + object.toString());
		return object.toString();
	}

}
