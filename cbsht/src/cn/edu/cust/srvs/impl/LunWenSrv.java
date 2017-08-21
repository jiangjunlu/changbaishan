package cn.edu.cust.srvs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.srvs.LunWenItfc;

@Service(value="lunwenSrv")
public class LunWenSrv implements LunWenItfc{
	@Resource(name="daoSupport")
	private DaoSupport dao;

	public int count(Map<String, Object> paraMap) throws Exception{
		Map counter=(Map)dao.queryOne("lunwenMapper.count", paraMap);
		int count=Integer.parseInt(counter.get("counter").toString());
		return count;
	}
	public int getMaxId() throws Exception{
		int id=-1;
		Map map=(Map)dao.queryOne("lunwenMapper.queryMaxId", null);
		if(map.get("id")!=null){
			id=Integer.parseInt(map.get("id").toString());
		}
		return id;
	}
	
	public int updateSuoshuShequ(int lwid,String[] sssq){
		int result=-1;
		Map paraMap=new HashMap<>();
		paraMap.put("lunwenId", lwid);
		try {
			dao.delete("lunwenMapper.deleteRelation", paraMap);
			for(int i=0;i<sssq.length;i++){
				paraMap.put("shequId",Integer.parseInt(sssq[i]));
					dao.insert("lunwenMapper.insertRelation", paraMap);
				result=i+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public Map search(Map<String, Object> paraMap) throws Exception {
		Map reMap=new HashMap();
		List reList = null;
		reList=dao.queryList("lunwenMapper.searchLunwen", paraMap);
		
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
		reList=dao.queryList("lunwenMapper.queryList", paraMap);
		for(int i=0;i<reList.size();i++){
			Map pm=new HashMap<>();
			Object lunwenIdObj=((Map)(reList.get(i))).get("lunwen_id");
			int lunwenIdInt=-1;
			if(lunwenIdObj!=null){
				lunwenIdInt=Integer.parseInt(lunwenIdObj.toString());
			}
			pm.put("lunwenId",lunwenIdInt );
			List sssq=dao.queryList("lunwenMapper.queryItsPart", pm);//所属社区
			((Map)(reList.get(i))).put("sssq", sssq);
		}
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
	public int delLunWen(Map<String, Object> paraMap) throws Exception {
		int i=dao.update("lunwenMapper.updateLunwen", paraMap);
		return i;
	}

	@Override
	public Map<String, Object> findLunWen(Map<String, Object> paraMap) throws Exception {
		Map reMap=null;
		List reList =dao.queryList("lunwenMapper.queryList", paraMap);
		for(int i=0;i<reList.size();i++){
			Map pm=new HashMap<>();
			Object lunwenIdObj=((Map)(reList.get(i))).get("lunwen_id");
			int lunwenIdInt=-1;
			if(lunwenIdObj!=null){
				lunwenIdInt=Integer.parseInt(lunwenIdObj.toString());
			}
			pm.put("lunwenId",lunwenIdInt );
			List sssq=dao.queryList("lunwenMapper.queryItsPart", pm);//所属社区
			((Map)(reList.get(i))).put("sssq", sssq);
		}
		if(reList.size()>0){
			reMap=(Map)reList.get(0);
		}
		return reMap;
	}

	@Override
	public int updateLunWen(Map<String, Object> paraMap) throws Exception {
		int i=dao.update("lunwenMapper.updateLunwen", paraMap);
		return i;
	}
	
	@Override
	public int insertLunWen(Map<String, Object> data) throws Exception {
		int i=dao.insert("lunwenMapper.insertLunwen", data);
		return i;
	}
	
	

	
	
}
