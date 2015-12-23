package com.creationmachine.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.creationmachine.model.Page;

@Repository("pageDao")
public class PageDaoImpl extends AbstractDao<Integer, Page> implements PageDao {

	public Page findById(Long id) {
		return getByKey(id);
	}

	public void savePage(Page page) {
		persist(page);
	}

	public void deletePageById(Long id) {
		Query query = getSession().createSQLQuery("delete from PAGE where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Page> findAllPages() {
		Criteria criteria = createEntityCriteria();
		return (List<Page>) criteria.list();
	}

	public Page findPageById(Long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Page) criteria.uniqueResult();
	}
}
