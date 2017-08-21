package cn.edu.cust.srvs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;

/* 论文Service */
@Service(value="/articleSrv")
public class ArticleSrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	public Map queryArticle(Map map) throws Exception{
		Map reMap = new HashMap();
		List list=dao.findList("articleMapper.queryArticle", map);
		if(list.size()>0){//查询有结果,存放相关信息
			reMap.put("articleResultInfo", Info.SUCCESS);
			reMap.put("articleList", list);
			reMap.put("articleListSize",list.size());
		}else{//查询没有结果
			reMap.put("articleResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	/**
	 * 查询社区
	 * @return
	 * @throws Exception
	 */
	public Map queryPart(Map map) throws Exception{
		Map reMap = new HashMap();
		List list=dao.findList("articleMapper.queryPart", map);
		if(list.size()>0){//查询有结果,存放相关信息
			reMap.put("partResultInfo", Info.SUCCESS);
			reMap.put("partList", list);
			reMap.put("partListSize",list.size());
		}else{//查询没有结果
			reMap.put("partResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
}
