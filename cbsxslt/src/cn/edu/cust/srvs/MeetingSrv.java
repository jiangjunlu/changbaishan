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
	 * @param map 按map查询
	 * @return map(含有list,code)
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
	 * @param map 按map查询
	 * @return map(含有list,code)
	 * @throws Exception
	 */
	public Map getIndex() throws Exception{
		Map reMap=new HashMap();
		Map paraMap=new HashMap();
		List reList=new ArrayList();
		paraMap.put("huiyiZhuangtai", 0);//0代表未召开，详情见数据库map表
		List list=dao.findList("meetingMapper.queryMeeting", paraMap);
		if(list.size()>0){
			for(int i=0;i<list.size();i=i+3){
				List l=new ArrayList();//暂存3个会议
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
		List list = new ArrayList();//存放去除重复的最终结果
		List<Map> listChinese=dao.findList("meetingMapper.queryMeetingTitleByZhongwenName", map);
		List<Map> listEnglish=dao.findList("meetingMapper.queryMeetingTitleByYingwenName", map);
		for(Map itemChinese:listChinese){
			for(int i=0;i<listEnglish.size();i++){
				Map itemEnglish=listEnglish.get(i);
				if((long)itemChinese.get("huiyi_id")==(long)itemEnglish.get("huiyi_id")){//id重复 代表 信息重复
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
