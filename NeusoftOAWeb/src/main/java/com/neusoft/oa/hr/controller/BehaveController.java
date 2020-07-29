package com.neusoft.oa.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.hr.model.BehaveModel;
import com.neusoft.oa.hr.service.IBehaveService;
import com.neusoft.oa.restresult.Result;

//爱好REST Controller类
@RestController
@RequestMapping(value="/behave")
@CrossOrigin(origins = {"*", "null"})
public class BehaveController {
	@Autowired
	private IBehaveService behaveService=null;
	
	@PostMapping(value="/add")
	public Result<String> add(@RequestBody BehaveModel dm) throws Exception{
		behaveService.add(dm);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("增加爱好成功!");
		return result;
		
	}
	@PostMapping(value="/modify")
	public Result<String> modify(@RequestBody BehaveModel dm) throws Exception{
		behaveService.modify(dm);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("修改爱好成功!");
		return result;
	}
	@PostMapping(value="/delete")
	public Result<String> delete(@RequestBody BehaveModel dm) throws Exception{
		behaveService.delete(dm);
		Result<String> result=new Result<String>();
		result.setStatus("OK");
		result.setMessage("删除爱好成功!");
		return result;
	}
	//取得爱好列表，分页模式
	@GetMapping(value="/list/all/page")
	public Result<BehaveModel> getListByAllWitgPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception{
		Result<BehaveModel> result=new Result<BehaveModel>();
		result.setCount(behaveService.getCountByAll());
		result.setPageCount(behaveService.getPageCountByAll(rows));
		result.setRows(rows);
		result.setPage(page);
		result.setList(behaveService.getListByAllWithPage(rows, page));
		
		result.setStatus("OK");
		result.setMessage("取得爱好列表分页方式成功!");
		return result;
	}
	//取得爱好列表，非分页模式
		@GetMapping(value="/list/all")
		public Result<BehaveModel> getListByUnAllWitgPage() throws Exception{
			Result<BehaveModel> result=new Result<BehaveModel>();
			result.setList(behaveService.getListByAll());
			result.setStatus("OK");
			result.setMessage("取得爱好列表非分页方式成功!");
			return result;
		}
	@GetMapping(value="/get")
	public Result<BehaveModel> getByNo(@RequestParam(required=true) int no) throws Exception{
		Result<BehaveModel> result=new Result<BehaveModel>();
		result.setResult(behaveService.getByNo(no));
		
		result.setStatus("OK");
		result.setMessage("取得指定爱好对象成功!");
		return result;
	}
	

}
