package cn.edu.cust.srvs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;

@Service(value="/newsSrv")
public class NewsSrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	public Map queryNews(Map map) throws Exception{
		Map reMap = new HashMap();
		List list = dao.findList("newsMapper.queryNews", map);
		if(list.size()>0){
			reMap.put("newsResultInfo", Info.SUCCESS);
			reMap.put("newsList", list);
			reMap.put("newsListSize", list.size());
		}else{
			reMap.put("newsResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
}
