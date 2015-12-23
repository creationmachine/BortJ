package com.creationmachine.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.creationmachine.dao.PageDao;
import com.creationmachine.model.Page;
import com.creationmachine.service.PageServiceImpl;

public class PageServiceImplTest {

	@Mock
	PageDao dao;
	
	@InjectMocks
	PageServiceImpl pageService;
	
	@Spy
	List<Page> pages = new ArrayList<Page>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		pages = getEmployeeList();
	}

	@Test
	public void findById(){
		Page emp = pages.get(0);
		when(dao.findById((long) anyInt())).thenReturn(emp);
		Assert.assertEquals(pageService.findById(emp.getId()),emp);
	}

	@Test
	public void savePage(){
		doNothing().when(dao).savePage(any(Page.class));
		pageService.savePage(any(Page.class));
		verify(dao, atLeastOnce()).savePage(any(Page.class));
	}
	
	@Test
	public void updatePage(){
		Page emp = pages.get(0);
		when(dao.findById((long) anyInt())).thenReturn(emp);
		pageService.updatePage(emp);
		verify(dao, atLeastOnce()).findById((long) anyInt());
	}

	
	@Test
	public void findAllPages(){
		when(dao.findAllPages()).thenReturn(pages);
		Assert.assertEquals(pageService.findAllPages(), pages);
	}
	

	
	public List<Page> getEmployeeList(){
		Page e1 = new Page();
		e1.setId(1L);
//		e1.setFirstName("Karen");
//		e1.setLastName("Axel");
//		e1.setJoiningDate(new LocalDate());
//		e1.setSalary(new BigDecimal(10000));
//		e1.setSsn("XXX111");
		
		Page e2 = new Page();
		e2.setId(2L);
//		e2.setFirstName("Karen");
//		e2.setLastName("Axel");
//		e2.setJoiningDate(new LocalDate());
//		e2.setSalary(new BigDecimal(20000));
//		e2.setSsn("XXX222");
		
		pages.add(e1);
		pages.add(e2);
		return pages;
	}
	
}
