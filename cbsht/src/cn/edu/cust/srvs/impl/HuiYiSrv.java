package cn.edu.cust.srvs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.srvs.HuiYiItfc;

@Service(value = "huiyiSrv")
public class HuiYiSrv implements HuiYiItfc {
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public int count(Map<String, Object> paraMap) throws Exception {
		Map counter = (Map) dao.queryOne("huiyiMapper.count", paraMap);
		int count = Integer.parseInt(counter.get("counter").toString());
		return count;
	}
	public int countRenyuan(Map<String, Object> paraMap) throws Exception {
		Map counter = (Map) dao.queryOne("huiyiMapper.countRenyuan", paraMap);
		int count = Integer.parseInt(counter.get("counter").toString());
		return count;
	}
	public Map getRenyuan(Map paraMap) throws Exception {
		Map reMap = new HashMap();
		List reList = null;
		reList = dao.queryList("huiyiMapper.queryRenyuan", paraMap);
		if (reList.size() > 0) {
			reMap.put("result", "success");
		} else {
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}
	
	@Override
	public Map getList(Map<String, Object> paraMap) throws Exception {
		Map reMap = new HashMap();
		List reList = null;
		reList = dao.queryList("huiyiMapper.queryList", paraMap);
		for(int i=0;i<reList.size();i++){
			Object object=((Map)(reList.get(i))).get("huiyi_id");
			if(object!=null){
				int hyid=Integer.parseInt(object.toString());
				Map map=new HashMap<>();
				map.put("huiyiId", hyid);
				Map ry=(Map)getRenyuan(map);
				((Map)(reList.get(i))).put("renyuan", ry.get("list"));
			}
		}
		if (reList.size() > 0) {
			reMap.put("result", "success");
		} else {
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}

	@Override
	public Map search(Map<String, Object> paraMap) throws Exception {
		Map reMap = new HashMap();
		List reList = null;
		reList = dao.queryList("huiyiMapper.searchHuiyi", paraMap);
		for(int i=0;i<reList.size();i++){
			Object object=((Map)(reList.get(i))).get("huiyi_id");
			if(object!=null){
				int hyid=Integer.parseInt(object.toString());
				Map map=new HashMap<>();
				map.put("huiyiId", hyid);
				Map ry=(Map)getRenyuan(map);
				((Map)(reList.get(i))).put("renyuan", ry.get("list"));
			}
		}
		if (reList.size() > 0) {
			reMap.put("result", "success");
		} else {
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}

	/**
	 * 鍋囧垹闄� 锛氭敼鍙榝lag
	 */
	@Override
	public int delHuiYi(Map<String, Object> paraMap) throws Exception {
		int i = dao.update("huiyiMapper.updateHuiyi", paraMap);
		return i;
	}

	@Override
	public Map<String, Object> findHuiYi(Map<String, Object> paraMap) throws Exception {
		Map reMap = null;
		List reList = dao.queryList("huiyiMapper.queryList", paraMap);
		for(int i=0;i<reList.size();i++){
			Object object=((Map)(reList.get(i))).get("huiyi_id");
			if(object!=null){
				int hyid=Integer.parseInt(object.toString());
				Map map=new HashMap<>();
				map.put("huiyiId", hyid);
				Map ry=(Map)getRenyuan(map);
				((Map)(reList.get(i))).put("renyuan", ry.get("list"));
			}
		}
		if (reList.size() > 0) {
			reMap = (Map) reList.get(0);
		}
		return reMap;
	}

	@Override
	public int updateHuiYi(Map<String, Object> paraMap) throws Exception {
		int i = dao.update("huiyiMapper.updateHuiyi", paraMap);
		return i;
	}

	@Override
	public int insertHuiYi(Map<String, Object> data) throws Exception {
		int i = dao.insert("huiyiMapper.insertHuiyi", data);
		return i;
	}

	
	/**
	 *更改 会议 的状态 
	 * @throws Exception 
	 */
	
	public int updateHuiYiReview(Map paraMap) throws Exception{
		int count=0;
		count=dao.update("huiyiMapper.reviewedHuiYi", paraMap);
		return count;
	}
	
	
	public Map getRList(Map<String, Object> paraMap) throws Exception {
		Map reMap = new HashMap();
		List reList = null;
		reList = dao.queryList("huiyiMapper.queryRList", paraMap);
		for(int i=0;i<reList.size();i++){
			Object object=((Map)(reList.get(i))).get("huiyi_id");
			if(object!=null){
				int hyid=Integer.parseInt(object.toString());
				Map map=new HashMap<>();
				map.put("huiyiId", hyid);
				Map ry=(Map)getRenyuan(map);
				((Map)(reList.get(i))).put("renyuan", ry.get("list"));
			}
		}
		if (reList.size() > 0) {
			reMap.put("result", "success");
		} else {
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}
}
