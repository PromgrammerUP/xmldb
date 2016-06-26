package org.javachina.xmldb.db.xmloperater;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.javachina.xmldb.db.model.DeleteModel;
import org.javachina.xmldb.db.model.InsertModel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XMLOperater {
	private static final String DBPATH="./src/org/javachina/xmldb/db/data/db.xml";
	public void insert(InsertModel model) throws JDOMException, IOException{
		String tableName = model.getTableName();
		Map<String, String> map = model.getPairs();
		
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = builder.build(DBPATH);
		
		Element root = doc.getRootElement();
		
		List<Element> tables = root.getChildren("table");
		
		//定义一个变量如果表不存在则变量为false
		
		for (Element table : tables) {
			String tName = table.getAttributeValue("name");
			if(tableName.equals(tName)){
				Element newElement = new Element(tableName);
				Set<String> allKey = map.keySet();
				for(String key: allKey){
					System.out.println(key);
					Element keyElement = new Element(key);
					keyElement.setText(map.get(key));
					newElement.addContent(keyElement);
					
				}
				table.addContent(newElement);
				
			}
		}
		XMLOutputter out = new XMLOutputter();
		out.output(doc,new FileOutputStream(DBPATH));
		System.out.println("文件插入操作成功");
	}
	public void delete(DeleteModel model) throws JDOMException, IOException{
		String tableName = model.getTableName();
		Map<String,String> map = model.getParams();
		
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = builder.build(DBPATH);
		
		//取得根元素
		Element root = doc.getRootElement();
		
		//取得所有表
		List<Element> tables = root.getChildren("table");
		
		//得到每一个表
		for(Element table:tables){
			//取得XML文件中的表名
			String tableNameFromXML = table.getAttributeValue("name");
			if(tableName.equals(tableNameFromXML)){
				List<Element> items = table.getChildren(tableName);
				//每一个item相当于一个teacher元素
				System.out.println(items.size());
				for (int i=0;i<items.size();i++) {
					Set<String> keys = map.keySet();
					//定义布尔值判断条件是否成立
					boolean isOk = true;
					for (String key : keys) {
						String valueFromXML = items.get(i).getChildText(key);
						System.out.println(valueFromXML+"---"+map.get(key));
						if(!valueFromXML.equals(map.get(key))){
							isOk = false;
						}
					}
					if(isOk){
						table.removeContent(i);
					}
				}
			}
			
		}
		XMLOutputter out = new XMLOutputter();
		out.output(doc,new FileOutputStream(DBPATH));
		System.out.println("文件删除操作成功");
	}
}
