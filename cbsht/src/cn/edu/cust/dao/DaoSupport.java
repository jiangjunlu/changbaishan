package cn.edu.cust.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("daoSupport")
public class DaoSupport  {
	//Resource���ã���idΪname=""���ֵ�Ķ���ע�뵽�ö�����
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * �������
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int insert(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}
	
	
	/**
	 * �޸Ķ���
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int update(String str, Object obj) throws Exception {
		return sqlSessionTemplate.update(str, obj);
	}

	
	/**
	 * ɾ������ 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int delete(String str, Object obj) throws Exception {
		return sqlSessionTemplate.delete(str, obj);
	}
	 
	/**
	 * ���Ҷ���
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object queryOne(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}

	/**
	 * ���Ҷ���
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List queryList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}
	
}


