package com.neusoft.oa.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.oa.admin.model.UserModel;
//管理员DAO层Mapper接口
@Mapper
public interface IUserMapper {
	public void update(UserModel um) throws Exception;
	//通过ID取得管理员对象
	public UserModel getById(String id) throws Exception;
}
