package cn.edu.cust.ctrls;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.edu.cust.srvs.MeetingSrv;
import cn.edu.cust.util.Info;

@Controller
@RequestMapping("search")
public class SearchCtrl {
	@Resource(name="/meetingSrv")
	private MeetingSrv meetingSrv;
	
	@ResponseBody
	@RequestMapping("meetingTitle")
	public String getMeetingTitle(HttpServletRequest request){
		String jsonStr="";
		String name=request.getParameter("name");
		Map paraMap = new HashMap();
		paraMap.put("name","%"+name+"%");
		try {
			Map map=meetingSrv.queryMeetingTitle(paraMap);
			jsonStr=JSON.toJSONString(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	@RequestMapping("searchGo")
	public ModelAndView searchGo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map paraMap=new HashMap();//存放查询条件
		String name=request.getParameter("s");
		paraMap.put("zhongwenName", "%"+name+"%");
		paraMap.put("yingwenName", "%"+name+"%");
		try {
			Map meetingMap=meetingSrv.queryMeeting(paraMap);//存放会议查询结果
			if(Info.SUCCESS==(int)meetingMap.get("meetingResultInfo")){//判断会议查询结果是否合法
				System.out.println(meetingMap);
				mv.addObject("meetingInfo",meetingMap.get("meetingList"));
			}else if((int)meetingMap.get("meetingResultInfo")==Info.NO_RESULT){
				System.out.println((int)meetingMap.get("meetingResultInfo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("html/huiyi");
		return mv;
		
	}
}
