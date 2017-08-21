package cn.edu.cust.ctrls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.impl.LunWenSrv;
import cn.edu.cust.srvs.impl.SheQuSrv;

@Controller
@RequestMapping(value="/lunwen")
public class LunWenCtrl {
	@Resource(name="lunwenSrv")
	private LunWenSrv lunwenSrv;
	@Resource(name="shequSrv")
	private SheQuSrv shequSrv;
	/**
	 * 搜索论文
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "search")
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		String name = request.getParameter("s");
		String paraPage = request.getParameter("p");
		paraMap.put("lunwenName", "%" + name + "%");
		try {
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=lunwenSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = lunwenSrv.getList(paraMap);
			if(result!=null){
				List list=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("list", list);
			}
			Map meetingMap = lunwenSrv.search(paraMap);// 存放会议查询结果
			if ("success".equals(meetingMap.get("result").toString())) {// 判断会议查询结果是否合法
				System.out.println(meetingMap);
				mv.addObject("list", meetingMap.get("list"));
			} else if ("no_reaul".equals(meetingMap.get("resual").toString())) {
				System.out.println(meetingMap.get("resual").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("s",name);
		mv.setViewName("lunwen-list");
		return mv;

	}
	/**
	 * 
	 * @return all meeting information,and include overdue meetings
	 */
	@RequestMapping(value="list")
	public ModelAndView getList(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		ModelAndView mv = new ModelAndView();
		Map paraMap=new HashMap();
		try {
			String paraPage=request.getParameter("p");
			request.setCharacterEncoding("utf-8");
			String name=request.getParameter("s");
			//String name=new String(request.getParameter("s").getBytes("iso-8859-1"),"utf-8");
			if(name!=null&&!"".equals(name)){
				paraMap.put("llunwenName", "%" + name + "%");
			}
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=lunwenSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = lunwenSrv.getList(paraMap);
			if(result!=null){
				List list=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("s", name);
				mv.addObject("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("lunwen-list");
		return mv;
	}
	/**
	 * 
	 * @return a meeting all information by id
	 */
	@RequestMapping(value="xq")
	public ModelAndView getDetail(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		Map paraMap=new HashMap();
		int id=Integer.parseInt(request.getParameter("id"));
		int page=Integer.parseInt(request.getParameter("p"));
		paraMap.put("lunwenId", id);
		try {
			List sysq=(List) shequSrv.getList(null).get("list");
			Map detail=lunwenSrv.findLunWen(paraMap);
			mv.addObject("lw",detail);
			mv.addObject("sysq",sysq);
			mv.addObject("p",page);
			mv.addObject("tableType","update");
			mv.setViewName("lunwen-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="del")
	public ModelAndView deleteMeeting(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		int lunwenFlag=0;//鐢ㄤ簬鍋囧垹闄�
		int lunwenId=Integer.parseInt(request.getParameter("id"));
		Map paraMap=new HashMap();
		try {
			paraMap.put("lunwenId", lunwenId);
			paraMap.put("lunwenFlag", lunwenFlag);
			int rowsAffectedNumber=lunwenSrv.delLunWen(paraMap);
			int page=Integer.parseInt(request.getParameter("p"));
			int count=lunwenSrv.count(paraMap);//鏌ヨ鎬昏褰曟暟
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			Map _paraMap=new HashMap();
			_paraMap.put("limitBegin", limitBegin);
			Map result=lunwenSrv.getList(_paraMap);//鏌ヨ鎸囧畾椤典俊鎭�
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("lunwen-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value="insert")
	public ModelAndView insertMeeting(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		Map paraMap=new HashMap();
		
		/*********鑾峰彇浼氳鎵�鏈夊弬鏁�*****************/
//		int lunwenId=Integer.parseInt(request.getParameter("id"));
		String lunwenLeibie=request.getParameter("lwlb");
		String lunwenGuanjianci=request.getParameter("lwgjc");
		String lunwenZhaiyao=request.getParameter("lwzy");
		String lunwenChuliZhuangtai=request.getParameter("lwclzt");
		String lunwenNeirong=request.getParameter("lwnr");
		String[] suoshuShequ=request.getParameterValues("sssq");
//		paraMap.put("lunwenId", lunwenId);

		paraMap.put("suoshuShequ", suoshuShequ);
		paraMap.put("lunwenLeibie", lunwenLeibie);
		paraMap.put("lunwenGuanjianci", lunwenGuanjianci);
		paraMap.put("lunwenZhaiyao", lunwenZhaiyao);
		paraMap.put("lunwenChuliZhuangtai", lunwenChuliZhuangtai);
		paraMap.put("lunwenNeirong", lunwenNeirong);
		/****************************************/
		try {
			int lwid=lunwenSrv.getMaxId()+1;
			paraMap.put("lunwenId", lwid);
			lunwenSrv.updateSuoshuShequ(lwid,suoshuShequ);
			int affectedrows=lunwenSrv.insertLunWen(paraMap);
			int page=1;
			int count=lunwenSrv.count(null);//鏌ヨ鎬昏褰曟暟
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result=lunwenSrv.getList(null);//鏌ヨ鎸囧畾椤典俊鎭�
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("lunwen-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="new")
	public ModelAndView toInsertPage(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();String paraPage=request.getParameter("p");
		int page=1;
		if(paraPage!=null &&!"".equals(paraPage)){
			page=Integer.parseInt(paraPage);
		}
		try {
			List shequ=(List)shequSrv.getList(null).get("list");//query all jigou
			mv.addObject("shequ",shequ);
			mv.addObject("tableType","insert");
			mv.setViewName("lunwen-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;	
	}
	
	@RequestMapping(value="update")
	public ModelAndView updateMeeting(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
//		int lunwenFlag=0;//鐢ㄤ簬鍋囧垹闄や細璁�
		int lunwenId=Integer.parseInt(request.getParameter("id"));
		String lunwenLeibie=request.getParameter("lwlb");
		String lunwenGuanjianci=request.getParameter("lwgjc");
		String lunwenZhaiyao=request.getParameter("lwzy");
		String lunwenChuliZhuangtai=request.getParameter("lwclzt");
		String lunwenNeirong=request.getParameter("lwnr");
		String[] suoshuShequ=request.getParameterValues("sssq");
		Map paraMap=new HashMap();
		try {
			paraMap.put("lunwenId", lunwenId);
			lunwenSrv.updateSuoshuShequ(lunwenId,suoshuShequ);
			paraMap.put("lunwenLeibie", lunwenLeibie);
			paraMap.put("lunwenGuanjianci", lunwenGuanjianci);
			paraMap.put("lunwenZhaiyao", lunwenZhaiyao);
			paraMap.put("lunwenChuliZhuangtai", lunwenChuliZhuangtai);
			paraMap.put("lunwenNeirong", lunwenNeirong);
			int rowsAffectedNumber=lunwenSrv.delLunWen(paraMap);
			int page=Integer.parseInt(request.getParameter("p"));
			int count=lunwenSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			Map _paraMap=new HashMap();
			_paraMap.put("limitBegin", limitBegin);
			Map result=lunwenSrv.getList(_paraMap);
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("lunwen-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
