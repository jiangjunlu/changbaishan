package cn.edu.cust.ctrls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.ArticleSrv;

@Controller
@RequestMapping("/lunwen")
public class ArticleCtrls {

	@Resource(name = "/articleSrv")
	private ArticleSrv articleSrv;

	// 加载index页面信息
	@RequestMapping("detail")
	public ModelAndView getDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		paraMap.put("lunwenId", request.getParameter("lwid"));
		try {
			Map map = articleSrv.queryArticle(paraMap);
			List articles=(List) map.get("articleList");
			Map article=null;
			if(articles!=null && articles.size()>0){
				article=(Map)articles.get(0);
			}
			mv.addObject("lunwen", article);
			mv.setViewName("html/lunwen");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 通过社区id加载论文信息
	@RequestMapping("all")
	public ModelAndView getNames(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		paraMap.put("shequId", request.getParameter("sqid"));
		try {
			Map map = articleSrv.queryArticle(paraMap);
			mv.addObject("lunwen", (List) map.get("articleList"));
			mv.setViewName("html/shequ_lunwen_name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
