package com.neusoft.oa.admin.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
//管理员Model类
@Alias("User")
public class UserModel implements Serializable {
	private String id=null;
	private String password=null;
	private String name=null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
