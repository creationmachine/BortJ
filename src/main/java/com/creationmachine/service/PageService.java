package com.creationmachine.service;

import java.util.List;

import com.creationmachine.model.Page;

public interface PageService {

	Page findById(Long id);
	
	void savePage(Page page);
	
	void updatePage(Page page);
	
	void deletePageById(Long id);

	List<Page> findAllPages(); 
	
}
