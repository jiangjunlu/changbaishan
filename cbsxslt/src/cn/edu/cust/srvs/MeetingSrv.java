package cn.edu.cust.srvs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cust.dao.DaoSupport;
import cn.edu.cust.util.Info;

@Service(value="/meetingSrv")
public class MeetingSrv {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	/**
	 * 
	 * @param map ��map��ѯ
	 * @return map(����list,code)
	 * @throws Exception
	 */
	public Map queryMeeting(Map map) throws Exception{
		Map reMap=new HashMap();
		List list=dao.findList("meetingMapper.queryMeeting", map);
		if(list.size()>0){
			reMap.put("meetingList", list);
			reMap.put("meetingListSize", list.size());
			reMap.put("meetingResultInfo", Info.SUCCESS); 
		}else{
			reMap.put("meetingResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	
	/**
	 * 
	 * @param map ��map��ѯ
	 * @return map(����list,code)
	 * @throws Exception
	 */
	public Map getIndex() throws Exception{
		Map reMap=new HashMap();
		Map paraMap=new HashMap();
		List reList=new ArrayList();
		paraMap.put("huiyiZhuangtai", 0);//0����δ�ٿ�����������ݿ�map��
		List list=dao.findList("meetingMapper.queryMeeting", paraMap);
		if(list.size()>0){
			for(int i=0;i<list.size();i=i+3){
				List l=new ArrayList();//�ݴ�3������
				for(int j=0;j<3&&(i+j)<list.size();j++){
					l.add(list.get(i+j));
				}
				reList.add(l);
			}
			reMap.put("meetingList", reList);
			reMap.put("meetingListSize", list.size());
			reMap.put("meetingResultInfo", Info.SUCCESS); 
		}else{
			reMap.put("meetingResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
	
	public Map queryMeetingTitle(Map map) throws Exception{
		Map reMap=new HashMap();
		List list = new ArrayList();//���ȥ���ظ������ս��
		List<Map> listChinese=dao.findList("meetingMapper.queryMeetingTitleByZhongwenName", map);
		List<Map> listEnglish=dao.findList("meetingMapper.queryMeetingTitleByYingwenName", map);
		for(Map itemChinese:listChinese){
			for(int i=0;i<listEnglish.size();i++){
				Map itemEnglish=listEnglish.get(i);
				if((long)itemChinese.get("huiyi_id")==(long)itemEnglish.get("huiyi_id")){//id�ظ� ���� ��Ϣ�ظ�
					listEnglish.remove(i);
				}
			}
		}
		list.addAll(listChinese);
		list.addAll(listEnglish);
		if(list.size()>0){
			reMap.put("meetingList", list);
			reMap.put("meetingListSize", list.size());
			reMap.put("meetingResultInfo", Info.SUCCESS); 
		}else{
			reMap.put("meetingResultInfo", Info.NO_RESULT);
		}
		return reMap;
	}
}
