package com.neusoft.oa.hr.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.oa.hr.model.DepartmentModel;
import com.neusoft.oa.hr.model.EmployeeModel;
import com.neusoft.oa.hr.service.IEmployeeService;
import com.neusoft.oa.restresult.Result;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService=null;
	
	@PostMapping(value="/add")
	public Result<String> add(EmployeeModel em,@RequestParam(required=false) MultipartFile employeePhoto,@RequestParam(required=false) int[] selectBehaves) throws Exception{
		
		if(employeePhoto!=null&&(!employeePhoto.isEmpty())) {
			em.setPhoto(employeePhoto.getBytes());
			em.setPhotoFileName(employeePhoto.getOriginalFilename());
			em.setPhotoContentType(employeePhoto.getContentType());
		}
		employeeService.add(em);
		if(selectBehaves!=null&&selectBehaves.length>0) {
			employeeService.addBehaves(em.getId(), selectBehaves); //增加员工的爱好
		}
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("增加员工成功!");
		return result;
	}
	@PostMapping(value="/modify")
	public Result<String> modify(EmployeeModel em,@RequestParam(required=false) MultipartFile employeePhoto,@RequestParam(required=false) int[] selectBehaves) throws Exception{
		
		if(employeePhoto!=null&&(!employeePhoto.isEmpty())) {
			em.setPhoto(employeePhoto.getBytes());
			em.setPhotoFileName(employeePhoto.getOriginalFilename());
			em.setPhotoContentType(employeePhoto.getContentType());
		}
		employeeService.modify(em);
		employeeService.deleteBehaves(em.getId()); //先删除此员工的所有爱好
		if(selectBehaves!=null&&selectBehaves.length>0) {
			employeeService.addBehaves(em.getId(), selectBehaves); //增加员工的爱好
		}
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("修改员工成功!");
		return result;
	}
	
	@PostMapping(value="/delete")
	public Result<String> delete(EmployeeModel em) throws Exception{
		
		employeeService.modify(em);
		employeeService.deleteBehaves(em.getId()); //先删除此员工的所有爱好
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("删除员工成功!");
		return result;
	}
	
	@PostMapping(value="/list/condition/page")
	public Result<EmployeeModel> getListByConditionWithPage(
			@RequestParam(required=false,defaultValue="10") int rows, 
			@RequestParam(required=false,defaultValue="1") int page, 
			@RequestParam(required=false,defaultValue="0") int lowAge, 
			@RequestParam(required=false,defaultValue="0") int highAge,
			@RequestParam(required=false) Date startJoinDate, 
			@RequestParam(required=false) Date endJoinDate, 
			@RequestParam(required=false,defaultValue="") String sex, 
			@RequestParam(required=false,defaultValue="") String nameKey) throws Exception{
		Result<EmployeeModel> result=new Result<EmployeeModel>();
		result.setPage(page);
		result.setRows(rows);
		result.setCount(employeeService.getCountByCondition(lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey));
		result.setPageCount(employeeService.getPageCountByCondition(rows, lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey));
		result.setList(employeeService.getListByConditionWithPageWithDepartment(rows, page, lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey));
		result.setStatus("OK");
		result.setMessage("按条件检索员工列表成功!");
		return result;
	}
	

}
