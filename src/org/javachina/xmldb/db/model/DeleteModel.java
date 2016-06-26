package org.javachina.xmldb.db.model;

import java.util.Map;

public class DeleteModel {
	private String tableName;
	private Map<String, String> params= null;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
