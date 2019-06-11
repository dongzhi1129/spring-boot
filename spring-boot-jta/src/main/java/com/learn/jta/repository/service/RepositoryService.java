package com.learn.jta.repository.service;

import com.learn.jta.repository.model.Repository;

public interface RepositoryService {

	void updateRepository(Integer id,int version,int stock) throws Exception;

}
