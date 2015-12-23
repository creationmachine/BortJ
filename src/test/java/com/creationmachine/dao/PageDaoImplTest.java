package com.creationmachine.dao;

import java.math.BigDecimal;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.creationmachine.dao.PageDao;
import com.creationmachine.model.Page;


public class PageDaoImplTest extends EntityDaoImplTest{

	@Autowired
	PageDao pageDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
	    FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
	    builder.setColumnSensing(true);
	    IDataSet dataSet = builder.build(this.getClass().getClassLoader().getResourceAsStream("Page.xml"));
		return dataSet;
	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Employee.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
	*/

	@Test
	public void findById(){
		Assert.assertNotNull(pageDao.findById(1L));
		Assert.assertNull(pageDao.findById(3L));
	}

	
	@Test
	public void savePage(){
		pageDao.savePage(getSamplePage());
		Assert.assertEquals(pageDao.findAllPages().size(), 3);
	}


	@Test
	public void findAllPages(){
		Assert.assertEquals(pageDao.findAllPages().size(), 2);
	}
	
	public Page getSamplePage(){
		Page page = new Page();
		page.setId(5L);
//		page.setLastName("Smith");
//		page.setEmail("karen.smith@domain.com");
//		page.setSsn("12345");
//		page.setSalary(new BigDecimal(10980));
//		page.setJoiningDate(new LocalDate());
		return page;
	}

}
