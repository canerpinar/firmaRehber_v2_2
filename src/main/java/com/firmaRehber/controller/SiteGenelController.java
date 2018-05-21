package com.firmaRehber.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.firmaRehber.entity.AltKategori;
import com.firmaRehber.entity.AltKategoriSeo;
import com.firmaRehber.entity.Firma;
import com.firmaRehber.entity.Kategori;
import com.firmaRehber.entity.KategoriSeo;
import com.firmaRehber.entity.Reklam;
import com.firmaRehber.entity.Seo;
import com.firmaRehber.entity.SubAltKategori;
import com.firmaRehber.entity.SubAltKategoriSeo;
import com.firmaRehber.entity.Sube;
import com.firmaRehber.entity.Urun;
import com.firmaRehber.service.FirmaService;
import com.firmaRehber.service.KategoriService;
import com.firmaRehber.service.UploadFileService;
import com.firmaRehber.service.WebAdministrationService;





@RestController
@RequestMapping(value="/admin/siteGenelEdit")
public class SiteGenelController {

	@Autowired
	private KategoriService kategoriService;
	
	
	@Autowired
	private WebAdministrationService administrationService;
	
	@GetMapping(value = "/kategoriEdit/{id}")	
	public Kategori getKategori(@PathVariable("id") String id){
		Kategori kategori = kategoriService.getAnaKategori(Integer.parseInt(id));
		return kategori;
	}
	
	@GetMapping(value="/getKategori/{kategoriAd}")
	public List<Kategori> getKategoriWithName(@PathVariable("kategoriAd")String kategoriAd){
		return kategoriService.getKategoriWithName(kategoriAd);
	}
	
	@GetMapping(value="/getAltKategori/{altkategoriAd}")
	public List<AltKategori> getAltKategoriWithName(@PathVariable("altkategoriAd")String altkategoriAd){
		return kategoriService.getAltKategoriWithName(altkategoriAd);
	}
	
	@GetMapping(value="/getSubAltKategori/{subaltkategoriAd}")
	public List<SubAltKategori> getSubAltKategoriWithName(@PathVariable("subaltkategoriAd")String subaltkategoriAd){
		return kategoriService.getSubAltKategoriWithName(subaltkategoriAd);
	}
	
	
	
	@GetMapping(value = "/reklamEdit/{id}")	
	public Reklam getReklam(@PathVariable("id") String id){
		Reklam reklam = administrationService.getReklam(Integer.parseInt(id));
		return reklam;
		
	}
	
	@GetMapping(value="/messageSent/{id}")
	public Firma getFirmaInformation(@PathVariable("id") String id) {		
		Firma firma = administrationService.getFirma(id);
		return firma;
	}
	
	@GetMapping(value="/referansReview/{id}")
	public Firma getFirmaAndReferans(@PathVariable("id") String id,HttpServletResponse response) {		
		Firma firma = administrationService.getFirma(id);
		return firma;
	}
	@GetMapping(value="/allKategori")
	public List<Kategori> getAllFirma(){
		return (List<Kategori>) kategoriService.getAllKategori();
	}
	
	@GetMapping(value="/altKategoriEdit/{id}")
	public AltKategori getAltKategori(@PathVariable("id")String id){
		return  kategoriService.getAltKategori(Integer.parseInt(id));
	}
	
	@GetMapping(value="/subAltKategoriEdit/{id}")
	public SubAltKategori getSubAltKategori(@PathVariable("id")String id){
		return  kategoriService.getSubAltKategori(Integer.parseInt(id));
	}
	
	@GetMapping(value="/kategoriSeoReview/{id}")
	public KategoriSeo getKategoriSeo() {
		return null;
	}
	
	@GetMapping(value="/altKategoriSeoReview/{id}")
	public List<AltKategoriSeo> getAltKategoriSeo(@PathVariable("id")int id) {
		return administrationService.getAltKategoriSeoList(id);
	}
	
	@GetMapping(value="/subAltKategoriSeoReview/{id}")
	public List<SubAltKategoriSeo> getSubAltKategoriSeo(@PathVariable("id")int id) {
		return administrationService.getSubAltKategoriSeoList(id);
	}
	
	

	@PostMapping(value="/deleteAltKategoriSeo/{altId}/{id}")
	public void deleteAltKategoriSeo(@PathVariable("altId") int altId,@PathVariable("id") int id) {
		administrationService.deleteAltKategoriSeo(id);
		int length = administrationService.getAltKategoriSeoList(altId).size();
		if(length==0) {
			AltKategori altKategori = kategoriService.getAltKategori(altId);
			altKategori.setSeoAvaliable(false);
			kategoriService.saveAltKategori(altKategori);
		}
	}	
	
	@PostMapping(value="/deleteSubAltKategoriSeo/{subId}/{id}")
	public void deleteSubAltKategoriSeo(@PathVariable("subId") int subaltId,@PathVariable("id") int id) {
		administrationService.deleteSubAltKategoriSeo(id);
		int length = administrationService.getSubAltKategoriSeoList(subaltId).size();
		if(length==0) {
			SubAltKategori subaltKategori = kategoriService.getSubAltKategori(subaltId);
			subaltKategori.setSeoAvaliable(false);
			kategoriService.saveSubKategori(subaltKategori);
		}
	}
	
	
	@GetMapping(value="/seoUpdate/{id}")
	public Sube getSube(@PathVariable("id") int id) {
		return administrationService.getSube(id);		
	}
	
	@Autowired
	private FirmaService firmaService;
	@GetMapping(value="/getSatisNoktasi/{id}")
	public List<Sube> getSatisNoktasiForFirma(@PathVariable("id")int id){
		return administrationService.getSatisNoktasiForFirma(id);
	}
	
	@GetMapping(value="/getUrunList")
	public List<Urun> geturunList(){
		return administrationService.getAllUrun();
	}

	

	
}
