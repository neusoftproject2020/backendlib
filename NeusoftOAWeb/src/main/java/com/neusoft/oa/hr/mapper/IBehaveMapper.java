package com.neusoft.oa.hr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.hr.model.BehaveModel;

@Mapper
public interface IBehaveMapper {
	
	//增加爱好
	public void insert(BehaveModel bm) throws Exception;
	//修改爱好
	public void update(BehaveModel bm) throws Exception;
	//删除爱好
	public void delete(BehaveModel bm) throws Exception;
	//取得所有爱好列表
	public List<BehaveModel> selectByAll() throws Exception;
	//取得所有爱好列表，分页模式 参数： start：起始位置； rows:取得记录格式， 
	public List<BehaveModel> selectByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得爱好的个数
	public int selectCountByAll() throws Exception;
	//根据爱好的no（PK）取得指定的爱好对象
	public BehaveModel selectByNo(int no) throws Exception;
	

}
