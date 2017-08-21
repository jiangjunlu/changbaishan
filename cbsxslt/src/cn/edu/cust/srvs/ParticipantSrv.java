package cn.edu.cust.srvs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;
import cn.edu.cust.util.MailUtil;

@Service(value="/participantSrv")
public class ParticipantSrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	//����λ���Ա��Ϣ  map���Ų���
	public int insertParticipant(Map map) throws Exception{
		int result = dao.save("participantMapper.saveParticipant", map);
		String renyuanName=map.get("renyuanName").toString();
		String renyuanEmail=map.get("renyuanEmail").toString();
		String huiyiId=map.get("huiyiId").toString();
		String url=map.get("url").toString();
		
		//��ʼ�����ʼ�
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("receiver", renyuanEmail);
		paraMap.put("renyuanName", renyuanName);
		paraMap.put("huiyiId",huiyiId);
		paraMap.put("url",url);
		MailUtil.send(paraMap);
		
		return result;//Ӱ���¼������
	}
	
	public Map queryParticipant(Map map) throws Exception{
		Map reMap = new HashMap();//��Ų�ѯ���
		List list = dao.findList("participantMapper.queryParticipant", map);
		if(list.size()>0){
			reMap.put("renyuanResultInfo", Info.SUCCESS);
			reMap.put("renyuanList", list);
			reMap.put("renyuanListSize",list.size());
		}else{
			reMap.put("renyuanResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	public int updateStatus(Map map) throws Exception{
		int result=dao.update("participantMapper.updateStatus", map);
		
		return result;
	}
}
