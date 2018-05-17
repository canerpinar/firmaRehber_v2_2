package com.firmaRehber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.firmaRehber.entity.Firma;
import com.firmaRehber.entity.Sube;
import com.firmaRehber.entity.User;
import com.firmaRehber.service.UserService;
import com.firmaRehber.service.WebAdministrationService;

@Controller
@RequestMapping("/admin/firma")
public class FirmaController {

	@Autowired
	private WebAdministrationService administrationService;
	

	
	@RequestMapping("/")
	public ModelAndView indexLoad() {
		ModelAndView model = new ModelAndView("admin/firma");
		model.addObject("firmaList", administrationService.getAllFirma());
		return model;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView firmaId(@PathVariable("id")String id){
		ModelAndView model=new ModelAndView("admin/firmaInformation");		
		Firma firma = administrationService.getFirma(id);
		model.addObject("firma", firma);
		model.addObject("urunler", administrationService.getAllUrunForFirma(Integer.parseInt(id)));
		
		return model;		
	}
	
	@RequestMapping("/urunEdit/{id}")//urun id
	public ModelAndView getUrunForEdit(@PathVariable("id")int id){
		ModelAndView model=new ModelAndView("admin/urunEdit");		
		model.addObject("urun", administrationService.getUrun(id));
		return model;		
	}
	

	
	
	
}
