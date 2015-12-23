package com.creationmachine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creationmachine.dao.PageDao;
import com.creationmachine.model.Page;

@Service("pageService")
@Transactional
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao dao;

    public Page findById(Long id) {
	return dao.findById(id);
    }

    public void savePage(Page page) {
	dao.savePage(page);
    }

    public void updatePage(Page page) {
	Page entity = dao.findById(page.getId());
	if (entity != null) {
	    entity.setTitle(page.getTitle());
	    entity.setBody(page.getBody());
	    entity.setAuthor(page.getAuthor());
	    entity.setPostDate(page.getPostDate());
	}
    }

    public void deletePageById(Long id) {
	dao.deletePageById(id);
    }

    public List<Page> findAllPages() {
	return dao.findAllPages();
    }

}
