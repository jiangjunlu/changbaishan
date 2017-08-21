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
	
	//存入参会人员信息  map里存放参数
	public int insertParticipant(Map map) throws Exception{
		int result = dao.save("participantMapper.saveParticipant", map);
		String renyuanName=map.get("renyuanName").toString();
		String renyuanEmail=map.get("renyuanEmail").toString();
		String huiyiId=map.get("huiyiId").toString();
		String url=map.get("url").toString();
		
		//开始发送邮件
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("receiver", renyuanEmail);
		paraMap.put("renyuanName", renyuanName);
		paraMap.put("huiyiId",huiyiId);
		paraMap.put("url",url);
		MailUtil.send(paraMap);
		
		return result;//影响记录的条数
	}
	
	public Map queryParticipant(Map map) throws Exception{
		Map reMap = new HashMap();//存放查询结果
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
