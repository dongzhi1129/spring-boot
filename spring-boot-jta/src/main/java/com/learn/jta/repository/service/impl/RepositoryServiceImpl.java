package com.learn.jta.repository.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.jta.repository.mapper.RepositoryMapper;
import com.learn.jta.repository.model.Repository;
import com.learn.jta.repository.model.RepositoryExample;
import com.learn.jta.repository.service.RepositoryService;

@Service
public class RepositoryServiceImpl implements RepositoryService {

	@Resource
	private RepositoryMapper repositoryMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRepository(Integer id, int version, int stock) throws Exception {
		RepositoryExample example = new RepositoryExample();
		RepositoryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<Repository> list = repositoryMapper.selectByExample(example);
		// TODO Auto-generated method stub
		if (list == null || list.size() <= 0) {
			throw new Exception();
		}
		Repository repository = list.get(0);
		criteria.andVersionEqualTo(version);
		repository.setStock(repository.getStock() - stock);
		repository.setReLock(stock);
		repository.setVersion(repository.getVersion() + 1);
		int affectRows = repositoryMapper.updateByExample(repository, example);
		if (affectRows <= 0) {
			throw new Exception("update failed");
		}

	}

}
