package cn.edu.cust.srvs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.srvs.SheQuItfc;

@Service(value="shequSrv")
public class SheQuSrv implements SheQuItfc{
	@Resource(name="daoSupport")
	private DaoSupport dao;

	public int count(Map<String, Object> paraMap) throws Exception{
		Map counter=(Map)dao.queryOne("shequMapper.count", paraMap);
		int count=Integer.parseInt(counter.get("counter").toString());
		return count;
	}
	
	@Override
	public Map getList(Map<String, Object> paraMap) throws Exception {
		Map reMap=new HashMap();
		List reList = null;
		reList=dao.queryList("shequMapper.queryList", paraMap);
		
		if(reList.size()>0){
			reMap.put("result", "success");
		}else{
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}
	@Override
	public Map search(Map<String, Object> paraMap) throws Exception {
		Map reMap=new HashMap();
		List reList = null;
		reList=dao.queryList("shequMapper.searchShequ", paraMap);
		
		if(reList.size()>0){
			reMap.put("result", "success");
		}else{
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}
	
	/**
	 * 鍋囧垹闄�锛氭敼鍙榝lag
	 */
	@Override
	public int delSheQu(Map<String, Object> paraMap) throws Exception {
		paraMap.put("shequFlag", 0);
		int i=dao.update("shequMapper.updateSheQu", paraMap);
		return i;
	}

	@Override
	public Map<String, Object> findSheQu(Map<String, Object> paraMap) throws Exception {
		Map reMap=null;
		List reList =dao.queryList("shequMapper.queryList", paraMap);
		if(reList.size()>0){
			reMap=(Map)reList.get(0);
		}
		return reMap;
	}

	@Override
	public int updateSheQu(Map<String, Object> paraMap) throws Exception {
		int i=dao.update("shequMapper.updateSheQu", paraMap);
		return i;
	}
	
	@Override
	public int insertSheQu(Map<String, Object> data) throws Exception {
		int i=dao.insert("shequMapper.insertSheQu", data);
		return i;
	}
	
	

	
	
}
