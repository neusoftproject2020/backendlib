package com.neusoft.oa.hr.service;

import java.util.List;

import com.neusoft.oa.hr.model.DepartmentModel;

//部门的业务接口
public interface IDepartmentService {
	
	//增加部门
	public int add(DepartmentModel dm) throws Exception;
	//修改部门
	public void modify(DepartmentModel dm) throws Exception;
	//删除部门
	public void delete(DepartmentModel dm) throws Exception;
	//取得所有部门列表
	public List<DepartmentModel> getListByAll() throws Exception;
	//取得所有部门列表,分页模式
	public List<DepartmentModel> getListByAllWithPage(int rows, int page) throws Exception;
	//取得部门的个数
	public int getCountByAll() throws Exception;
	//取得部门的显示页数
	public int getPageCountByAll(int rows) throws Exception;
	
	//根据部门的no（PK）取得指定的部门对象
	public DepartmentModel getByNo(int no) throws Exception;
	
		
}
