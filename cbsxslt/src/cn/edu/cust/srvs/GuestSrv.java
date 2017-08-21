package cn.edu.cust.srvs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;

/* �α�Service */
@Service(value="/guestSrv")
public class GuestSrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	//��ѯ �α�  ,map��������
	public Map queryGuest(Map map) throws Exception{
		Map reMap = new HashMap();
		List list=dao.findList("guestMapper.queryGuest", map);
		if(list.size()>0){//��ѯ�н��,��������Ϣ
			reMap.put("guestResultInfo", Info.SUCCESS);
			reMap.put("guestList", list);
			reMap.put("guestListSize",list.size());
		}else{//��ѯû�н��
			reMap.put("guestResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	
}
