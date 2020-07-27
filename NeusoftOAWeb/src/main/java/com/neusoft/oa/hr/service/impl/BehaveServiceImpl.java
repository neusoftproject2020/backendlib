package com.neusoft.oa.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.hr.mapper.IBehaveMapper;
import com.neusoft.oa.hr.model.BehaveModel;
import com.neusoft.oa.hr.service.IBehaveService;
@Service
@Transactional  //环绕事务Advice的切入点
public class BehaveServiceImpl implements IBehaveService {
	@Autowired
	private IBehaveMapper behaveMapper=null;

	@Override
	public int add(BehaveModel bm) throws Exception {
		behaveMapper.insert(bm);
		return bm.getNo();
	}

	@Override
	public void modify(BehaveModel bm) throws Exception {
		behaveMapper.update(bm);

	}

	@Override
	public void delete(BehaveModel bm) throws Exception {
		behaveMapper.delete(bm);

	}

	@Override
	public List<BehaveModel> getListByAll() throws Exception {
		
		return behaveMapper.selectByAll();
	}

	@Override
	public List<BehaveModel> getListByAllWithPage(int rows, int page) throws Exception {
		return behaveMapper.selectByAllWithPage(rows*(page-1), rows);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return behaveMapper.selectCountByAll();
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
	public BehaveModel getByNo(int no) throws Exception {
		
		return behaveMapper.selectByNo(no);
	}

}
