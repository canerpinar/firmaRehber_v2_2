package com.firmaRehber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.firmaRehber.service.WebAdministrationService;

@Controller
@RequestMapping("/admin/urunler")
public class UrunController {

	@Autowired
	private WebAdministrationService administrationService;
	
	@RequestMapping("/")
	public ModelAndView indexPageLoad(){
		ModelAndView model = new ModelAndView("admin/urunler");
		model.addObject("urunler", administrationService.getAllUrun());
		return model;
	}
}
