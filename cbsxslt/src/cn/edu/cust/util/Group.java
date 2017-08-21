package cn.edu.cust.util;

import java.util.ArrayList;
import java.util.List;

public class Group {
	public static List splitGroup(List list,int groupSize){
		List reList=new ArrayList();
		for(int i=0;i<list.size();i=i+groupSize){
			List l=new ArrayList();
			for(int j=0;j<groupSize&&(i+j)<list.size();j++){
				l.add(list.get(i+j));
			}
			reList.add(l);
		}
		return reList;
	}
}
