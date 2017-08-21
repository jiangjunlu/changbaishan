package cn.edu.cust.srvs;

import java.util.Map;

public interface LunWenItfc {
	/**
	 * insertLunWen
	 * @param data save update data,by key-value
	 * @return a number,the number sign success or fail.
	 */
	public int insertLunWen(Map<String, Object> data) throws Exception;
	/**getList
	 * @param paraMap save patameters,by key-value
	 * @return list,the item type is map.
	 */
	public Map getList(Map<String, Object> paraMap) throws Exception;
	/**search lunwen
	 * @param paraMap save patameters,by key-value
	 * @return list,the item type is map.
	 */
	public Map search(Map<String, Object> paraMap) throws Exception;
	/**delLunWen
	 * @param paraMap save patameters,by key-value
	 * @return a number.the number sign success or fail.
	 */
	public int delLunWen(Map<String, Object> paraMap) throws Exception;
	/**
	 * findLunWen
	 * @param paraMap save delete criteria,by key-value
	 * @return a map,but the map only have a meeting information
	 */
	public Map<String, Object> findLunWen(Map<String, Object> paraMap) throws Exception;
	/**
	 * updateLunWen
	 * @param condition save update criteria,by key-value
	 * @param data save update data,by key-value
	 * @return a list,but the list only have a item,and it's type is map.
	 */
	public int updateLunWen(Map<String, Object> paraMap) throws Exception;
}
