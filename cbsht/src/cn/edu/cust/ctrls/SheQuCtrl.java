package cn.edu.cust.ctrls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.impl.SheQuSrv;

@Controller
@RequestMapping(value="/shequ")
public class SheQuCtrl {
	@Resource(name="shequSrv")
	private SheQuSrv shequSrv;
	/**
	 * 搜索社区
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "search")
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		String name = request.getParameter("s");
		String paraPage = request.getParameter("p");
		paraMap.put("shequName", "%" + name + "%");
		try {
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=shequSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = shequSrv.getList(paraMap);
			
			if(result!=null){
				List list=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("list", list);
			}
			Map meetingMap = shequSrv.search(paraMap);// 存放会议查询结果
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
		mv.setViewName("shequ-list");
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
				paraMap.put("lshequname", "%" + name + "%");
			}
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=shequSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = shequSrv.getList(paraMap);
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
		mv.setViewName("shequ-list");
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
		paraMap.put("shequId", id);
		try {
			Map detail=shequSrv.findSheQu(paraMap);
			mv.addObject("sq",detail);
			mv.addObject("p",page);
			mv.addObject("tableType","update");
			mv.setViewName("shequ-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="del")
	public ModelAndView deleteMeeting(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		int huiyiFlag=0;//闁烩偓鍔嬬花顒勫磻閸パ冪仼闂傚嫨鍊撶槐鎵媼閿燂拷
		int shequId=Integer.parseInt(request.getParameter("id"));
		Map paraMap=new HashMap();
		try {
			paraMap.put("shequId", shequId);
			int rowsAffectedNumber=shequSrv.delSheQu(paraMap);
			int page=Integer.parseInt(request.getParameter("p"));
			int count=shequSrv.count(paraMap);//鏌ヨ鎬昏褰曟暟
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
			Map result=shequSrv.getList(_paraMap);//鏌ヨ鎸囧畾椤典俊鎭�
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("shequ-list");
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
//		int shequId=Integer.parseInt(request.getParameter("id"));
		String shequName=request.getParameter("sqn");
		String shequJianJie=request.getParameter("sqjj");
//		paraMap.put("shequId", shequId);
		paraMap.put("shequName", shequName);
		paraMap.put("shequJianJie", shequJianJie);
		/****************************************/
		try {
			int rowsAffectedNumber=shequSrv.insertSheQu(paraMap);
			int page=1;
			int count=shequSrv.count(paraMap);//鏌ヨ鎬昏褰曟暟
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result=shequSrv.getList(paraMap);//鏌ヨ鎸囧畾椤典俊鎭�
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("shequ-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="update")
	public ModelAndView updateMeeting(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		int shequId=Integer.parseInt(request.getParameter("id"));
		String shequName=request.getParameter("sqn");
		String shequJianJie=request.getParameter("sqjj");
		Map paraMap=new HashMap();
		try {
			paraMap.put("shequId", shequId);
			paraMap.put("shequName", shequName);
			paraMap.put("shequJianJie", shequJianJie);
			int rowsAffectedNumber=shequSrv.updateSheQu(paraMap);
			String paraPage=request.getParameter("p");
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=shequSrv.count(paraMap);//鏌ヨ鎬昏褰曟暟
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
			Map result=shequSrv.getList(_paraMap);//鏌ヨ鎸囧畾椤典俊鎭�
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("shequ-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
