package org.javachina.xmldb.db.portal;

import java.io.IOException;
import java.util.ArrayList;

import org.javachina.xmldb.db.model.DeleteModel;
import org.javachina.xmldb.db.model.InsertModel;
import org.javachina.xmldb.db.parser.SQLParser;
import org.javachina.xmldb.db.xmloperater.XMLOperater;
import org.jdom.JDOMException;

public class Db {
	//执行增删改
	public void execute(String sql) throws JDOMException, IOException{
		String command = sql.toLowerCase().trim().substring(0, sql.indexOf(" "));
		//1.解析sql
		SQLParser parser = new SQLParser();
		//2.完成操作
		XMLOperater operater = new XMLOperater();
		if(command.equals("insert")){
			InsertModel model = parser.parserInsert(sql);
		
			operater.insert(model);
		}else if(command.equals("delete")){
			//1.解析sql
			DeleteModel model = parser.parserDelete(sql);
			//2.完成删除操作
			
			operater.delete(model);
		}else if(command.equals("update")){
			
		}else if(command.equals("select")){
			
		}else{
			System.out.println("您输入的sql语句有误！");
		}
		
		
		
		
	}
	//执行查找
	public ArrayList executeQuery(String sql){
		return null;
	}
}
