package cn.edu.cust.srvs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.srvs.UserItfc;

@Service(value="userSrv")
public class UserSrv implements UserItfc{
	@Resource(name="daoSupport")
	private DaoSupport dao;

	@Override
	public String check(String username) throws Exception {
		Map paraMap=new HashMap();
		paraMap.put("username",username);
		List list=dao.queryList("userMapper.queryList",paraMap);
		if(list!=null && list.size()>0){
			return "exist";
		}else{
			return "noexist";
		}
	}
	@Override
	public Map search(Map<String, Object> paraMap) throws Exception {
		Map reMap=new HashMap();
		List reList = null;
		reList=dao.queryList("userMapper.searchuser", paraMap);
		
		if(reList.size()>0){
			reMap.put("result", "success");
		}else{
			reMap.put("result", "fail");
		}
		reMap.put("list", reList);
		return reMap;
	}
	@Override
	public Map insertUser(Map paraMap) throws Exception {
		Map reMap=new HashMap();
		int i=dao.insert("userMapper.insertUser", paraMap);
		reMap.put("i", i);
		return reMap;
	}

	@Override
	public Map updateUser(Map paraMap) throws Exception {
		Map reMap=new HashMap();
		int i=dao.update("userMapper.updateUser", paraMap);
		reMap.put("i", i);
		return reMap;
	}

	@Override
	public int deleteUser(Map paraMap) throws Exception {
		paraMap.put("userFlag", 0);
		int i=dao.update("userMapper.updateUser", paraMap);
		return i;
	}

	@Override
	public Map queryUser(Map paraMap) throws Exception {
		Map reMap=new HashMap();
		List list=dao.queryList("userMapper.queryList", paraMap);
		if(list!=null&&list.size()>0){
			reMap=(Map)list.get(0);
		}
		return reMap;
	}
	@Override
	public Map getList(Map paraMap) throws Exception {
		Map reMap=new HashMap();
		List list=dao.queryList("userMapper.queryList", paraMap);
		if(list!=null&&list.size()>0){
			reMap.put("list", list);
		}
		return reMap;
	}
	public int count(Map<String, Object> paraMap) throws Exception{
		Map counter=(Map)dao.queryOne("userMapper.count", paraMap);
		int count=Integer.parseInt(counter.get("counter").toString());
		return count;
	}
}
