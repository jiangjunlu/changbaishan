package cn.edu.cust.ctrls;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.impl.JiaBinSrv;

@Controller
@RequestMapping(value = "/jiabin")
public class JiaBinCtrl {
	@Resource(name = "jiabinSrv")
	private JiaBinSrv jiabinSrv;

	@RequestMapping(value = "search")
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// 存放查询条件
		String name = request.getParameter("s");
		String paraPage = request.getParameter("p");
		paraMap.put("jiabinName", "%" + name + "%");
		try {
			int page = 1;
			if (paraPage != null && !"".equals(paraPage)) {
				page = Integer.parseInt(paraPage);
			}
			int count = jiabinSrv.count(paraMap);
			int pageSize = 10;
			int pages = (count - 1) / pageSize + 1;
			if (page < 1) {
				page = 1;
			} else if (page > pages) {
				page = pages;
			}
			int limitBegin = (page - 1) * pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = jiabinSrv.getList(paraMap);
			if (result != null) {
				List list = (List) result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("list", list);
			}
			Map meetingMap = jiabinSrv.search(paraMap);// 存放会议查询结果
			if ("success".equals(meetingMap.get("result").toString())) {// 判断会议查询结果是否合法
				System.out.println(meetingMap);
				mv.addObject("list", meetingMap.get("list"));
			} else if ("no_reaul".equals(meetingMap.get("resual").toString())) {
				System.out.println(meetingMap.get("resual").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("s", name);
		mv.setViewName("jiabin-list");
		return mv;

	}

	/**
	 * 
	 * @return all meeting information,and include overdue meetings
	 */
	@RequestMapping(value = "list")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();
		try {
			String paraPage = request.getParameter("p");
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("s");
			// String name=new
			// String(request.getParameter("s").getBytes("iso-8859-1"),"utf-8");
			if (name != null && !"".equals(name)) {
				paraMap.put("ljibinName", "%" + name + "%");
			}
			int page = 1;
			if (paraPage != null && !"".equals(paraPage)) {
				page = Integer.parseInt(paraPage);
			}
			int count = jiabinSrv.count(paraMap);
			int pageSize = 10;
			int pages = (count - 1) / pageSize + 1;
			if (page < 1) {
				page = 1;
			} else if (page > pages) {
				page = pages;
			}
			int limitBegin = (page - 1) * pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = jiabinSrv.getList(paraMap);
			if (result != null) {
				List list = (List) result.get("list");
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.addObject("s", name);
				mv.addObject("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("jiabin-list");
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
		paraMap.put("jiabinId", id);
		try {
			Map detail = jiabinSrv.findJiaBin(paraMap);
			mv.addObject("jb", detail);
			mv.addObject("p", page);
			mv.addObject("tableType", "update");
			mv.setViewName("jiabin-table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "del")
	public ModelAndView deleteJiaBin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int jiabinFlag = 0;// 用于假删除会议
		int jiabinId = Integer.parseInt(request.getParameter("id"));
		Map paraMap = new HashMap();
		try {
			paraMap.put("jiabinId", jiabinId);
			paraMap.put("jiabinFlag", jiabinFlag);
			int rowsAffectedNumber = jiabinSrv.delJiaBin(paraMap);
			int page = Integer.parseInt(request.getParameter("p"));
			int count = jiabinSrv.count(paraMap);// 查询总记录数
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
			Map result = jiabinSrv.getList(_paraMap);// 查询指定页信息
			String resultType = (String) result.get("result");
			List list = (List) result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("jiabin-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
//
//	@RequestMapping( value = "insert",method = { RequestMethod.POST })  
//	public ModelAndView upload(@RequestParam(value = "zp", required = false) MultipartFile file,HttpServletRequest request) 
//	{  
//	  System.out.println("成功进入控制层");//这里输出的文字  是为了可以在后台看到是否进入了这个方法  方便查错  
//	  String path = "E:/";//上传的目录  简单点 这里是E盘根目录  根据自己的需求改  想把上传的文件放在哪里  路径就写到哪里  
//	  String fileName = file.getOriginalFilename();//获取上传的文件名字 日后可以根据文件名做相应的需改 例如自定义文件名 分析文件后缀名等等  
//	  System.out.println(path);//后台输出你要存放文件的路径名  方便查错  
//	  File targetFile = new File(path, fileName);  //新建文件  
//	  if (!targetFile.exists()) {    //判断文件的路径是否存在  
//	    targetFile.mkdirs();  //如果文件不存在  在目录中创建文件夹   这里要注意mkdir()和mkdirs()的区别  
//	  }  
//	  // 保存  
//	  try {  
//	    file.transferTo(targetFile); //传送  失败就抛异常  
//	  } catch (Exception e) {  
//	    e.printStackTrace();  
//	  }  
//	  return new ModelAndView("result");   //看情况返回参数就OK了  
//	}

	@RequestMapping(value = "insert",method = { RequestMethod.POST })
	public ModelAndView insertJiaBin(@RequestParam(value = "zp", required = false) MultipartFile file,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();
		
		String jbzp=UUID.randomUUID().toString().replace("-", "");
		String _path = this.getClass().getResource("/").getFile().toString();//上传的目录  简单点 这里是E盘根目录  根据自己的需求改  想把上传的文件放在哪里  路径就写到哪里  
		String path=_path.substring(0, _path.indexOf("webapps"));
		path+="webapps/cbsxslt/jiabin_img";
		String fileName = file.getOriginalFilename();//获取上传的文件名字 日后可以根据文件名做相应的需改 例如自定义文件名 分析文件后缀名等等  
		if(fileName!=null&&!"".equals(fileName)){ 
			String newPath=jbzp+fileName.substring(fileName.lastIndexOf("."));
			File targetFile = new File(path,jbzp+fileName.substring(fileName.lastIndexOf(".")) );  //新建文件  
			if (!targetFile.exists()) {    //判断文件的路径是否存在  
				targetFile.mkdirs();  //如果文件不存在  在目录中创建文件夹   这里要注意mkdir()和mkdirs()的区别  
			} 
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} //传送 图片失败就抛异常  
			paraMap.put("jiabinZhaopian", newPath); 
		}
		/********* 获取会议所有参数 *****************/
		String jiabinName = request.getParameter("jn");
		String jiabinLingyu = request.getParameter("ly");
		String jiabinJigou = request.getParameter("jg");
		String jiabinJianjie = request.getParameter("jj");
		String jiabinCanyuHuodong = request.getParameter("cjhd");
		String jiabinZhicheng = request.getParameter("zc");
		String jiabinZhiwu = request.getParameter("zw");
		paraMap.put("jiabinName", jiabinName);
		paraMap.put("jiabinLingyu", jiabinLingyu);
		paraMap.put("jiabinJigou", jiabinJigou);
		paraMap.put("jiabinJianjie", jiabinJianjie);
		paraMap.put("jiabinCanyuHuodong", jiabinCanyuHuodong);
		paraMap.put("jiabinZhicheng", jiabinZhicheng);
		paraMap.put("jiabinZhiwu", jiabinZhiwu);
		/****************************************/
		try {
			 
			int rowsAffectedNumber = jiabinSrv.insertJiaBin(paraMap);
			int page = 1;
			int count = jiabinSrv.count(paraMap);// 查询总记录数
			int pageSize = 10;
			int pages = (count - 1) / pageSize + 1;
			int limitBegin = (page - 1) * pageSize;
			paraMap.put("limitBegin", limitBegin);
			Map result = jiabinSrv.getList(paraMap);// 查询指定页信息
			String resultType = (String) result.get("result");
			List list = (List) result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("jiabin-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "update",method = { RequestMethod.POST })
	public ModelAndView updateJiaBin(@RequestParam(value = "zp", required = false) MultipartFile file,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();
		String jbzp=UUID.randomUUID().toString().replace("-", "");
		String _path = this.getClass().getResource("/").getFile().toString();//上传的目录  简单点 这里是E盘根目录  根据自己的需求改  想把上传的文件放在哪里  路径就写到哪里  
		String path=_path.substring(0, _path.indexOf("webapps"));
		path+="webapps/cbsxslt/jiabin_img";
		String fileName = file.getOriginalFilename();//获取上传的文件名字 日后可以根据文件名做相应的需改 例如自定义文件名 分析文件后缀名等等  
		if(fileName!=null&&!"".equals(fileName)){
			String newPath=jbzp+fileName.substring(fileName.lastIndexOf("."));
			File targetFile = new File(path,jbzp+fileName.substring(fileName.lastIndexOf(".")) );  //新建文件  
			if (!targetFile.exists()) {    //判断文件的路径是否存在  
				targetFile.mkdirs();  //如果文件不存在  在目录中创建文件夹   这里要注意mkdir()和mkdirs()的区别  
			}  
			try {
				file.transferTo(targetFile); //传送 图片失败就抛异常  
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			paraMap.put("jiabinZhaopian", newPath);
		}
		int jiabinId = Integer.parseInt(request.getParameter("id"));
		String jiabinName = request.getParameter("jn");
		String jiabinLingyu = request.getParameter("ly");
		String jiabinJigou = request.getParameter("jg");
		String jiabinJianjie = request.getParameter("jj");
		String jiabinCanyuHuodong = request.getParameter("cjhd");
		String jiabinZhicheng = request.getParameter("zc");
		String jiabinZhiwu = request.getParameter("zw");
//		String jiabinZhaopian = request.getParameter("zp");
		
		try {
			
			paraMap.put("jiabinId", jiabinId);
			paraMap.put("jiabinName", jiabinName);
			paraMap.put("jiabinLingyu", jiabinLingyu);
			paraMap.put("jiabinJigou", jiabinJigou);
			paraMap.put("jiabinJianjie", jiabinJianjie);
			paraMap.put("jiabinCanyuHuodong", jiabinCanyuHuodong);
			paraMap.put("jiabinZhicheng", jiabinZhicheng);
			paraMap.put("jiabinZhiwu", jiabinZhiwu);
			int rowsAffectedNumber = jiabinSrv.updateJiaBin(paraMap);
			int page = Integer.parseInt(request.getParameter("p"));
			int count = jiabinSrv.count(paraMap);// 查询总记录数
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
			Map result = jiabinSrv.getList(_paraMap);// 查询指定页信息
			String resultType = (String) result.get("result");
			List list = (List) result.get("list");
			mv.addObject("resultType", resultType);
			mv.addObject("list", list);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("jiabin-list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
