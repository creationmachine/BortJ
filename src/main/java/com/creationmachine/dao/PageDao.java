package com.creationmachine.dao;

import java.util.List;

import com.creationmachine.model.Page;

public interface PageDao {

	Page findById(Long id);

	void savePage(Page page);
	
	void deletePageById(Long id);
	
	List<Page> findAllPages();

	Page findPageById(Long id);

}
