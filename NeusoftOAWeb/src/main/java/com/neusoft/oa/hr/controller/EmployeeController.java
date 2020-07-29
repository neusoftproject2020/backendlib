package com.neusoft.oa.hr.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = {"*", "null"})
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService=null;
	
	@PostMapping(value="/add")
	public Result<String> add(EmployeeModel em,@RequestParam(required=false) MultipartFile employeePhoto,@RequestParam(required=false) int[] selectBehaves) throws Exception{
		
		if(employeePhoto!=null&&(!employeePhoto.isEmpty())) {
			//目标文件地址
			File dist=new File("d:/webroot/photo/"+employeePhoto.getOriginalFilename());
			em.setPhotoFileName(employeePhoto.getOriginalFilename());
			//em.setPhotoFileName("P_"+em.getId()+"."+    );
			em.setPhotoContentType(employeePhoto.getContentType());	
			//取得文件的字节
			em.setPhoto(employeePhoto.getBytes());
			//保存上传文件到目标目录
			employeePhoto.transferTo(dist);
			
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
	
	@PostMapping(value="/update/photo")
	public Result<String> updatePhoto(EmployeeModel em,@RequestParam(required=false) MultipartFile employeePhoto,@RequestParam(required=false) int[] selectBehaves) throws Exception{
		if(employeePhoto!=null&&(!employeePhoto.isEmpty())) {
			em.setPhoto(employeePhoto.getBytes());
			em.setPhotoFileName(employeePhoto.getOriginalFilename());
			em.setPhotoContentType(employeePhoto.getContentType());
		}
		employeeService.modifyPhoto(em);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("修改员工照片成功!");
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
	
	@GetMapping(value="/list/condition/page")
	public Result<EmployeeModel> getListByConditionWithPage(
			@RequestParam(required=false,defaultValue="10") int rows, 
			@RequestParam(required=false,defaultValue="1") int page, 
			@RequestParam(required=false,defaultValue="0") int departmentNo,
			@RequestParam(required=false,defaultValue="0") int lowAge, 
			@RequestParam(required=false,defaultValue="0") int highAge,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startJoinDate, 
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endJoinDate, 
			@RequestParam(required=false,defaultValue="") String sex, 
			@RequestParam(required=false,defaultValue="") String nameKey) throws Exception{
		Result<EmployeeModel> result=new Result<EmployeeModel>();
		result.setPage(page);
		result.setRows(rows);
		result.setCount(employeeService.getCountByCondition(departmentNo,lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey));
		result.setPageCount(employeeService.getPageCountByCondition(rows,departmentNo, lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey));
		result.setList(employeeService.getListByConditionWithPageWithDepartment(rows, page,departmentNo,lowAge, highAge, startJoinDate, endJoinDate, sex, nameKey));
		result.setStatus("OK");
		result.setMessage("按条件检索员工列表成功!");
		return result;
	}
	@GetMapping("/get/{id}")
	public Result<EmployeeModel> get(@PathVariable(value="id") String id) throws Exception{
		Result<EmployeeModel> result=new Result<EmployeeModel>();
		result.setResult(employeeService.getByIdWithDepartmentAndBehaves(id));
		result.setStatus("OK");
		result.setMessage("按取得指定员工成功!");
		return result;
		
	}
	//
	//显示或下载员工的照片字段photo
	@RequestMapping("/photo")
	public ResponseEntity<byte[]> showPhoto(@RequestParam String id) throws Exception{
		EmployeeModel em=employeeService.getByIdWithPhoto(id);
		if(em!=null&&em.getPhotoContentType()!=null) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Content-Type", em.getPhotoContentType());
			return new ResponseEntity<byte[]>(em.getPhoto(), responseHeaders,HttpStatus.OK);
		}
		else {
			return null;
		}	
	}
	

}
