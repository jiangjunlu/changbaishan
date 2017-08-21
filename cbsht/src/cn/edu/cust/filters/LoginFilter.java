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
			 // ��������������Ҫ�õ�request,response,session����
			HttpServletRequest servletRequest = (HttpServletRequest) request;
		    HttpServletResponse servletResponse = (HttpServletResponse) response;
		    HttpSession session = servletRequest.getSession();
		    // ����û������URI
		    String path = servletRequest.getRequestURI();
		    String page=path.substring(path.lastIndexOf("/"));
		    if(path.lastIndexOf(".")>=0){
			    String cssOrJs=path.substring(path.lastIndexOf("."));
			    if(cssOrJs.equals(".js")||cssOrJs.equals(".css")||cssOrJs.equals(".png")||cssOrJs.equals(".jpg")||cssOrJs.equals(".gif")){  // css js png jpg gif�������
		        	chain.doFilter(request, response);
		        	return;
		        }
		    }
		    if(page.equals("/login.jsp")||page.equals("/login")||page.equals("/register.jsp")||path.endsWith("/user/insert")||path.endsWith("/user/check")){  // ��½ҳ���������
	        	chain.doFilter(request, response);
	        	return;
	        }
		    // ��session��ȡԱ��������Ϣ
			Map user = (Map) session.getAttribute("user");
	//		String url=request.getServerName()+":"+request.getServerPort()+"/cbsht/login.jsp";    
		
		        // �ж����û��ȡ��Ա����Ϣ,����ת����½ҳ��
			if (user == null) {
		            // ��ת����½ҳ��
				servletResponse.sendRedirect("login.jsp");
				
			} else {
			          // �Ѿ���½,�����˴�����
			    chain.doFilter(request, response);
			}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
