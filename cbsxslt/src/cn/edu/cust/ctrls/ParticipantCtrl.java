package cn.edu.cust.ctrls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.MeetingSrv;
import cn.edu.cust.srvs.ParticipantSrv;
import cn.edu.cust.util.Info;

@Controller
@RequestMapping(value = "/participant")
public class ParticipantCtrl {
	@Resource(name="/meetingSrv")
	private MeetingSrv meetingSrv;

	@Resource(name = "/participantSrv")
	private ParticipantSrv participantSrv;
	
	/**
	 * 点击注册按钮  查询出该会议   跳转到注册页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setInfo")
	public ModelAndView setInfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String huiyiId=request.getParameter("id");
		Map<Object,Object> paraMap = new HashMap<Object,Object>();
		paraMap.put("huiyiZhuangtai", 0);
		paraMap.put("huiyiId", huiyiId);
		try {
			Map meetingMap=meetingSrv.queryMeeting(paraMap);//存放会议查询结果

			if(Info.SUCCESS==(int)meetingMap.get("meetingResultInfo")){//判断会议查询结果是否合法
				Map meetInfo=(Map)(((List)meetingMap.get("meetingList")).get(0));//id符合条件的 查询结果  肯定只有一个map
				mv.addObject("meetingInfo",meetInfo);
				mv.setViewName("html/zhuce");
			}else if((int)meetingMap.get("meetingResultInfo")==Info.NO_RESULT){
				System.out.println((int)meetingMap.get("meetingResultInfo"));
				mv.setViewName("html/index");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=============================================================注册跳转出错");
		}
		return mv;
	}
	
	// 注册 以后要根据需求该paraMap
	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map<Object,Object> paraMap = new HashMap<Object,Object>();
		paraMap.put("huiyiZhuangtai", 0);
		String renyuanName = request.getParameter("renyuanName");
		String huiyiId = request.getParameter("huiyiId");
		String renyuanEmail = request.getParameter("renyuanEmail");
		String renyuanDianhua = request.getParameter("renyuanDianhua");
		String renyuanBeizhu = request.getParameter("renyuanBeizhu");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");

		paraMap.put("renyuanName", renyuanName);
		paraMap.put("huiyiId", huiyiId);
		paraMap.put("renyuanEmail", renyuanEmail);
		paraMap.put("renyuanDianhua", renyuanDianhua);
		paraMap.put("renyuanBeizhu", renyuanBeizhu);
		paraMap.put("beginDate", beginDate);
		paraMap.put("endDate", endDate);
		paraMap.put("url", request.getServerName()+":"+request.getServerPort()+"/cbsxslt");
		try {

			Map meetingMap=meetingSrv.queryMeeting(paraMap);//存放会议查询结果
			int counts = participantSrv.insertParticipant(paraMap);
			if (counts > 0) {
				mv.addObject("resultInfo", "success");
			} else {
				mv.addObject("resultInfo", "fail");
			}
			mv.addObject("meetingInfo", meetingMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("html/zhuce");
		return mv;
	}
	@RequestMapping(value = "/yanzheng")
	public ModelAndView ya(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String renyuanEmail=request.getParameter("e");
		String huiyiId=request.getParameter("hyid");
		Map<Object,Object> paraMap=new HashMap<Object, Object>();
		paraMap.put("renyuanEmail", renyuanEmail);
		paraMap.put("huiyiId", huiyiId);
		paraMap.put("renyuanFlag", 2);
		try {
			int affectedRows=participantSrv.updateStatus(paraMap);
			if(affectedRows>0){
				mv.addObject("resultInfo", "验证成功");
			}else{
				mv.addObject("resultInfo", "链接已失效");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("html/yanzheng");
		return mv;
	}
}
