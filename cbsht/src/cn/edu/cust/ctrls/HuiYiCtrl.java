package cn.edu.cust.ctrls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.impl.HuiYiSrv;
import cn.edu.cust.srvs.impl.JiGouSrv;

@Controller
@RequestMapping(value = "/huiyi")
public class HuiYiCtrl {
	@Resource(name = "huiyiSrv")
	private HuiYiSrv huiyiSrv;
	@Resource(name = "jigouSrv")
	private JiGouSrv jigouSrv;
	@RequestMapping(value = "search")
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		String name = request.getParameter("s");
		String paraPage = request.getParameter("p");
		paraMap.put("zhongwenName", "%" + name + "%");
		paraMap.put("yingwenName", "%" + name + "%");
		try {
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=huiyiSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = huiyiSrv.getList(paraMap);
			if(result!=null){
				List list=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("list", list);
			}
			Map meetingMap = huiyiSrv.search(paraMap);// 存放会议查询结果
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
		mv.setViewName("huiyi-list");
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
			String fk_user_id=request.getParameter("fk_user_id");
			String role=request.getParameter("role");
			request.setCharacterEncoding("utf-8");
			String name=request.getParameter("s");
			paraMap.put("fk_user_id", fk_user_id);
			Map result=new HashMap<>();
			//String name=new String(request.getParameter("s").getBytes("iso-8859-1"),"utf-8");
			if(name!=null&&!"".equals(name)){
				paraMap.put("lzhongwenName", "%" + name + "%");
				paraMap.put("lyingwenName", "%" + name + "%");
			}
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=huiyiSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			
			if(role.equals("0")){
				result = huiyiSrv.getList(paraMap);
			}else{
				result = huiyiSrv.getRList(paraMap);
			}
		
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
		mv.setViewName("huiyi-list");
		return mv;
	}
	/**
	 * 
	 * @return a meeting all information by id
	 */
	@RequestMapping(value = "xq")
	public ModelAndView getDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();
		int id = Integer.parseInt(request.getParameter("id"));
		int page = Integer.parseInt(request.getParameter("p"));
		paraMap.put("huiyiId", id);
		try {
			Map detail = huiyiSrv.findHuiYi(paraMap);
			List jigou = (List) jigouSrv.getList(null).get("list");// query all
																	// jigou
			String[] zhubanfang = null;
			String[] xiebanfang = null;
			String[] laibanfang = null;
			if (detail.get("huiyi_zhubanfang") != null) {
				zhubanfang = detail.get("huiyi_zhubanfang").toString().split(" ");
			}
			List zbf = queryJigou(zhubanfang);
			detail.remove("huiyi_zhubanfang");
			if (detail.get("huiyi_laibanfang") != null) {
				laibanfang = detail.get("huiyi_laibanfang").toString().split(" ");
			}
			List lbf = queryJigou(laibanfang);
			detail.remove("huiyi_laibanfang");
			if (detail.get("huiyi_xiebanfang") != null) {
				xiebanfang = detail.get("huiyi_xiebanfang").toString().split(" ");
			}
			List xbf = queryJigou(xiebanfang);
			detail.remove("huiyi_xiebanfang");
			detail.put("huiyi_zhubanfang", zbf);
			detail.put("huiyi_xiebanfang", xbf);
			detail.put("huiyi_laibanfang", lbf);
			mv.addObject("hy", detail);
			mv.addObject("p", page);
			mv.addObject("jigou", jigou);
			mv.addObject("tableType", "update");
			mv.setViewName("huiyi-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "del")
	public ModelAndView deleteMeeting(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int huiyiFlag = 0;// 鐢ㄤ簬鍋囧垹闄や細璁�
		int huiyiId = Integer.parseInt(request.getParameter("id"));
		Map paraMap = new HashMap();
		try {
			paraMap.put("huiyiId", huiyiId);
			paraMap.put("huiyiFlag", huiyiFlag);
			int rowsAffectedNumber = huiyiSrv.delHuiYi(paraMap);
			int page = Integer.parseInt(request.getParameter("p"));
			int count = huiyiSrv.count(paraMap);// 鏌ヨ鎬昏褰曟暟
			int pageSize = 10;
			int pages = (count - 1) / pageSize + 1;
			if (page < 1) {
				page = 1;
			} else if (page > pages) {
				page = pages;
			}
			int limitBegin = (page - 1) * pageSize;
			Map _paraMap = new HashMap();
			_paraMap.put("limitBegin", limitBegin);
			Map result = huiyiSrv.getList(_paraMap);// 鏌ヨ鎸囧畾椤典俊鎭�
			String resultType = (String) result.get("result");
			List list = (List) result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("huiyi-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 
	 * @param request
	 * @return to huiyi page
	 */
	@RequestMapping(value = "ry")
	public ModelAndView getRenyuan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap<>();
		String paraPage = request.getParameter("p");
		int hyid=Integer.parseInt(request.getParameter("id"));
		paraMap.put("huiyiId", hyid);
		int page=1;
		if(paraPage!=null &&!"".equals(paraPage)){
			page=Integer.parseInt(paraPage);
		}
		int count;
		try {
			count = huiyiSrv.countRenyuan(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Map huiyi = (Map) huiyiSrv.findHuiYi(paraMap);// query all
			mv.addObject("huiyi", huiyi);
			mv.setViewName("renyuanxiangqing");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value = "new")
	public ModelAndView toInsertPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String paraPage = request.getParameter("p");
		int page = 1;
		if (paraPage != null && !"".equals(paraPage)) {
			page = Integer.parseInt(paraPage);
		}
		try {
			List jigou = (List) jigouSrv.getList(null).get("list");// query all
																	// jigou
			mv.addObject("jigou", jigou);
			mv.addObject("tableType", "insert");
			mv.setViewName("huiyi-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value = "insert")
	public ModelAndView insertMeeting(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();
		/********* 鑾峰彇浼氳鎵�湁鍙傛暟 *****************/
		// int huiyiId=Integer.parseInt(request.getParameter("id"));
		String zhongwenName = request.getParameter("zwn");
		String yingwenName = request.getParameter("ywn");
		String zhaokaiTime = request.getParameter("zkt");
		String jieshuTime = request.getParameter("jst");
		String huiyiNeirong = request.getParameter("hynr");
		String huiyiZhaiyao = request.getParameter("hyzy");
		String huiyiDidian = request.getParameter("hydd");
		String huiyiZhuangtai = request.getParameter("hyzt");
		String huiyiGuanjianci = request.getParameter("hygjc");
		String huiyiZhubanfang = request.getParameter("hyzbf");
		String huiyiXiebanfang = request.getParameter("hyxbf");
		String huiyiLaibanfang = request.getParameter("hylbf");
		String huiyiXinwen = request.getParameter("hyxw");
		String fk_user_id = request.getParameter("userid");
		
		
		// paraMap.put("huiyiId", huiyiId);
		paraMap.put("zhongwenName", zhongwenName);
		paraMap.put("yingwenName", yingwenName);
		paraMap.put("zhaokaiTime", zhaokaiTime);
		paraMap.put("jieshuTime", jieshuTime);
		paraMap.put("huiyiNeirong", huiyiNeirong);
		paraMap.put("huiyiZhaiyao", huiyiZhaiyao);
		paraMap.put("huiyiDidian", huiyiDidian);
		paraMap.put("huiyiZhuangtai", huiyiZhuangtai);
		paraMap.put("huiyiGuanjianci", huiyiGuanjianci);
		paraMap.put("huiyiZhubanfang", huiyiZhubanfang);
		paraMap.put("huiyiXiebanfang", huiyiXiebanfang);
		paraMap.put("huiyiLaibanfang", huiyiLaibanfang);
		paraMap.put("huiyiXinwen", huiyiXinwen);
		paraMap.put("userid", fk_user_id);
		/****************************************/
		try {
			int rowsAffectedNumber = huiyiSrv.insertHuiYi(paraMap);
			int page = 1;
			int count = huiyiSrv.count(paraMap);// parameter map
			int pageSize = 10;
			int pages = (count - 1) / pageSize + 1;
			int limitBegin = (page - 1) * pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = huiyiSrv.getList(paraMap);// 鏌ヨ鎸囧畾椤典俊鎭�
			String resultType = (String) result.get("result");
			List list = (List) result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("huiyi-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "update")
	public ModelAndView updateMeeting(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// int huiyiFlag=0;//鐢ㄤ簬鍋囧垹闄や細璁�
		int huiyiId = Integer.parseInt(request.getParameter("id"));
		String zhongwenName = request.getParameter("zwn");
		String yingwenName = request.getParameter("ywn");
		String zhaokaiTime = request.getParameter("zkt");
		String jieshuTime = request.getParameter("jst");
		String huiyiNeirong = request.getParameter("hynr");
		String huiyiZhaiyao = request.getParameter("hyzy");
		String huiyiDidian = request.getParameter("hydd");
		String huiyiZhuangtai = request.getParameter("hyzt");
		String huiyiGuanjianci = request.getParameter("hygjc");
		String[] hyzbf = request.getParameterValues("hyzbf");
		String[] hylbf = request.getParameterValues("hylbf");
		String[] hyxbf = request.getParameterValues("hyxbf");
		String huiyiZhubanfang = "";
		String huiyiXiebanfang = "";
		String huiyiLaibanfang = "";
		for (int i = 0; hyzbf != null && i < hyzbf.length; i++) {
			huiyiZhubanfang += " " + hyzbf[i];
		}
		for (int i = 0; hyxbf != null && i < hyxbf.length; i++) {
			huiyiXiebanfang += " " + hyxbf[i];
		}
		for (int i = 0; hylbf != null && i < hylbf.length; i++) {
			huiyiLaibanfang += " " + hylbf[i];
		}
		String huiyiXinwen = request.getParameter("hyxw");
		Map paraMap = new HashMap();
		try {
			paraMap.put("huiyiId", huiyiId);
			paraMap.put("zhongwenName", zhongwenName);
			paraMap.put("yingwenName", yingwenName);
			paraMap.put("zhaokaiTime", zhaokaiTime);
			paraMap.put("jieshuTime", jieshuTime);
			paraMap.put("huiyiNeirong", huiyiNeirong);
			paraMap.put("huiyiZhaiyao", huiyiZhaiyao);
			paraMap.put("huiyiDidian", huiyiDidian);
			paraMap.put("huiyiZhuangtai", huiyiZhuangtai);
			paraMap.put("huiyiGuanjianci", huiyiGuanjianci);
			paraMap.put("huiyiZhubanfang", huiyiZhubanfang);
			paraMap.put("huiyiXiebanfang", huiyiXiebanfang);
			paraMap.put("huiyiLaibanfang", huiyiLaibanfang);
			paraMap.put("huiyiXinwen", huiyiXinwen);
			int rowsAffectedNumber = huiyiSrv.delHuiYi(paraMap);
			int page = Integer.parseInt(request.getParameter("p"));
			int count = huiyiSrv.count(paraMap);// 鏌ヨ鎬昏褰曟暟
			int pageSize = 10;
			int pages = (count - 1) / pageSize + 1;
			if (page < 1) {
				page = 1;
			} else if (page > pages) {
				page = pages;
			}
			int limitBegin = (page - 1) * pageSize;
			Map _paraMap = new HashMap();
			_paraMap.put("limitBegin", limitBegin);
			Map result = huiyiSrv.getList(_paraMap);// 鏌ヨ鎸囧畾椤典俊鎭�
			String resultType = (String) result.get("result");
			List list = (List) result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("huiyi-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 閫昏緫锛氭甯告潵璁诧紝id涓嶄负绌猴紝鑲畾鑳芥煡鍒拌鏈烘瀯銆傚洜涓鸿繖涓猧d鏄細璁〃閲屽瓨鐨勶紝鑰屽瓨鐨勬椂鍊欏凡缁忕‘淇濊id姝ｇ‘
	 * 涓轰簡闃叉涓嶆甯告儏鍐碉紝璁╅櫎浜嗏�姝ｅ父鎯呭喌鈥濅互澶栵紝閮借繑鍥瀗ull
	 * 
	 * @param String
	 *            [] id
	 * @return 鍙娌＄粨鏋滐紝涓�畾鏄痭ull
	 */
	public List queryJigou(String[] id) {
		if (id == null)
			return null;
		Map paraMap = new HashMap();
		List reList = new ArrayList();
		boolean isNull = true;
		for (int i = 0; i < id.length; i++) {
			int idd = -1;
			if (id[i] != null && !"".equals(id[i])) {
				idd = Integer.parseInt(id[i]);
			}
			paraMap.remove("jigouId");
			paraMap.put("jigouId", idd);
			try {
				Map jigouMap = jigouSrv.getList(paraMap);
				List list = (List) jigouMap.get("list");
				Map detail = null;
				if (list != null && list.size() > 0) {
					detail = (Map) list.get(0);
				}
				if (detail != null && detail.get("jigou_id") != null && !"".equals(detail.get("jigou_id").toString())) {
					reList.add(detail);
					isNull = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (isNull)
			return null;
		else
			return reList;
	}
	
	
	
	
	@RequestMapping(value="list_review")
	public ModelAndView getListReview(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map paraMap=new HashMap();
		try {
			String paraPage=request.getParameter("p");
			request.setCharacterEncoding("utf-8");
			String name=request.getParameter("s");
			//String name=new String(request.getParameter("s").getBytes("iso-8859-1"),"utf-8");
			if(name!=null&&!"".equals(name)){
				paraMap.put("lzhongwenName", "%" + name + "%");
				paraMap.put("lyingwenName", "%" + name + "%");
			}
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=huiyiSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = huiyiSrv.getRList(paraMap);
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
		mv.setViewName("huiyi-list_review");
		return mv;
	}
	
	
	/**
	 *
	 * @author Administrator
	 * @version 1
	 * @see 该方法 对可见的会议（未删除 ）够 审核
	 * @return返回返回影响的数据条数  
	 * @param 要求的参数 会议 id  ---name ==huiyi_id
	 */
	@RequestMapping(value="list_review_Up")
	public ModelAndView getListReview_Up(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String id=request.getParameter("id");
		
		Map paraMap=new HashMap();
		paraMap.put("huiyi_id", id);
		try {
			huiyiSrv.updateHuiYiReview(paraMap);
			String paraPage=request.getParameter("p");
			request.setCharacterEncoding("utf-8");
			String name=request.getParameter("s");
			//String name=new String(request.getParameter("s").getBytes("iso-8859-1"),"utf-8");
			if(name!=null&&!"".equals(name)){
				paraMap.put("lzhongwenName", "%" + name + "%");
				paraMap.put("lyingwenName", "%" + name + "%");
			}
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=huiyiSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = huiyiSrv.getRList(paraMap);
			if(result!=null){
				List list=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("s", name);
				mv.addObject("list", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("huiyi-list_review");
		return mv;
	}
}
