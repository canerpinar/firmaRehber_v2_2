package com.firmaRehber.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.firmaRehber.entity.AltKategori;
import com.firmaRehber.entity.AltKategoriSeo;
import com.firmaRehber.entity.Kategori;
import com.firmaRehber.entity.KategoriSeo;
import com.firmaRehber.entity.Seo;
import com.firmaRehber.entity.SeoContent;
import com.firmaRehber.entity.SubAltKategori;
import com.firmaRehber.entity.SubAltKategoriSeo;
import com.firmaRehber.service.KategoriService;
import com.firmaRehber.service.WebAdministrationService;

@Controller
@RequestMapping(value="/admin/seo")
public class SeoController {

	@Autowired
	private WebAdministrationService administrationService;
	
	@Autowired
	private KategoriService kategoriService;
	
	
	
	@RequestMapping("/")
	public ModelAndView indexPage(){
		ModelAndView model = new ModelAndView("admin/seo");
		List<Seo> listSeo = administrationService.getAllSeo();
		List<SeoContent>listSeoContent = new ArrayList<>();
		
		listSeo.forEach(seo->{
			List<SeoContent> listSeo_Content = seo.getSeoContentList();
				listSeo_Content.forEach(seoContent->{
						listSeoContent.add(seoContent);
				});
		});		
		model.addObject("seoContentList", listSeoContent);
		model.addObject("seoList", listSeo);
		model.addObject("kategoriler", kategoriService.getAllKategori());
		
		kategoriService.getAllKategori().forEach(kategori->{
			System.out.println(kategori.getKategoriAd());
			kategori.getAltKategori().forEach(altkategori->{
				System.out.println(altkategori.getAltKategoriAd());
			});
		});
		
		model.addObject("kategoriSeo", new KategoriSeo());
		model.addObject("altKategoriSeo", new AltKategoriSeo());
		model.addObject("subAltKategoriSeo", new SubAltKategoriSeo());
		model.addObject("altKategoriler", kategoriService.getAllAltKategori());
		model.addObject("subAltKategoriler", kategoriService.getAllSubAltKategori());
		return model;
	}
	
	@RequestMapping(value="/saveKategoriSeo",method=RequestMethod.POST)	
	@ResponseBody
	public void saveKategoriSeo(@ModelAttribute("kategoriSeo")KategoriSeo kategoriSeo,HttpServletResponse response) throws IOException{
		administrationService.kategoriSeoSave(kategoriSeo);
		Kategori kategori = kategoriService.getAnaKategori(kategoriSeo.getKategoriId());
		kategori.setSeoAvaliable(true);
		kategoriService.updateKategori(kategori);
		response.sendRedirect("/admin/seo/");		
	}
	
	@RequestMapping(value="/saveAltKategoriSeo",method=RequestMethod.POST)	
	@ResponseBody
	public void saveAltKategoriSeo(@ModelAttribute("altKategoriSeo")AltKategoriSeo altKategoriSeo,HttpServletResponse response) throws IOException{
		administrationService.altKategoriSeoSave(altKategoriSeo);
		AltKategori altKategori = kategoriService.getAltKategori(altKategoriSeo.getAltKategoriId());
		altKategori.setSeoAvaliable(true);
		kategoriService.saveAltKategori(altKategori);		
		response.sendRedirect("/admin/seo/");		
	}
	
	@RequestMapping(value="/saveSubAltKategoriSeo",method=RequestMethod.POST)	
	@ResponseBody
	public void saveSubAltKategoriSeo(@ModelAttribute("subAltKategoriSeo")SubAltKategoriSeo subAltKategoriSeo,HttpServletResponse response) throws IOException{
		administrationService.subAltKategoriSeoSave(subAltKategoriSeo);
		SubAltKategori subAltKategori = kategoriService.getSubAltKategori(subAltKategoriSeo.getSubAltKategoriId());
		subAltKategori.setSeoAvaliable(true);
		kategoriService.saveSubKategori(subAltKategori);
		response.sendRedirect("/admin/seo/");		
	}

	
	
	
}
