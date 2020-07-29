package com.neusoft.oa.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.hr.model.DepartmentModel;
import com.neusoft.oa.hr.service.IDepartmentService;
import com.neusoft.oa.restresult.Result;

@RestController
@RequestMapping(value="/department")
@CrossOrigin(origins = {"*", "null"})
public class DepartmentController {
	
	@Autowired
	private IDepartmentService ds=null;
		
	@PostMapping(value="/add")
	public Result<String> add(@RequestBody DepartmentModel dm) throws Exception{
		ds.add(dm);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("增加部门成功!");
		return result;
		
	}
	@PostMapping(value="/modify")
	public Result<String> modify(@RequestBody DepartmentModel dm) throws Exception{
		ds.modify(dm);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("修改部门成功!");
		return result;
	}
	@PostMapping(value="/delete")
	public Result<String> delete(@RequestBody DepartmentModel dm) throws Exception{
		ds.delete(dm);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("删除部门成功!");
		return result;
	}
	//取得所有部门列表（不分页）
	@GetMapping(value="/list/all")
	public Result<DepartmentModel> getListByAll() throws Exception{
		Result<DepartmentModel> result=new Result<DepartmentModel>();
		result.setList(ds.getListByAll());
		result.setStatus("OK");
		result.setMessage("取得全部部门列表成功!");
		return result;
		
	}
	
	//取得部门列表，分页模式
	@GetMapping(value="/list/all/page")
	public Result<DepartmentModel> getListByAllWitgPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception{
		Result<DepartmentModel> result=new Result<DepartmentModel>();
		result.setCount(ds.getCountByAll());
		result.setPageCount(ds.getPageCountByAll(rows));
		result.setRows(rows);
		result.setPage(page);
		result.setList(ds.getListByAllWithPage(rows, page));
		
		result.setStatus("OK");
		result.setMessage("取得部门列表分页方式成功!");
		return result;
	}
	@GetMapping(value="/get")
	public Result<DepartmentModel> getByNo(@RequestParam(required=true) int no) throws Exception{
		Result<DepartmentModel> result=new Result<DepartmentModel>();
		result.setResult(ds.getByNo(no));
		
		result.setStatus("OK");
		result.setMessage("取得指定部门对象成功!");
		return result;
	}
	

}
