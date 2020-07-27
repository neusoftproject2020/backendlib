package com.neusoft.oa.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.neusoft.oa.hr.mapper.IDepartmentMapper;
import com.neusoft.oa.hr.model.DepartmentModel;
import com.neusoft.oa.hr.service.IDepartmentService;

@Service
@Transactional  //环绕事务Advice的切入点
public class DepartmentServiceImpl implements IDepartmentService {
	@Autowired
	private IDepartmentMapper departmentMapper=null;
	@Override
	public int add(DepartmentModel dm) throws Exception {
		departmentMapper.insert(dm);
		return dm.getNo();
	}

	@Override
	public void modify(DepartmentModel dm) throws Exception {
		departmentMapper.update(dm);
	}

	@Override
	public void delete(DepartmentModel dm) throws Exception {
		departmentMapper.delete(dm);
	}

	@Override
	public List<DepartmentModel> getListByAll() throws Exception {
		
		return departmentMapper.selectByAll();
	}

	@Override
	public List<DepartmentModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return departmentMapper.selectByAllWithPage(rows*(page-1), rows);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return departmentMapper.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		
		int count=this.getCountByAll();
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
	public DepartmentModel getByNo(int no) throws Exception {
		
		return departmentMapper.selectByNo(no);
	}

}
