package cn.edu.cust.srvs;

import java.util.Map;

public interface JiaBinItfc {
	/**
	 * insertJiaBin
	 * @param data save update data,by key-value
	 * @return a number,the number sign success or fail.
	 */
	public int insertJiaBin(Map<String, Object> data) throws Exception;
	/**getList
	 * @param paraMap save patameters,by key-value
	 * @return list,the item type is map.
	 */
	public Map getList(Map<String, Object> paraMap) throws Exception;
	/**
	 * reach jiabin
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public Map search(Map<String, Object> paraMap) throws Exception;
	/**delJiaBin
	 * @param paraMap save patameters,by key-value
	 * @return a number.the number sign success or fail.
	 */
	public int delJiaBin(Map<String, Object> paraMap) throws Exception;
	/**
	 * findJiaBin
	 * @param paraMap save delete criteria,by key-value
	 * @return a map,but the map only have a meeting information
	 */
	public Map<String, Object> findJiaBin(Map<String, Object> paraMap) throws Exception;
	/**
	 * updateJiaBin
	 * @param condition save update criteria,by key-value
	 * @param data save update data,by key-value
	 * @return a list,but the list only have a item,and it's type is map.
	 */
	public int updateJiaBin(Map<String, Object> paraMap) throws Exception;
}
