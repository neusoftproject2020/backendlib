package com.neusoft.oa.hr.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.hr.mapper.IEmployeeMapper;
import com.neusoft.oa.hr.model.BehaveModel;
import com.neusoft.oa.hr.model.EmployeeModel;
import com.neusoft.oa.hr.service.IEmployeeService;
@Service
@Transactional  //环绕事务Advice的切入点
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeMapper employeeMapper=null;
	
	@Override
	public void add(EmployeeModel em) throws Exception {
		if(em.getPhotoFileName()!=null) {
			employeeMapper.insertWithPhoto(em);
		}
		else {
			employeeMapper.insert(em);
		}

	}

	
	@Override
	public void modify(EmployeeModel em) throws Exception {
		if(em.getPhotoFileName()!=null) {
			employeeMapper.updateWithPhoto(em);
		}
		else {
			employeeMapper.update(em);
		}

	}
	//只修改员工的照片
	public void modifyPhoto(EmployeeModel em) throws Exception{
		employeeMapper.updatePhoto(em);
	}

	@Override
	public void delete(EmployeeModel em) throws Exception {
		employeeMapper.delete(em);

	}

	@Override
	public EmployeeModel getById(String id) throws Exception {
		
		return employeeMapper.selectById(id);
	}
	//取得指定的员工对象,取其照片
	@Override
	public EmployeeModel getByIdWithPhoto(String id) throws Exception{
		return employeeMapper.selectByIdWithPhoto(id);
	}

	@Override
	public EmployeeModel getByIdWithDepartment(String id) throws Exception {
		return employeeMapper.selectByIdWithDepartment(id);
	}

	@Override
	public EmployeeModel getByIdWithBehaves(String id) throws Exception {
		
		return employeeMapper.selectByIdWithBehaves(id);
	}

	@Override
	public EmployeeModel getByIdWithDepartmentAndBehaves(String id) throws Exception {
		
		return employeeMapper.selectByIdWithDepartmentAndBehaves(id);
	}

	@Override
	public List<EmployeeModel> getListByConditionWithPageWithDepartment(int rows, int page,int departmentNo, int lowAge, int highAge,
			Date startJoinDate, Date endJoinDate, String sex, String nameKey) throws Exception {
		
		if(nameKey!=null&&nameKey.trim().length()>0) {
			nameKey="%"+nameKey+"%";
		}
		
		return employeeMapper.selectListByConditionWithPageWithDepartment(rows*(page-1), rows,departmentNo, lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey);
	}

	@Override
	public int getCountByCondition(int departmentNo,int lowAge, int highAge, Date startJoinDate, Date endJoinDate, String sex,
			String nameKey) throws Exception {
		if(nameKey!=null&&nameKey.trim().length()>0) {
			nameKey="%"+nameKey+"%";
		}
		return employeeMapper.selectCountByCondition(departmentNo,lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey);
	}

	@Override
	public int getPageCountByCondition(int rows,int departmentNo, int lowAge, int highAge, Date startJoinDate, Date endJoinDate,
			String sex, String nameKey) throws Exception {
		if(nameKey!=null&&nameKey.trim().length()>0) {
			nameKey="%"+nameKey+"%";
		}
		int count=this.getCountByCondition(departmentNo,lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey);
		int pageCount=0;
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	
	}

	@Override
	public void addBehave(String id, int behaveNo) throws Exception {
		employeeMapper.insertBehave(id, behaveNo);

	}

	@Override
	public void addBehaves(String id, int[] behaveNos) throws Exception {
		employeeMapper.insertBehaves(id, behaveNos);

	}

	@Override
	public void deleteBehaves(String id) throws Exception {
		employeeMapper.deleteBehaves(id);

	}

	@Override
	public List<BehaveModel> getBehaveListByEmployee(String id) throws Exception {
		
		return employeeMapper.selectBehaveListByEmployee(id);
	}

	@Override
	public List<EmployeeModel> getListByBehaves(int[] behaves) throws Exception {
		
		return employeeMapper.selectListByBehaves(behaves);
	}

}
