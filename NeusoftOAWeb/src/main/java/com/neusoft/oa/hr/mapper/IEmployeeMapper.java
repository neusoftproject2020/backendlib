package com.neusoft.oa.hr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.hr.model.BehaveModel;
import com.neusoft.oa.hr.model.EmployeeModel;

@Mapper
public interface IEmployeeMapper {

	//增加员工，没有图片
	public void insert(EmployeeModel em) throws Exception;
	//增加员工，有图片
	public void insertWithPhoto(EmployeeModel em) throws Exception;
	//修改员工，没有图片
	public void update(EmployeeModel em) throws Exception;
	//修改员工，有图片
	public void updateWithPhoto(EmployeeModel em) throws Exception;
	//只修改员工的照片
	public void updatePhoto(EmployeeModel em) throws Exception;
	
	//删除员工
	public void delete(EmployeeModel em) throws Exception;
	//取得指定的员工对象,不取关联的部门和爱好
	public EmployeeModel selectById(String id) throws Exception;
	//取得指定的员工对象,取得员工照片
	public EmployeeModel selectByIdWithPhoto(String id) throws Exception;
		
	//取得所有员工列表，不取关联的部门和爱好属性
	public List<EmployeeModel> selectListByAll() throws Exception;
	//==========================================================================================
	//关联映射SQL的编程
	//==========================================================================================
	//取得指定的员工对象,取关联的部门，不取爱好
	public EmployeeModel selectByIdWithDepartment(String id) throws Exception;
	//取得指定的员工对象,不取关联的部门，取爱好
	public EmployeeModel selectByIdWithBehaves(String id) throws Exception;
	//取得指定的员工对象,取关联的部门，也取爱好
	public EmployeeModel selectByIdWithDepartmentAndBehaves(String id) throws Exception;
		
	//取得所有员工列表，取关联的部门，不取关联爱好属性, 分页模式
	public List<EmployeeModel> selectListByAllWithPageWithDepartment(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得所有员工列表，取关联的部门，取关联的爱好属性
	public List<EmployeeModel> selectListByAllWithDepartmentAndBehaves() throws Exception;
	
	//=================================================================================================
	//动态SQL映射
	//=================================================================================================
	//按综合条件检索员工列表，分页模式，之取关联的部门属性对象
	public List<EmployeeModel> selectListByConditionWithPageWithDepartment(
			@Param("start") int start,@Param("rows") int rows,@Param("departmentNo") int departmentNo
			,@Param("lowAge") int lowAge,@Param("highAge") int highAge
			,@Param("startJoinDate") Date startJoinDate,@Param("endJoinDate") Date endJoinDate
			,@Param("sex") String sex,@Param("nameKey") String nameKey) throws Exception;
	
	//按综合条件检索员工个数，分页模式，之取关联的部门属性对象
	public int selectCountByCondition(@Param("departmentNo") int departmentNo,
			@Param("lowAge") int lowAge,@Param("highAge") int highAge
			,@Param("startJoinDate") Date startJoinDate,@Param("endJoinDate") Date endJoinDate
			,@Param("sex") String sex,@Param("nameKey") String nameKey) throws Exception;
	
	//=============================================================================================
	// 与员工爱好的关联的方法
	//=============================================================================================
	//为员工增加爱好，单个爱好, 参数：员工ID，爱好编号
	public void insertBehave(@Param("id") String id,@Param("behaveNo") int behaveNo) throws Exception;
	//为员工增加爱好，多个爱好,参数:员工ID，爱好编号数组 , 此方法使用动态SQL foreach机制
	public void insertBehaves(@Param("id") String id,@Param("behaveNos") int[] behaveNos) throws Exception;
	//删除指定员工的全部爱好
	public void deleteBehaves(String id) throws Exception;
	//取得指定员工的爱好列表
	public List<BehaveModel> selectBehaveListByEmployee(String id) throws Exception;
	//取得有指定多个爱好的员工列表，爱好通过数组参数确定
	public List<EmployeeModel> selectListByBehaves(@Param("behaves") int[] behaves) throws Exception;
	
	
}
