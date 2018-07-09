package com.firmaRehber.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.firmaRehber.entity.AltKategori;
import com.firmaRehber.entity.Kategori;
import com.firmaRehber.entity.Message;
import com.firmaRehber.entity.Reklam;
import com.firmaRehber.entity.SubAltKategori;
import com.firmaRehber.service.KategoriService;
import com.firmaRehber.service.UploadFileService;
import com.firmaRehber.service.WebAdministrationService;

/*
 * this controller class does hand with application admin panel
 * 
 * 
 * 
 * 
 */

@Controller
@RequestMapping(value="/siteUpdate")
public class SiteUpdateController {

	@Autowired
	private ServletContext context;
	
	@Autowired
	private KategoriService kategoriService;
	
	@Autowired
	private WebAdministrationService administrationService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@RequestMapping(value="/kategoriUpdate/{id}",method=RequestMethod.POST)
	@ResponseBody
	public void updateKategori(@PathVariable("id") String id,@RequestParam("anaKategoriName") String anaKategoriAd,@RequestParam("altKategori") String[] altKategoriAd) {		
		System.out.println(id);
		Kategori kategori = kategoriService.getAnaKategori(Integer.parseInt(id));
		kategori.setKategoriAd(anaKategoriAd);
		int counter = 0;
		for (String string : altKategoriAd) {
			kategori.getAltKategori().get(counter).setAltKategoriAd(string);
			counter++;
		}
		kategoriService.updateKategori(kategori);
	}
	
	@RequestMapping(value="/reklamEdit/{id}",method=RequestMethod.POST)
	@ResponseBody
	public void updateReklam(@PathVariable("id")String id,@ModelAttribute("reklam")Reklam reklam,@RequestParam("file")MultipartFile file,
			HttpServletResponse response) throws IOException{
		Reklam reklamOld = administrationService.getReklam(Integer.parseInt(id));
		reklamOld.setReklamAd(reklam.getReklamAd());
		reklamOld.setReklamImage(file.getOriginalFilename());
		
		reklamOld.setReklamPosition(reklam.getReklamPosition());
		reklamOld.setReklamLink(reklam.getReklamLink());
		
		String path=context.getRealPath("")+"imagesForReklam\\";
		Files.deleteIfExists(Paths.get(path+reklam.getReklamAd()));
		uploadFileService.saveFileForSlider(file, path);
		administrationService.updateReklam(reklamOld);
		response.sendRedirect("/admin/siteGenel");
		
		
		/*
		System.out.println(reklam.getId());
		System.out.println(reklam.getReklamAd());
		System.out.println(reklam.getReklamLink());
		*/
	}
	
	@RequestMapping(value="/reklamDelete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public void deleteReklam(@PathVariable("id")String id,HttpServletResponse response) throws IOException{
		administrationService.deleteReklam(id);
		response.sendRedirect("/admin/siteGenel");
	}
	
	@RequestMapping(value="/sentMessage",method=RequestMethod.POST)
	@ResponseBody
	public void sentMessageForFirma(@RequestParam("firmaId") String id,@RequestParam("mesajContent")String mesajContent){
		Message message = new Message();
		message.setMesajContent("Admin : "+ mesajContent);
		message.setMesajKimden("Admin");
		message.setGonderenId(-1);
		message.setGonderenUyemi(true);
		message.setMesajSahipLink("");
		message.setOkunmaDurum(false);
		message.setMesajKimeId(Integer.parseInt(id));		
		administrationService.sentMessage(message);
	}
	
	@RequestMapping(value="/kategoriDelete/{kategori_id}")
	@ResponseBody
	public void deleteKategori(@PathVariable("kategori_id")int kategori_id,HttpServletResponse response) throws IOException {
		Kategori kategori =kategoriService.getAnaKategori(kategori_id);
			
			kategori.getAltKategori().forEach(altKat->{
				altKat.getSubaltKategori().forEach(subAlt->{
				kategoriService.deleteSubAltKategori(subAlt);
				});
				kategoriService.deleteAltKategori(altKat);
				
			});
			kategoriService.deleteKategori(kategori);
			response.sendRedirect("/admin/siteGenel");
				
	}
	
	@RequestMapping(value="/altKategoriDelete/{kategoriId}/{altKategoriId}")
	@ResponseBody
	public void deleteAltKategori(@PathVariable("kategoriId")String kategoriId,@PathVariable("altKategoriId")String altKategoriId,HttpServletResponse response) throws IOException{
		System.out.println("kategori Id :".concat(kategoriId));
		System.out.println("alt kategori Id :".concat(altKategoriId));
		//Kategori kategori = kategoriService.getAnaKategori(Integer.parseInt(kategoriId));
		AltKategori altKategori = kategoriService.getAltKategori(Integer.parseInt(altKategoriId));
		for (SubAltKategori subAlt : altKategori.getSubaltKategori()) {
			kategoriService.deleteSubAltKategori(subAlt);
		}
		kategoriService.deleteAltKategori(altKategori);
		
		response.sendRedirect("/admin/siteGenel/kategoriEdit/"+kategoriId);

	}
	
	@RequestMapping(value="/subAltKategoriDelete/{kategoriId}/{subAltKategoriId}")
	@ResponseBody
	public void deleteSubAltKategori(@PathVariable("kategoriId")String kategoriId,@PathVariable("subAltKategoriId")String subAltKategoriId,HttpServletResponse response) throws IOException{
		System.out.println("kategori Id :".concat(kategoriId));
		System.out.println("sub alt kategori Id :".concat(subAltKategoriId));
		SubAltKategori subAltKategori = kategoriService.getSubAltKategori(Integer.parseInt(subAltKategoriId));
		kategoriService.deleteSubAltKategori(subAltKategori);
		response.sendRedirect("/admin/siteGenel/kategoriEdit/"+kategoriId);
	}
	
	
	
	
	
}
