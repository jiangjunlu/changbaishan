package cn.edu.cust.srvs;

import java.util.List;
import java.util.Map;

public interface JiGouItfc {
	/**
	 * insertJiGou
	 * @param data save update data,by key-value
	 * @return a number,the number sign success or fail.
	 */
	public int insertJiGou(Map<String, Object> data) throws Exception;
	/**getList
	 * @param paraMap save patameters,by key-value
	 * @return list,the item type is map.
	 */
	public Map getList(Map<String, Object> paraMap) throws Exception;
	/**
	 * search jigou
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public Map search(Map<String, Object> paraMap) throws Exception;
	/**delJiGou
	 * @param paraMap save patameters,by key-value
	 * @return a number.the number sign success or fail.
	 */
	public int delJiGou(Map<String, Object> paraMap) throws Exception;
	/**
	 * findJiGou
	 * @param paraMap save delete criteria,by key-value
	 * @return a map,but the map only have a meeting information
	 */
	public Map<String, Object> findJiGou(Map<String, Object> paraMap) throws Exception;
	/**
	 * updateJiGou
	 * @param condition save update criteria,by key-value
	 * @param data save update data,by key-value
	 * @return a list,but the list only have a item,and it's type is map.
	 */
	public int updateJiGou(Map<String, Object> paraMap) throws Exception;
	
}
