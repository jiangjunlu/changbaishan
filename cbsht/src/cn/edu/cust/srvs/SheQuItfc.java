package cn.edu.cust.srvs;

import java.util.Map;

public interface SheQuItfc {
	/**
	 * insertHuiYi
	 * @param data save update data,by key-value
	 * @return a number,the number sign success or fail.
	 */
	public int insertSheQu(Map<String, Object> data) throws Exception;
	/**getList
	 * @param paraMap save patameters,by key-value
	 * @return list,the item type is map.
	 */
	public Map getList(Map<String, Object> paraMap) throws Exception;
	/**search shequ
	 * @param paraMap save patameters,by key-value
	 * @return list,the item type is map.
	 */
	public Map search(Map<String, Object> paraMap) throws Exception;
	/**delHuiYi
	 * @param paraMap save patameters,by key-value
	 * @return a number.the number sign success or fail.
	 */
	public int delSheQu(Map<String, Object> paraMap) throws Exception;
	/**
	 * findHuiYi
	 * @param paraMap save delete criteria,by key-value
	 * @return a map,but the map only have a meeting information
	 */
	public Map<String, Object> findSheQu(Map<String, Object> paraMap) throws Exception;
	/**
	 * updateHuiYi
	 * @param condition save update criteria,by key-value
	 * @param data save update data,by key-value
	 * @return a list,but the list only have a item,and it's type is map.
	 */
	public int updateSheQu(Map<String, Object> paraMap) throws Exception;
}
