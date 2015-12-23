package com.creationmachine.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.creationmachine.controller.AppController;
import com.creationmachine.model.Page;
import com.creationmachine.service.PageService;

public class AppControllerTest {

	@Mock
	PageService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	AppController appController;
	
	@Spy
	List<Page> pages = new ArrayList<Page>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		pages = getEmployeeList();
	}
	
	@Test
	public void listEmployees(){
		when(service.findAllPages()).thenReturn(pages);
		Assert.assertEquals(appController.listPages(model), "all");
		Assert.assertEquals(model.get("pages"), pages);
		verify(service, atLeastOnce()).findAllPages();
	}
	
	@Test
	public void newEmployee(){
		Assert.assertEquals(appController.newPage(model), "new");
		Assert.assertNotNull(model.get("page"));
		Assert.assertFalse((Boolean)model.get("edit"));
		// Assert.assertEquals(((Page)model.get("page")).getId(), 0);
	}


	@Test
	public void saveEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).savePage(any(Page.class));
		Assert.assertEquals(appController.savePage(pages.get(0), result, model), "new");
	}

	@Test
	public void saveEmployeeWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		Assert.assertEquals(appController.savePage(pages.get(0), result, model), "new");
	}

	
	@Test
	public void saveEmployeeWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).savePage(any(Page.class));
		Assert.assertEquals(appController.savePage(pages.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Created successfully");
	}

	@Test
	public void editEmployee(){
		Page emp = pages.get(0);
		// Assert.assertEquals(appController.editPage(anyString(), model), "edit");
		Assert.assertNotNull(model.get("page"));
		Assert.assertTrue((Boolean)model.get("edit"));
		// Assert.assertEquals(((Page)model.get("page")).getId(), 1);
	}

	@Test
	public void updateEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updatePage(any(Page.class));
		// Assert.assertEquals(appController.updatePage(pages.get(0), result, model,""), "new");
	}

	@Test
	public void updateEmployeeWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		// Assert.assertEquals(appController.updatePage(pages.get(0), result, model,""), "new");
	}

	@Test
	public void updateEmployeeWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).updatePage(any(Page.class));
		Assert.assertEquals(appController.updateEmployee(pages.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Employee Karen Smith updated successfully");
	}
	
	
	@Test
	public void deleteEmployee(){
		doNothing().when(service).deletePageById((long) anyInt());
		Assert.assertEquals(appController.deletePage(123L), "redirect:/list");
	}

	public List<Page> getEmployeeList(){
		Page e1 = new Page();
		e1.setId(1L);
//		e1.setFirstName("Karen");
//		e1.setLastName("Smith");
//		e1.setEmail("karen.smith@domain.com");
//		e1.setJoiningDate(new LocalDate());
//		e1.setSalary(new BigDecimal(10000));
//		e1.setSsn("XXX111");
		
		Page e2 = new Page();
		e2.setId(2L);
//		e2.setFirstName("Jeremy");
//		e2.setLastName("Doe");
//		e1.setEmail("jeremy.doe@domain.com");
//		e2.setJoiningDate(new LocalDate());
//		e2.setSalary(new BigDecimal(20000));
//		e2.setSsn("XXX222");
		
		pages.add(e1);
		pages.add(e2);
		return pages;
	}
}
