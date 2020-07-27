package com.neusoft.oa.hr.service;

import java.util.List;

import com.neusoft.oa.hr.model.BehaveModel;

//爱好业务接口
public interface IBehaveService {

	//增加爱好
	public int add(BehaveModel bm) throws Exception;
	//修改爱好
	public void modify(BehaveModel bm) throws Exception;
	//删除爱好
	public void delete(BehaveModel bm) throws Exception;
	//取得所有爱好列表
	public List<BehaveModel> getListByAll() throws Exception;
	//取得所有爱好列表,分页模式
	public List<BehaveModel> getListByAllWithPage(int rows, int page) throws Exception;
	//取得爱好的个数
	public int getCountByAll() throws Exception;
	//取得爱好的显示页数
	public int getPageCountByAll(int rows) throws Exception;
	
	//根据爱好的no（PK）取得指定的爱好对象
	public BehaveModel getByNo(int no) throws Exception;
}
