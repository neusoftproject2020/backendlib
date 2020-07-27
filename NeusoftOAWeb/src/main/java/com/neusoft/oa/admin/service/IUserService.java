package com.neusoft.oa.admin.service;

import com.neusoft.oa.admin.model.UserModel;
//管理员业务接口
public interface IUserService {
	
	//取得指定的用户对象
	public UserModel getById(String id) throws Exception;
	//验证用户合法性，通过id和密码与数据表相符即可
	public boolean validate(String id,String password) throws Exception;
	//修改自己的密码
	public void changePassword(String id,String password) throws Exception;

}
