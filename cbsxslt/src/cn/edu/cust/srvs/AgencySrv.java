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
	 * ��ѯ����
	 * @param map  jigouJuese ��Ż�����ɫ��ʶ
	 * @return
	 * @throws Exception 
	 */
	public Map queryAgencySrv(Map map) throws Exception{
		Map reMap = new HashMap();
		List list = dao.findList("agencyMapper.queryAgency", map);
		if(list.size()>0){//��ѯ�н��,��������Ϣ
			reMap.put("agencyResultInfo", Info.SUCCESS);
			reMap.put("agencyList", list);
			reMap.put("agencyListSize",list.size());
		}else{//��ѯû�н��
			reMap.put("agencyResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	
}
