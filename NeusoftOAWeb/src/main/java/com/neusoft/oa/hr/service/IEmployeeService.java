package com.neusoft.oa.hr.service;

import java.util.Date;
import java.util.List;

import com.neusoft.oa.hr.model.BehaveModel;
import com.neusoft.oa.hr.model.EmployeeModel;

//员工业务接口
public interface IEmployeeService {
	
	//增加员工
	public void add(EmployeeModel em) throws Exception;
	//修改员工
	public void modify(EmployeeModel em) throws Exception;
	//只修改员工的照片
	public void modifyPhoto(EmployeeModel em) throws Exception;
		
	//删除员工
	public void delete(EmployeeModel em) throws Exception;
	//取得指定的员工对象,不取关联的部门和爱好
	public EmployeeModel getById(String id) throws Exception;
	//取得指定的员工对象,取其照片
	public EmployeeModel getByIdWithPhoto(String id) throws Exception;
		
	//取得指定的员工对象,取关联的部门，不取爱好
	public EmployeeModel getByIdWithDepartment(String id) throws Exception;
	//取得指定的员工对象,不取关联的部门，取爱好
	public EmployeeModel getByIdWithBehaves(String id) throws Exception;
	//取得指定的员工对象,取关联的部门，也取爱好
	public EmployeeModel getByIdWithDepartmentAndBehaves(String id) throws Exception;	
	
	//此方法未来在前端员工页面使用，使用分页方式显示员工列表，并能进行条件检索
	//按检索条件取得员工列表，并取得关联的部门对象
	public List<EmployeeModel> getListByConditionWithPageWithDepartment(
			int rows,int page,int departmentNo,int lowAge,int highAge,Date startJoinDate,Date endJoinDate
			,String sex, String nameKey) throws Exception;
	
	//按综合条件检索员工个数
	public int getCountByCondition(int departmentNo,
			int lowAge,int highAge,Date startJoinDate,Date endJoinDate
			,String sex, String nameKey) throws Exception;
	
	//按综合条件检索员工页数
	public int getPageCountByCondition(int rows,int departmentNo,
			int lowAge,int highAge,Date startJoinDate,Date endJoinDate
			,String sex, String nameKey) throws Exception;
		
	
	//=============================================================================================
	// 与员工爱好的关联的方法
	//=============================================================================================
	//为员工增加爱好，单个爱好, 参数：员工ID，爱好编号
	public void addBehave( String id, int behaveNo) throws Exception;
	//为员工增加爱好，多个爱好,参数:员工ID，爱好编号数组 , 此方法使用动态SQL foreach机制
	public void addBehaves(String id, int[] behaveNos) throws Exception;
	//删除指定员工的全部爱好
	public void deleteBehaves(String id) throws Exception;
	//取得指定员工的爱好列表
	public List<BehaveModel> getBehaveListByEmployee(String id) throws Exception;
	//取得有指定多个爱好的员工列表，爱好通过数组参数确定
	public List<EmployeeModel> getListByBehaves(int[] behaves) throws Exception;

}
