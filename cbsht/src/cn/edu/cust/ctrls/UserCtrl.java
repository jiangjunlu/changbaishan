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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.impl.UserSrv;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="/user")
public class UserCtrl {
	@Resource(name="userSrv")
	private UserSrv userSrv;
	@RequestMapping(value="check",method=RequestMethod.POST)
	public void checkName(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		try {
			String msg=userSrv.check(username);
			Map jsonMap=new HashMap();
			jsonMap.put("msg", msg);
			String jsonStr=JSON.toJSONString(jsonMap);
			request.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="insert")
	public ModelAndView insertUser(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv= new ModelAndView();
		Map paraMap=new HashMap();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		paraMap.put("username", username);
		paraMap.put("password", password);
		paraMap.put("name", name);
		paraMap.put("phone", phone);
		try {
			userSrv.insertUser(paraMap);
			mv.setViewName("login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value="update")
	public ModelAndView updateUser(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		ModelAndView mv= new ModelAndView();
		Map paraMap=new HashMap();
		Map user=(Map)session.getAttribute("user");
		String username=user.get("username").toString();
		String password=user.get("password").toString();
		String name=user.get("name").toString();
		String phone=user.get("phone").toString();
		String newPassword=request.getParameter("passwordc");
		
		try {
			if(password.equals(newPassword)){
				paraMap.put("username", username);
				paraMap.put("password", newPassword);
				paraMap.put("name", name);
				paraMap.put("phone", phone);
				userSrv.updateUser(paraMap);
				session.removeAttribute("user");
				mv.addObject("msg","reload");
		//		mv.setViewName("login");
			}else{
				mv.addObject("msg","原密码不正确");
			}
			mv.setViewName("user-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value="login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		ModelAndView mv= new ModelAndView();
		Map paraMap=new HashMap();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		paraMap.put("username", username);
		paraMap.put("password", password);
		try {
			Map user=userSrv.queryUser(paraMap);
			if(user.get("username")!=null&&!"".equals(user.get("username").toString())){
			//	mv.addObject("user",user);
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(3600);
				mv.setViewName("main");
			}else{
				mv.addObject("msg","用户名与密码不匹配");
				mv.setViewName("login");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value="exit")
	public ModelAndView exit(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		ModelAndView mv= new ModelAndView();
		session.removeAttribute("user");
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping(value="list")
	public ModelAndView getList(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		ModelAndView mv = new ModelAndView();
		Map paraMap=new HashMap();
		try {
			String paraPage=request.getParameter("p");
			String name=request.getParameter("s");
			if(name!=null&&!"".equals(name)){
				paraMap.put("lusername", "%" + name + "%");
			}
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=userSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = userSrv.getList(paraMap);
			if(result!=null){
				List users=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("s", name);
				mv.addObject("users", users);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("user-list");
		return mv;
	}
	
	@RequestMapping(value = "search")
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		String name = request.getParameter("s");
		String paraPage = request.getParameter("p");
		paraMap.put("username", "%" + name + "%");
		try {
			int page=1;
			if(paraPage!=null &&!"".equals(paraPage)){
				page=Integer.parseInt(paraPage);
			}
			int count=userSrv.count(paraMap);
			int pageSize=10;
			int pages=(count-1)/pageSize+1;
			if(page<1){
				page=1;
			}else if(page>pages){
				page=pages;
			}
			int limitBegin=(page-1)*pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = userSrv.getList(paraMap);
			if(result!=null){
				List users=(List)result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("users", users);
			}
			Map meetingMap = userSrv.search(paraMap);// 存放会议查询结果
			if ("success".equals(meetingMap.get("result").toString())) {// 判断会议查询结果是否合法
				System.out.println(meetingMap);
				mv.addObject("users", meetingMap.get("list"));
			} else if ("no_reaul".equals(meetingMap.get("resual").toString())) {
				System.out.println(meetingMap.get("resual").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("s",name);
		mv.setViewName("user-list");
		return mv;

	}
	@RequestMapping(value="del")
	public ModelAndView deleteMeeting(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		int huiyiFlag=0;//闁烩偓鍔嬬花顒勫磻閸パ冪仼闂傚嫨鍊撶槐鎵媼閿燂拷
		int userId=Integer.parseInt(request.getParameter("id"));
		Map paraMap=new HashMap();
		try {
			paraMap.put("userId", userId);
			int rowsAffectedNumber=userSrv.deleteUser(paraMap);
			int page=Integer.parseInt(request.getParameter("p"));
			int count=userSrv.count(paraMap);//鏌ヨ鎬昏褰曟暟
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
			Map result=userSrv.getList(_paraMap);//鏌ヨ鎸囧畾椤典俊鎭�
			String resultType=(String)result.get("result");
			List list=(List)result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("users", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("user-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
