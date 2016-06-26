package org.javachina.xmldb.db.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.javachina.xmldb.db.model.DeleteModel;
import org.javachina.xmldb.db.model.InsertModel;
import org.javachina.xmldb.db.model.SelectModel;
import org.javachina.xmldb.db.model.UpdateModel;

public class SQLParser {
	/*
	 * 解析查找sql
	 */
	public SelectModel parserSelect(String sql){
		return null;
	}
	/*
	 * 解析更新sql
	 */
	public UpdateModel parserUpdate(String sql){
		return null;
	}
	/*
	 * 解析插入sql
	 */
	public InsertModel parserInsert(String sql){
		//insert into teacher (id,name,age) values(1001,张三,20)
		InsertModel model = new InsertModel();
		//规范sql ，小写、去空格
		sql = sql.toLowerCase().trim();
		//得到表名
		String tableName = sql.substring(sql.indexOf("into")+4, sql.indexOf("(")).trim();
		
		model.setTableName(tableName);
		String params = sql.substring(sql.indexOf("(")+1,sql.indexOf(")")).trim();
		String values = sql.substring(sql.lastIndexOf("(")+1, sql.lastIndexOf(")")).trim();
		
		String[] paramArr = params.split(",");
		String[] valueArr = values.split(",");
		Map<String, String> map = new HashMap<String,String>();
		for(int i=0;i<paramArr.length;i++){
			map.put(paramArr[i].trim(), valueArr[i].trim());
		}
		model.setPairs(map);
		return model;
	}
	/*
	 * 解析删除sql
	 */
	public DeleteModel parserDelete(String sql){
		//delete tableName where id = 1001 and name = 张三
		DeleteModel model = new DeleteModel();
		sql = sql.toLowerCase().trim();
		
		//定义一个判断where是否存在的变量
		boolean isWhereExist = false;
		String[] factors = sql.split(" ");
		for(String factor :factors){
			if(factor.equals("where")){
				isWhereExist = true;
				break;
			}
		}
		String tableName =null;
		Map<String, String> map = new HashMap<String, String>();
		if(isWhereExist){
			
			 tableName = sql.substring(sql.indexOf("delete")+6, sql.indexOf("where")).trim();
			
			String params = sql.substring(sql.indexOf("where")+5).trim();
			String[] pars = params.split("and");
			
			for(String parm: pars){
				String[] pair = parm.split("=");
				map.put(pair[0].trim(), pair[1].trim());
			}
		}else{
			 tableName = sql.substring(6).trim();
			 
		}
		model.setParams(map);
		model.setTableName(tableName);
		return model;
	}
	public static void main(String[] args) {
		DeleteModel model = new SQLParser().parserDelete("delete person where id=1001 and name =张三");
		String tName = model.getTableName();
		System.out.println(tName);
		Map<String, String> m = model.getParams();
		Set<String> keys = m.keySet();
		for (String key : keys) {
			System.out.println(key+":"+m.get(key));
		}
		
	}
}
