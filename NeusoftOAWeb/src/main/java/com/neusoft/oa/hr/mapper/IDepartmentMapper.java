package com.neusoft.oa.hr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.hr.model.DepartmentModel;

//部门的Mapper接口（DAO接口）
@Mapper
public interface IDepartmentMapper {
	//增加部门
	public void insert(DepartmentModel dm) throws Exception;
	//修改部门
	public void update(DepartmentModel dm) throws Exception;
	//删除部门
	public void delete(DepartmentModel dm) throws Exception;
	//取得所有部门列表
	public List<DepartmentModel> selectByAll() throws Exception;
	//取得所有部门列表，分页模式 参数： start：起始位置； rows:取得记录格式， 
	public List<DepartmentModel> selectByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得部门的个数
	public int selectCountByAll() throws Exception;
	//根据部门的no（PK）取得指定的部门对象
	public DepartmentModel selectByNo(int no) throws Exception;
	//取得指定的部门，并取得其关联的员工集合
	public DepartmentModel selectByNoWithEmployees(int no) throws Exception;
	

}
