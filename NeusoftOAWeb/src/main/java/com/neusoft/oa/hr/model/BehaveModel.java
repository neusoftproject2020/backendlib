package com.neusoft.oa.hr.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;
@Alias("Behave")
public class BehaveModel implements Serializable {
	
	private int no=0;
	private String name=null;
	//爱好关联的员工集合
	private List<EmployeeModel> employees=null;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EmployeeModel> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}
	

}
