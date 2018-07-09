package com.firmaRehber.controller;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.firmaRehber.entity.Firma;
import com.firmaRehber.entity.Kampanya;
import com.firmaRehber.entity.Message;
import com.firmaRehber.entity.Seo;
import com.firmaRehber.entity.Sube;
import com.firmaRehber.entity.Urun;
import com.firmaRehber.entity.User;
import com.firmaRehber.service.KategoriService;
import com.firmaRehber.service.UserService;
import com.firmaRehber.service.WebAdministrationService;

@Controller
@RequestMapping("/admin/firma")
public class FirmaController {

	@Autowired
	private WebAdministrationService administrationService;
	
	@Autowired
	private KategoriService kategoriService;
	
	
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
		List<Kampanya> kampanyalar = administrationService.getKampanyaForFirma(firma.getId());
		System.out.println("kampanya length : "+ kampanyalar.size());
		model.addObject("kampanyalar", kampanyalar);
		kampanyalar.forEach(kampanya->{
			System.out.println("-------- kampanya id" + kampanya.getId());
		});
		
		model.addObject("urunler", administrationService.getAllUrunForFirma(Integer.parseInt(id)));
		
		return model;		
	}
	
	@RequestMapping("/urunEdit/{id}")//urun id
	public ModelAndView getUrunForEdit(@PathVariable("id")int id){
		ModelAndView model=new ModelAndView("admin/urunEdit");
		model.addObject("kategoriler",kategoriService.getAllKategori());
		Urun urun = administrationService.getUrun(id);
		model.addObject("urun", urun);
		
		Seo seo = administrationService.getSeoForUrun(urun.getUrunLink());
		if(seo != null)model.addObject("seo", seo);
		else model.addObject("seo", new Seo());
		
		
		//model.addObject("seo_", administrationService.getSeoForUrun(urun.getUrunLink()));
		return model;		
	}
	
	@RequestMapping(value="/urunUpdate",method=RequestMethod.POST)
	@ResponseBody
	public void urunUpdate(@ModelAttribute("urun")Urun urun,HttpServletResponse response) throws IOException{
		Urun getUrun = administrationService.getUrun(urun.getId());
		/*
		System.out.println("urun id " +urun.getId());
		System.out.println("urun kat id " +urun.getKatagoriId());
		System.out.println("urun kat ad " +urun.getKategoriAd());
		
		
		
		System.out.println("-----alt kat urun alt kat ad " +urun.getAltKategoriAd());
		System.out.println("-----alt kat urun alt kat id " +urun.getAltKatagoriId());
		
		System.out.println("---ikinci alt kat ad " +urun.getSubKategoriAd());
		System.out.println("---ikinci alt kat id " +urun.getSubKategoriId());
		
		System.out.println("urun ad "+ urun.getUrunAd());
		System.out.println("urun marka "+ urun.getMarka());
		*/
		
		getUrun.setKategoriAd(urun.getKategoriAd());
		getUrun.setKatagoriId(urun.getKatagoriId());
		getUrun.setUrunControl(true);
		getUrun.setAltKategoriAd(urun.getAltKategoriAd());
		getUrun.setAltKatagoriId(urun.getAltKatagoriId());

		getUrun.setSubKategoriAd(urun.getSubKategoriAd());
		getUrun.setSubKategoriId(urun.getSubKategoriId());
		getUrun.setOdemeKrediKarti(urun.isOdemeKrediKarti());
		
		getUrun.setOdemeHavale(urun.isOdemeHavale());
		getUrun.setOdemeKapida(urun.isOdemeKapida());
		getUrun.setUrunLink(urun.getUrunLink());
		getUrun.setMarka(urun.getMarka());
		getUrun.setUrunAd(urun.getUrunAd());
		getUrun.setUrunFiyat(urun.getUrunFiyat());
		
		
		Seo seo = administrationService.getSeoForUrun(getUrun.getUrunLink());
		
		String urunLink = "/" + getUrun.getUrunAd().replace(" ", "-")+"-"+urun.getPidKod();
		urunLink=Normalizer.normalize(urunLink.toLowerCase(), Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		urunLink=pattern.matcher(urunLink).replaceAll("");		
		seo.setPageName(urunLink);
		administrationService.saveSeoForUrun(seo);
		
		

		administrationService.saveUrun(getUrun);
		response.sendRedirect("/admin/firma/"+String.valueOf(urun.getUrunSahibiFirma()));
		
	}
	
	@RequestMapping(value="/kampanya/{id}")
	public ModelAndView getKampanyaForUpdate(@PathVariable("id")int id)
	{
		ModelAndView model = new ModelAndView("admin/kampanya");
		Kampanya kampanya = administrationService.getKampanya(id);
		Urun urun = administrationService.getUrun(kampanya.getUrunId());
		model.addObject("urun", urun);
		model.addObject("kampanya", kampanya);
		return model;
	}
	

	@RequestMapping(value="/kampanyaUpdate",method=RequestMethod.POST)
	@ResponseBody
	public void kampanyaUpdate(@ModelAttribute("kampanya")Kampanya kampanya,HttpServletResponse response,
			HttpServletRequest request,@RequestParam("firma_id")String firma_id) throws IOException{
		Kampanya kampanya_ = administrationService.getKampanya(kampanya.getId());
		kampanya_.setKampanyaAd(kampanya.getKampanyaAd());
		kampanya_.setKampanyaControl(true);
		administrationService.saveKampanya(kampanya_);
		System.out.println("kampanya güncellendi");
		response.sendRedirect("/admin/firma/"+firma_id);
	}
	
	@RequestMapping(value="readMessage/{user}",method=RequestMethod.POST)
	public @ResponseBody void readAllMessageForFirma(@PathVariable("user")String user){
		List<Message> messageList = administrationService.getMessageOkunmamis(user);
		messageList.forEach(message->{
			Message message_ = administrationService.getMessageForOkunmamis(message);
			message_.setOkunmaDurum(true);
			administrationService.messageUpdate(message_);
		});
		
	}
	
	@RequestMapping(value="sendMessageAnswer/{user}",method=RequestMethod.POST)
	public @ResponseBody void sendMessageAnswers(@PathVariable("user")String user,
			@RequestParam Map<String,String> allRequest){
			Message message = new Message();
			message.setGonderenId(Integer.parseInt(allRequest.get("mesajKimden")));

			message.setGonderenUyemi(true);		
			message.setMesajKimden("Admin");
			message.setMesajContent("Admin :"+" :"+allRequest.get("answer"));
			message.setOkunmaDurum(false);
			System.out.println("mesaj kime ------" + allRequest.get("mesajKime"));
			message.setMesajKimeId(Integer.parseInt(allRequest.get("mesajAlanId")));
			message.setMesajSahipLink("/firmaDetay"+allRequest.get("mesajKime"));
		
		administrationService.sentMessage(message);
		//System.out.println("-----content :" + allRequest);
	}


	
	
	
}
