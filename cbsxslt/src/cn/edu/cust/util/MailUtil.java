package cn.edu.cust.util;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
  
  
/**  
 * 邮件发送工具实现类  
 *   
 * @author 
 * @create  
 */  
public class MailUtil {  
  
    protected final Logger logger = Logger.getLogger(getClass());  
  
    public static String SEND_HOST="smtp.sina.com";
    private static final String EMAIL_SINA="myfeigg@sina.com";
    private static final String PASS_SINA="qweqwe";
    private static final String EMAIL_163="15754314639@163.com";
    private static final String PASS_163="qwe123";
    
    /**
     * 
     * @param map
     * receiver 收信人邮箱
     * renyuanName 收信人姓名
     * huiyiId  注册会议的编号
     * @return
     */
    public static boolean send(Map<String,String> map) {  
        // 发送email  
        HtmlEmail email = new HtmlEmail();
        String url="http://localhost:8080/cbsxslt";
        String _url=map.get("url");
        if(_url!=null && !"".equals(_url)){
        	url=_url;
        }
        try {  
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
            email.setHostName(SEND_HOST);  
            // 字符编码集的设置  
            email.setCharset("UTF-8");  
            // 收件人的邮箱  
            email.addTo(map.get("receiver"));
            if("smtp.sina.com".equals(SEND_HOST)){
                email.setFrom(EMAIL_SINA,"长白山学术论坛");  // 发送人的邮箱  
                email.setAuthentication(EMAIL_SINA, PASS_SINA); // 如果需要认证信息的话，设置认证：用户名-密码。
            }else if("smtp.163.com".equals(SEND_HOST)){
                email.setFrom(EMAIL_163,"长白山学术论坛");  // 发送人的邮箱  
                email.setAuthentication(EMAIL_163, PASS_163); //分别为发件人在邮件服务器上的注册名称和密码  
            }
             
            // 要发送的邮件主题  
            email.setSubject("长白山学术论坛注册");  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setHtmlMsg("<b>"+map.get("renyuanName")+"</b>,您好!您已在长白山学术论坛成功注册"+",请点击"+
            		"<a target='_blank' href='http://"+url+"/participant/yanzheng?e="+map.get("receiver")+
            		"&hyid="+map.get("huiyiId")+"'>"+
            		"验证</a>");  
            // 发送  
            email.setSslSmtpPort("465");
            email.send();  
            return true;
        } catch (EmailException e) {  
            e.printStackTrace();  
            return false;
        }  
    }  
  
}