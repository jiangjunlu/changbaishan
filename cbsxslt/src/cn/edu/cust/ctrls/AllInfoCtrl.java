package cn.edu.cust.ctrls;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cust.srvs.AgencySrv;
import cn.edu.cust.srvs.ArticleSrv;
import cn.edu.cust.srvs.GuestSrv;
import cn.edu.cust.srvs.MeetingSrv;
import cn.edu.cust.srvs.NewsSrv;
import cn.edu.cust.srvs.ParticipantSrv;
import cn.edu.cust.util.Group;
import cn.edu.cust.util.Info;

@Controller
@RequestMapping("/all")
public class AllInfoCtrl {
	@Resource(name = "/meetingSrv")
	private MeetingSrv meetingSrv;
	@Resource(name = "/guestSrv")
	private GuestSrv guestSrv;
	@Resource(name = "/participantSrv")
	private ParticipantSrv participantSrv;
	@Resource(name = "/agencySrv")
	private AgencySrv agencySrv;
	@Resource(name = "/newsSrv")
	private NewsSrv newsSrv;
	@Resource(name = "/articleSrv")
	private ArticleSrv articleSrv;

	
	// ����indexҳ����Ϣ
	@RequestMapping("index")
	public ModelAndView readIndexInfo(HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// ��Ų�ѯ����
		paraMap.put("huiyiZhuangtai", 0);
		paraMap.put("jigouJuese", 1);
		try {
			paraMap.put("index", "index");// ����һ����־ �� �ڻ����ѯʱ��ֻ��û���Ļ���
			Map meetingMap = meetingSrv.getIndex();// ��Ż����ѯ���
			Map guestMap = guestSrv.queryGuest(paraMap);// ��żα���ѯ���
			Map agencyMap = agencySrv.queryAgencySrv(paraMap);// ������Ϣ
			// Map newsMap = newsSrv.queryNews(paraMap);//��̳���� (��ʱ)
			if (Info.SUCCESS == (int) meetingMap.get("meetingResultInfo")) {// �жϻ����ѯ����Ƿ�Ϸ�
				System.out.println(meetingMap);
				mv.addObject("meetingInfo", meetingMap.get("meetingList"));
			} else if ((int) meetingMap.get("meetingResultInfo") == Info.NO_RESULT) {
				System.out.println((int) meetingMap.get("meetingResultInfo"));
			}
			if (Info.SUCCESS == (int) guestMap.get("guestResultInfo")) {// �жϼα���ѯ����Ƿ�Ϸ�
				System.out.println(meetingMap);
				List guestInfo=Group.splitGroup((List)guestMap.get("guestList"), 4);
				mv.addObject("guestInfo", guestInfo);
			} else if ((int) guestMap.get("guestResultInfo") == Info.NO_RESULT) {
				System.out.println((int) guestMap.get("guestResultInfo"));
			}
			if (Info.SUCCESS == (int) agencyMap.get("agencyResultInfo")) {// �жϻ�����ѯ����Ƿ�Ϸ�
				mv.addObject("agencyInfo", agencyMap.get("agencyList"));
			} else if ((int) agencyMap.get("agencyResultInfo") == Info.NO_RESULT) {
				System.out.println((int) agencyMap.get("agencyResultInfo"));
			}
			List particle=Group.splitGroup(queryPArticle(paraMap),3);
			mv.addObject("particle", particle);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		mv.setViewName("html/index");
		return mv;
	}

	@RequestMapping("moreMeeting")
	// ����indexҳ����Ϣ
	public ModelAndView moreMeeting() {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// ��Ų�ѯ����
		try {
			Map meetingMap = meetingSrv.queryMeeting(paraMap);// ��Ż����ѯ���
			if (Info.SUCCESS == (int) meetingMap.get("meetingResultInfo")) {// �жϻ����ѯ����Ƿ�Ϸ�
		//		System.out.println(meetingMap);
				mv.addObject("meetingInfo", meetingMap.get("meetingList"));
			} else if ((int) meetingMap.get("meetingResultInfo") == Info.NO_RESULT) {
				System.out.println((int) meetingMap.get("meetingResultInfo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.setViewName("html/huiyi");
		return mv;
	}

	/**
	 * ����������� ������ϸ��Ϣҳ��
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("meetingDetail")
	// ���ػ�����ϸ��Ϣ
	public ModelAndView getMeetingDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paraMap = new HashMap();// ��Ų�ѯ����
		if (request.getParameter("id") != null
				&& !"".equals(request.getParameter("id"))) {
			paraMap.put("huiyiId", request.getParameter("id"));
		}
		try {
			Map meetingMap = meetingSrv.queryMeeting(paraMap);
			Map participantMap = participantSrv.queryParticipant(paraMap);
			if (Info.SUCCESS == (int) meetingMap.get("meetingResultInfo")) {// �����ѯ����Ƿ�Ϸ�
				Map detail = (Map) ((List) meetingMap.get("meetingList"))
						.get(0);
				// ======================����ؼ���ƴ��=========================================
				// " "Ϊ�ؼ��ʷָ��� ���ַ������� �滻Ϊ ��������
				if (detail.get("huiyi_guanjianci") != null) {
					String[] huiyi_guanjianci = detail.get("huiyi_guanjianci")
							.toString().split(" ");
					detail.remove("huiyi_guanjianci");//
					detail.put("huiyi_guanjianci", huiyi_guanjianci);
				}
				// ======================�������췽=========================================
				if (detail.get("huiyi_zhubanfang") != null) {
					String[] zhubanfang_id = detail.get("huiyi_zhubanfang")
							.toString().split(" ");
					List huiyi_zhubanfang = queryAgency(zhubanfang_id);
					detail.remove("huiyi_zhubanfang");//
					detail.put("huiyi_zhubanfang", huiyi_zhubanfang);
				}
				// ======================����Э�췽=========================================
				if (detail.get("huiyi_xiebanfang") != null) {
					String[] xiebanfang_id = detail.get("huiyi_xiebanfang")
							.toString().split(" ");
					List huiyi_xiebanfang = queryAgency(xiebanfang_id);
					detail.remove("huiyi_xiebanfang");//
					detail.put("huiyi_xiebanfang", huiyi_xiebanfang);
				}
				// ======================������/�ΰ췽=========================================
				if (detail.get("huiyi_laibanfang") != null) {
					String[] laibanfang_id = detail.get("huiyi_laibanfang")
							.toString().split(" ");
					List huiyi_laibanfang = queryAgency(laibanfang_id);
					detail.remove("huiyi_laibanfang");//
					detail.put("huiyi_laibanfang", huiyi_laibanfang);
				}
				mv.addObject("meetingDetail", detail);
			} else if ((int) meetingMap.get("meetingResultInfo") == Info.NO_RESULT) {
				System.out.println((int) meetingMap.get("meetingResultInfo"));
			}
			mv.setViewName("html/xiangqing");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("download")
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String path = request.getParameter("p");// path ·��
			// ���ر����ļ�
			String fileName = "����" + path.substring(path.indexOf("."));
			/*
			 * if(number.length()<=1){ fileName
			 * =("����"+number+"."+type).toString(); // �ļ���Ĭ�ϱ����� }else{ fileName
			 * =(number+"."+type).toString(); // �ļ���Ĭ�ϱ����� }
			 */
			// ��������
			InputStream inStream = new FileInputStream(path);// �ļ��Ĵ��·��
			// ��������ĸ�ʽ
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
			// ѭ��ȡ�����е�����
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	@RequestMapping("/queryPa")
	public ModelAndView queryPa(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		Map paraMap=new HashMap();
		List list=queryPArticle(paraMap);
		String fun=request.getParameter("fun");
		if(fun!=null){
			if("lw".equals(fun)){
				String lwid=request.getParameter("lwid");
				mv.addObject("fun",fun);
				mv.addObject("lwid",lwid);
			}else if("sq".equals(fun)){
				String sqid=request.getParameter("sqid");
				mv.addObject("fun",fun);
				mv.addObject("sqid",sqid);
			}
		}
		mv.addObject("particles",list);
		mv.setViewName("html/shequ");
		return mv;
	}
	
	/**
	 * �߼�������������id��Ϊ�գ��϶��ܲ鵽�û�������Ϊ���id�ǻ�������ģ������ʱ���Ѿ�ȷ����id��ȷ
	 * Ϊ�˷�ֹ������������ó��ˡ�������������⣬������null
	 * 
	 * @param String
	 *            [] id
	 * @return ֻҪû�����һ����null
	 */
	public List queryAgency(String[] id) {
		Map paraMap = new HashMap();
		List reList = new ArrayList();
		boolean isNull = true;
		for (int i = 0; i < id.length; i++) {
			paraMap.remove("jigouId");
			paraMap.put("jigouId", id[i]);
			try {
				Map agencyMap = agencySrv.queryAgencySrv(paraMap);
				if (Info.SUCCESS == Integer.parseInt(agencyMap.get(
						"agencyResultInfo").toString())) {
					Map detail = (Map) ((List) agencyMap.get("agencyList"))
							.get(0);
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

	/**
	 * ��ѯĳ�����µ�����
	 */
	public List queryArticle(int shequId) {
		Map paraMap = new HashMap();
		paraMap.put("shequId", shequId);
		List reList = null;
		try {
			Map map = articleSrv.queryArticle(paraMap);
			reList = (List) map.get("articleList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reList;
	}
	/**
	 * ��ѯ����-���� ���ݿ��Ӧ ���ı�����������
	 */
	public List queryPArticle(Map paraMap) {
		try {
			Map partMap = articleSrv.queryPart(paraMap);
			if (Info.SUCCESS == (int) partMap.get("partResultInfo")) {// ������ѹ�����
				List parts = (List) partMap.get("partList");
				List particle = new ArrayList();
				for (int i = 0; i < parts.size(); i++) {
					Map m = new HashMap();
					int partId = Integer.parseInt(((Map) parts.get(i)).get(
							"shequ_id").toString());
					List articles = queryArticle(partId);
					m.put("part", parts.get(i));
					m.put("articles", articles);
					particle.add(m);
				}
				return particle;
			} else if ((int) partMap.get("partResultInfo") == Info.NO_RESULT) {
				System.out.println("partResultInfo"
						+ (int) partMap.get("partResultInfo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
