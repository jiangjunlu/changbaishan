package cn.edu.cust.srvs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;

@Service("/agencySrv")
public class AgencySrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	/**
	 * 查询机构
	 * @param map  jigouJuese 存放机构角色标识
	 * @return
	 * @throws Exception 
	 */
	public Map queryAgencySrv(Map map) throws Exception{
		Map reMap = new HashMap();
		List list = dao.findList("agencyMapper.queryAgency", map);
		if(list.size()>0){//查询有结果,存放相关信息
			reMap.put("agencyResultInfo", Info.SUCCESS);
			reMap.put("agencyList", list);
			reMap.put("agencyListSize",list.size());
		}else{//查询没有结果
			reMap.put("agencyResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	
}
