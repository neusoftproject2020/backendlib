package com.neusoft.oa.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.admin.model.UserModel;
import com.neusoft.oa.admin.service.IUserService;
import com.neusoft.oa.restresult.Result;

//管理员控制器类
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*", "null"})
public class UserController {
	@Autowired
	private IUserService userService=null;
	
	@GetMapping(value="/validate/{id}/{password}")
	public Result<UserModel> validate(@PathVariable(value="id") String id,@PathVariable(value="password") String password,HttpSession session) throws Exception{
		Result<UserModel> result=new Result<UserModel>();
		if(userService.validate(id, password)) {
			result.setStringResult("Y");			
			result.setResult(userService.getById(id));
			result.setMessage("管理员验证成功");
			//保存管理员会话信息
			session.setAttribute("user", result.getResult());
		}
		else {
			result.setStringResult("N");
			result.setMessage("管理员验证失败");
		}
		result.setStatus("OK");
		return result;
		
	}
	@GetMapping(value="/get/{id}")
	public Result<UserModel> get(@PathVariable(value="id") String id) throws Exception{
		Result<UserModel> result=new Result<UserModel>();
		
		result.setStatus("OK");
		result.setResult(userService.getById(id));
		result.setMessage("取得管理员成功");
		
		return result;
		
	}
	@GetMapping(value="/checkUserLogin")
	public Result<String> checkUserLogined(HttpSession session) throws Exception{
		Result<String> result=new Result<String>();
		if(session!=null&&session.getAttribute("user")!=null) {
			result.setStringResult("Y");
			result.setStatus("OK");
			result.setMessage("管理员已经登录");
		}
		else {
			result.setStringResult("N");
			result.setStatus("OK");
			result.setMessage("管理员没有登录");
		}
		return result;
	}
	

}
