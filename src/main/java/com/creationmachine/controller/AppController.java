package com.creationmachine.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.creationmachine.model.Page;
import com.creationmachine.service.PageService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    PageService service;
    
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/", "pages/list" }, method = RequestMethod.GET)
    public String listPages(ModelMap model) {

        List<Page> pages = service.findAllPages();
        model.addAttribute("pages", pages);
        return "pages/list";
    }

    @RequestMapping(value = { "pages/new" }, method = RequestMethod.GET)
    public String newPage(ModelMap model) {
        Page page = new Page();
        model.addAttribute("page", page);
        model.addAttribute("edit", false);
        return "pages/new";
    }

    @RequestMapping(value = { "pages/new" }, method = RequestMethod.POST)
    public String savePage(@Valid Page page, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "pages/new";
        }


        service.savePage(page);

        model.addAttribute("success", "Page " + page.getTitle() + " was added successfully");
        return "pages/success";
    }

    @RequestMapping(value = { "pages/edit/{id}" }, method = RequestMethod.GET)
    public String editPage(@PathVariable Long id, ModelMap model) {
        Page page = service.findById(id);
        model.addAttribute("page", page);
        model.addAttribute("edit", true);
        return "pages/new";
    }

    @RequestMapping(value = { "pages/edit/{id}" }, method = RequestMethod.POST)
    public String editPage(@Valid Page page, BindingResult result,
            ModelMap model, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "pages/new";
        }

        service.updatePage(page);

        model.addAttribute("success", "Page " + page.getTitle() + " was updated successfully");
        return "pages/success";
    }

    @RequestMapping(value = { "pages/delete/{id}" }, method = RequestMethod.GET)
    public String deletePage(@PathVariable Long id) {
        service.deletePageById(id);
        return "redirect:/list";
    }

}
