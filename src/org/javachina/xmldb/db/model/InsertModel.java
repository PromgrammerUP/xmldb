package org.javachina.xmldb.db.model;

import java.util.Map;

public class InsertModel {
	private String tableName;
	private Map<String, String> pairs;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, String> getPairs() {
		return pairs;
	}
	public void setPairs(Map<String, String> pairs) {
		this.pairs = pairs;
	}
	
}
