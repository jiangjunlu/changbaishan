package cn.edu.cust.srvs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.srvs.JiGouItfc;

@Service(value="jigouSrv")
public class JiGouSrv implements JiGouItfc{
	@Resource(name="daoSupport")
	private DaoSupport dao;
	public int count(Map<String, Object> paraMap) throws Exception{
		Map counter=(Map)dao.queryOne("jigouMapper.count", paraMap);
		int count=Integer.parseInt(counter.get("counter").toString());
		return count;
	}
	@Override
	public Map search(Map<String, Object> paraMap) throws Exception {
		Map reMap=new HashMap();
		List reList = null;
		reList=dao.queryList("jigouMapper.searchJigou", paraMap);
		
		if(reList.size()>0){
			reMap.put("result", "success");
		}else{
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}

	@Override
	public Map getList(Map<String, Object> paraMap) throws Exception {
		Map reMap=new HashMap();
		List reList = null;
		reList=dao.queryList("jigouMapper.queryList", paraMap);
		
		if(reList.size()>0){
			reMap.put("result", "success");
		}else{
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}

	/**
	 * 閸嬪洤鍨归梽锟� 閿涙碍鏁奸崣姒漧ag
	 */
	@Override
	public int delJiGou(Map<String, Object> paraMap) throws Exception {
		int i=dao.update("jigouMapper.updateJigou", paraMap);
		return i;
	}

	@Override
	public Map<String, Object> findJiGou(Map<String, Object> paraMap) throws Exception {
		Map reMap=null;
		List reList =dao.queryList("jigouMapper.queryList", paraMap);
		if(reList.size()>0){
			reMap=(Map)reList.get(0);
		}
		return reMap;
	}
	@Override
	public int updateJiGou(Map<String, Object> paraMap) throws Exception {
		int i=dao.update("jigouMapper.updateJigou", paraMap);
		return i;
	}
	
	@Override
	public int insertJiGou(Map<String, Object> data) throws Exception {
		int i=dao.insert("jigouMapper.insertJigou", data);
		return i;
	}

}
