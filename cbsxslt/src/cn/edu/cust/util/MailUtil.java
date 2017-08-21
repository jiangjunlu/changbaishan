package cn.edu.cust.util;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
  
  
/**  
 * �ʼ����͹���ʵ����  
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
     * receiver ����������
     * renyuanName ����������
     * huiyiId  ע�����ı��
     * @return
     */
    public static boolean send(Map<String,String> map) {  
        // ����email  
        HtmlEmail email = new HtmlEmail();
        String url="http://localhost:8080/cbsxslt";
        String _url=map.get("url");
        if(_url!=null && !"".equals(_url)){
        	url=_url;
        }
        try {  
            // ������SMTP���ͷ����������֣�163�����£�"smtp.163.com"  
            email.setHostName(SEND_HOST);  
            // �ַ����뼯������  
            email.setCharset("UTF-8");  
            // �ռ��˵�����  
            email.addTo(map.get("receiver"));
            if("smtp.sina.com".equals(SEND_HOST)){
                email.setFrom(EMAIL_SINA,"����ɽѧ����̳");  // �����˵�����  
                email.setAuthentication(EMAIL_SINA, PASS_SINA); // �����Ҫ��֤��Ϣ�Ļ���������֤���û���-���롣
            }else if("smtp.163.com".equals(SEND_HOST)){
                email.setFrom(EMAIL_163,"����ɽѧ����̳");  // �����˵�����  
                email.setAuthentication(EMAIL_163, PASS_163); //�ֱ�Ϊ���������ʼ��������ϵ�ע�����ƺ�����  
            }
             
            // Ҫ���͵��ʼ�����  
            email.setSubject("����ɽѧ����̳ע��");  
            // Ҫ���͵���Ϣ������ʹ����HtmlEmail���������ʼ�������ʹ��HTML��ǩ  
            email.setHtmlMsg("<b>"+map.get("renyuanName")+"</b>,����!�����ڳ���ɽѧ����̳�ɹ�ע��"+",����"+
            		"<a target='_blank' href='http://"+url+"/participant/yanzheng?e="+map.get("receiver")+
            		"&hyid="+map.get("huiyiId")+"'>"+
            		"��֤</a>");  
            // ����  
            email.setSslSmtpPort("465");
            email.send();  
            return true;
        } catch (EmailException e) {  
            e.printStackTrace();  
            return false;
        }  
    }  
  
}