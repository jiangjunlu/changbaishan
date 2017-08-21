package cn.edu.cust.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
			 // 获得在下面代码中要用的request,response,session对象
			HttpServletRequest servletRequest = (HttpServletRequest) request;
		    HttpServletResponse servletResponse = (HttpServletResponse) response;
		    HttpSession session = servletRequest.getSession();
		    // 获得用户请求的URI
		    String path = servletRequest.getRequestURI();
		    String page=path.substring(path.lastIndexOf("/"));
		    if(path.lastIndexOf(".")>=0){
			    String cssOrJs=path.substring(path.lastIndexOf("."));
			    if(cssOrJs.equals(".js")||cssOrJs.equals(".css")||cssOrJs.equals(".png")||cssOrJs.equals(".jpg")||cssOrJs.equals(".gif")){  // css js png jpg gif无需过滤
		        	chain.doFilter(request, response);
		        	return;
		        }
		    }
		    if(page.equals("/login.jsp")||page.equals("/login")||page.equals("/register.jsp")||path.endsWith("/user/insert")||path.endsWith("/user/check")){  // 登陆页面无需过滤
	        	chain.doFilter(request, response);
	        	return;
	        }
		    // 从session里取员工工号信息
			Map user = (Map) session.getAttribute("user");
	//		String url=request.getServerName()+":"+request.getServerPort()+"/cbsht/login.jsp";    
		
		        // 判断如果没有取到员工信息,就跳转到登陆页面
			if (user == null) {
		            // 跳转到登陆页面
				servletResponse.sendRedirect("login.jsp");
				
			} else {
			          // 已经登陆,继续此次请求
			    chain.doFilter(request, response);
			}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
