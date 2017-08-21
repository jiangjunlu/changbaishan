package cn.edu.cust.srvs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;

/* 嘉宾Service */
@Service(value="/guestSrv")
public class GuestSrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	//查询 嘉宾  ,map里存放条件
	public Map queryGuest(Map map) throws Exception{
		Map reMap = new HashMap();
		List list=dao.findList("guestMapper.queryGuest", map);
		if(list.size()>0){//查询有结果,存放相关信息
			reMap.put("guestResultInfo", Info.SUCCESS);
			reMap.put("guestList", list);
			reMap.put("guestListSize",list.size());
		}else{//查询没有结果
			reMap.put("guestResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	
}
