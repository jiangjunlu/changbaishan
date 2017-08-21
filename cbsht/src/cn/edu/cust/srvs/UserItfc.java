package cn.edu.cust.srvs;

import java.util.Map;

public interface UserItfc {
	/**
	 * Check username exists
	 */
	public abstract String check(String username) throws Exception;
	
	/**
	 * insert a username
	 */
	public abstract Map insertUser(Map paraMap) throws Exception;
	
	/**
	 * update user information
	 */
	public abstract Map updateUser(Map paraMap) throws Exception;
	
	/**
	 * delete user which is requied
	 */
	public abstract int deleteUser(Map paraMap) throws Exception;
	
	/**
	 * query users 
	 */
	public abstract Map queryUser(Map paraMap) throws Exception;
	
	/**
	 * query user 
	 */
	public abstract Map getList(Map paraMap) throws Exception;
	public Map search(Map<String, Object> paraMap) throws Exception;
	
}
