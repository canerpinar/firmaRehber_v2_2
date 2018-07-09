package com.firmaRehber.controller;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.firmaRehber.entity.AltKategori;
import com.firmaRehber.entity.Firma;
import com.firmaRehber.entity.Kampanya;
import com.firmaRehber.entity.Kategori;
import com.firmaRehber.entity.Message;
import com.firmaRehber.entity.Seo;
import com.firmaRehber.entity.SeoContent;
import com.firmaRehber.entity.SubAltKategori;
import com.firmaRehber.entity.Sube;
import com.firmaRehber.entity.SubeKampanya;
import com.firmaRehber.entity.Urun;
import com.firmaRehber.entity.User;
import com.firmaRehber.entity.UserAuthenticaion;
import com.firmaRehber.entity.UserRole;
import com.firmaRehber.service.FirmaService;
import com.firmaRehber.service.UploadFileService;
import com.firmaRehber.service.UserService;
import com.firmaRehber.service.WebAdministrationService;

@Controller
@RequestMapping("/firma/admin")
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class FirmaAdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FirmaService firmaService;
	
	@Autowired
	private UploadFileService fileService;
	
	@Autowired
	private ServletContext context;
	
	Firma firma;
	
	@Autowired
	private WebAdministrationService administrationService;

	
	@RequestMapping("/companySuccess")
	public ModelAndView isSuccessSaveCompany(@RequestParam("username") String email,@RequestParam("password")String password,HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView();
		User user = userService.getFirmaUserIsAuthentication(email, password);
		model.addObject("message", email.concat("------:-------" + password));

		
		
		if(user==null) {
			model.addObject("message", "Lütfen üyeliğinizi size gönderdiğimiz email ile aktif ediniz.");
			model.setViewName("/firma/company_success_fail");
		}else {
			Firma firma = firmaService.getFirma(email);
			firma.setFirmaActiveStatus(true);
			firmaService.saveFirma(firma);		
			model.setViewName("/firma/company_success_fail");
			model.addObject("message", "Kaydınız başarıyla aktif edilmiştir.");
			System.out.println("Authentication true");
		}	 	
		return model;
	}
	

	@RequestMapping("/")
	public String getFirmaAdminLoadPage(Model model,HttpServletResponse response) {
		
		model.addAttribute("message_", "Firma admin sayfası");
	      /*
	      User user=new User();
	      user.setUsername("cnr_p_10@hotmail.com");
	      user.setPassword("06");
	      
	      UserDetails aut = userService.loadUserByUsername(user.getUsername());
	      */
	      SecurityContext context = SecurityContextHolder.getContext();
	      /*
			Authentication auth =
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), aut.getAuthorities());
				context.setAuthentication(auth);
				*/
	      model.addAttribute("message", "You are logged in as " + context.getAuthentication().getName());
	      firma = firmaService.getFirma(context.getAuthentication().getName());
	      model.addAttribute("firma", firma);
	      List<Urun> urunListForFirma = administrationService.getAllUrunForFirma(firma.getId());
	      model.addAttribute("urunler", urunListForFirma);
	      model.addAttribute("mesajCount", administrationService.getOkunmamisMessageCount(firma.getId()));
	      
	      if(!firma.isFirmaActiveStatus()) {
	    	  return "/firma/company_success_fail";
	      }
			Cookie cookie = new Cookie("firma_id", String.valueOf(firma.getId()));
			//cookie.setPath("firma/admin/urun-ekle.html");
			response.addCookie(cookie);
	      urunListForFirma.forEach(urun->{
	    	  System.out.println(urun.getUrunAd());
	      });
	      return "/firma/company-profile";
	}
	
	@Autowired
	private SiteGenelController genelController;
	
	@RequestMapping(value="/allKategori",method=RequestMethod.GET)
	@ResponseBody
	public List<Kategori> getAllKategori(){
		return genelController.getAllFirma();
	}
	
	@RequestMapping(value="/getKategori/{kategoriAd}",method=RequestMethod.GET)
	@ResponseBody
	public List<Kategori> getKategori(@PathVariable("kategoriAd")String kategoriAd){
		return genelController.getKategoriWithName(kategoriAd);
	}
	
	@RequestMapping(value="/getAltKategori/{kategoriAd}",method=RequestMethod.GET)
	@ResponseBody
	public List<AltKategori> getAltKategori(@PathVariable("kategoriAd")String kategoriAd){
		return genelController.getAltKategoriWithName(kategoriAd);
	}
	
	@RequestMapping(value="/getSubAltKategori/{kategoriAd}",method=RequestMethod.GET)
	@ResponseBody
	public List<SubAltKategori> getSubAltKategori(@PathVariable("kategoriAd")String kategoriAd){
		return genelController.getSubAltKategoriWithName(kategoriAd);
	}
	
	@RequestMapping(value="/urun-ekle.html",method=RequestMethod.GET)
	public ModelAndView getUrunEkle(){
		ModelAndView model = new ModelAndView("/firma/urun-ekle");
		model.addObject("urun", new Urun());
		List<Urun> urunlerList = administrationService.getAllUrunForFirma(firma.getId());
		model.addObject("firma", firma);
		model.addObject("listUrunler", urunlerList);
		System.out.println("firma id" + firma.getId());
		return model;
	}
	
	@RequestMapping(value="/urunEkle",method=RequestMethod.POST)
	public void saveUrunEkle(@ModelAttribute("urun")Urun urun,@RequestParam("urun_image") MultipartFile files[],HttpServletResponse response) throws IOException{
		
		System.out.println("sahip id" + firma.getId());
		System.out.println("kat id" + urun.getKatagoriId());
		System.out.println("alt kat id" + urun.getAltKatagoriId());
		System.out.println("alt kat id" + urun.getSubKategoriId());
		urun.setImage(files[0].getOriginalFilename());
		urun.setImageOne(files[1].getOriginalFilename());
		urun.setImageTwo(files[2].getOriginalFilename());
		urun.setImageThree(files[3].getOriginalFilename());
		urun.setUrunSahibiFirma(firma.getId());
		urun.setPidKod("PID"+urun.getPidKod());
		String urunLink = "/" + urun.getUrunAd().replace(" ", "-")+"-"+urun.getPidKod();
		urunLink=Normalizer.normalize(urunLink.toLowerCase(), Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		urunLink=pattern.matcher(urunLink).replaceAll("");
		urun.setUrunLink(urunLink);
		urun.setImagePath(firma.getEmail()+"/"+urun.getUrunAd());

		File directory = new File(context.getRealPath("")+firma.getEmail()+"/"+urun.getUrunAd());
		if(!directory.exists())
			directory.mkdirs();
		for(MultipartFile file :files) {
			fileService.saveFileForSlider(file, directory.toString()+"/");	
		}
			
		Seo seo = new Seo();
		seo.setPageName(urunLink);
		List<SeoContent> listSeoContent = new ArrayList<>();
		SeoContent seoContent = new SeoContent();
		seoContent.setContent(urun.getUrunHakkinda());
		seoContent.setMetaName("content");
		listSeoContent.add(seoContent);
		seo.setSeoContentList(listSeoContent);
		seoContent.setSeo(seo);
		administrationService.saveSeoForUrun(seo);
		
		//System.out.println(file.getOriginalFilename());
		
		administrationService.saveUrun(urun);
		Firma firma_ = firmaService.getFirma(firma.getEmail());
		firma_.setFirmaControl(false);
		firmaService.saveFirma(firma_);
		
		
		response.sendRedirect("/firma/admin/");
	}
	
	@RequestMapping(value="/sube-satis-noktasi.html",method=RequestMethod.GET)
	public ModelAndView getSubeSatisNoktasiEkle(){
		ModelAndView model = new ModelAndView("/firma/sube-satis-noktasi");
		model.addObject("subeList", administrationService.getSubeForFirma(this.firma.getId()));
		model.addObject("sube", new Sube());
		model.addObject("firma", firma);
		return model;
	}
	
	@RequestMapping(value="/kampanyalar.html",method=RequestMethod.GET)
	public ModelAndView getKampanyalar(){
		ModelAndView model = new ModelAndView("/firma/kampanyalar");
		model.addObject("kampanya", new Kampanya());
		model.addObject("kampanyalar", administrationService.getKampanyaForFirma(firma.getId()));
		model.addObject("firma", firma);
		return model;
	}
	
	@RequestMapping(value="/referanslar.html",method=RequestMethod.GET)
	public ModelAndView getReferanslar(){
		ModelAndView model = new ModelAndView("/firma/referanslar");
		model.addObject("firma", firma);
		return model;
	}
	
	@RequestMapping(value="/istatistik.html",method=RequestMethod.GET)
	public ModelAndView getIstatistik(){
		ModelAndView model = new ModelAndView("/firma/istatistik");
		model.addObject("firma", firma);
		return model;
	}
	
	@RequestMapping(value="/mesajlar.html",method=RequestMethod.GET)
	public ModelAndView getMesaj(){
		ModelAndView model = new ModelAndView("/firma/mesajlar");
		List<Object[]> messageList = administrationService.getAllMessageForFirma(firma.getId());
		
		List<Integer> okunmamisMesajCount = new ArrayList<>();
		List<List<Message>> allMessageList = new ArrayList<>();
		//Map<List<Message>,Boolean> allMessageList = new HashedMap();
		System.out.println(firma.getId());
		System.out.println(messageList.size());
		model.addObject("messageList", messageList);
		
		for(Object[] message : messageList){
			
			System.out.println("------- ilk deger :"+message[0]);
			System.out.println("------- ikinci deger :"+message[1]);
			okunmamisMesajCount.add(administrationService.getMessageOkunmamis(message[0].toString()).size());
			for(int sayac : okunmamisMesajCount){
				System.out.println("----"+sayac);
			}
			allMessageList.add(administrationService.getAllMessageFromFirmaForList(Integer.parseInt(message[2].toString()),firma.getId()));
			System.out.println(message.toString());
		}
		model.addObject("allMessageForList", allMessageList);
		model.addObject("okunmamisMesaj", okunmamisMesajCount);
		model.addObject("firma", firma);
		return model;
	}
	
	@RequestMapping(value="/iletisim.html",method=RequestMethod.GET)
	public ModelAndView getIletisim(){
		ModelAndView model = new ModelAndView("/firma/iletisim");
		model.addObject("firma", firma);
		return model;
	}
	
	@RequestMapping(value="/fileUploadBgChange",method=RequestMethod.POST)
	@ResponseBody
	public void postFileUploadBgChange(@RequestParam("upload-file")MultipartFile file,@RequestParam("username")String username,HttpServletResponse response) throws IOException {
		System.out.println(file.getOriginalFilename());
		System.out.println(username);
		
		Firma firma = firmaService.getFirma(username);
		firma.setFirmaBackgroundImage(file.getOriginalFilename());
		firmaService.saveFirma(firma);		
		fileService.saveFileForSlider(file, context.getRealPath("")+username+"/bg_image/");
		response.sendRedirect("/firma/admin/");
	}
	
	@RequestMapping("/updateFirmaImage")
	@ResponseBody
	public void updateFirmaImage(@RequestParam("imageUploadForFirma")MultipartFile file,@RequestParam("username")String username,HttpServletResponse response) throws IOException{
		
		
		Firma firma = firmaService.getFirma(username);
		firma.setFirmaImage(file.getOriginalFilename());
		firmaService.saveFirma(firma);
		fileService.saveFileForSlider(file, context.getRealPath("")+username+"/");
		response.sendRedirect("/firma/admin/");		
	}
	
	@RequestMapping("/updateFirmaContact")
	@ResponseBody
	public void updateFirmaContact(@ModelAttribute("firma")Firma firma_,HttpServletResponse response) throws IOException{

		Firma _firma = firmaService.getFirma(this.firma.getEmail());
		_firma.setFirmaName(firma_.getFirmaName());
		_firma.setFirmaOwner(firma_.getFirmaOwner());
		_firma.setHaftasonustatus(firma_.isHaftasonustatus());

		_firma.setWebsite(firma_.getWebsite());
		firmaService.saveFirma(_firma);

		response.sendRedirect("/firma/admin/");		
	}
	
	@RequestMapping("/subeSave")
	@ResponseBody
	public void saveSube(@ModelAttribute("sube") Sube sube,@RequestParam("subeSatisStatus")String subeStatus,HttpServletResponse response) throws IOException {
		Firma firma=firmaService.getFirma(this.firma.getEmail());
		sube.setFirma(firma);
		if(subeStatus.equals("sube")){
			
		}else if(subeStatus.equals("satisNoktasi")) sube.setSatisVarMi(true);
		
		System.out.println(sube.getAd());
		System.out.println(sube.getEmail());
		administrationService.saveSube(sube);
		response.sendRedirect("../../firma/admin/sube-satis-noktasi.html");
		
	}
	
	@RequestMapping("/deleteSube/{id}")
	@ResponseBody
	public void deleteSube(@PathVariable("id") int id,HttpServletResponse response) throws IOException {
		administrationService.deleteSube(id);
		response.sendRedirect("/firma/admin/sube-satis-noktasi.html");
		
	}
	
	@RequestMapping("/updateSube/{id}")
	@ResponseBody
	public Sube getSube(@PathVariable("id") int id) {
		return genelController.getSube(id);
	}
	
	@RequestMapping("/updateSube")
	@ResponseBody
	public void updateSaveSube(@ModelAttribute("sube") Sube sube,HttpServletResponse response) {
		System.out.println(sube.getAd());
		System.out.println(sube.getEmail());		
	}
	
	@RequestMapping("/getAllSatisNoktasi/{id}")
	@ResponseBody
	public List<Sube> getAllSatisNoktasi(@PathVariable("id") int id) {
		return genelController.getSatisNoktasiForFirma(id);
	}
	
	@RequestMapping("/getAllUrunForFirma/{firmaId}")
	@ResponseBody
	public List<Urun> getUrunForFirma(@PathVariable("firmaId")Integer firmaId){
		return genelController.getUrunListForFirma(firmaId);
	}
	
	@RequestMapping(value="/kampanyaEkle",method=RequestMethod.POST)
	@ResponseBody
	public void saveKampanya(@ModelAttribute("kampanya") Kampanya kampanya,HttpServletResponse response) throws IOException {		
		administrationService.saveKampanya(kampanya);
		Urun urun = administrationService.getUrun(kampanya.getUrunId());
		System.out.println("----kampanya id :"+ kampanya.getId());
		if(kampanya.getKampanyaTuru()==1) {
			urun.setKampanyaStatus(true);
			urun.setKampanyaliFiyat(kampanya.getKampanyaFiyat());
			urun.setKampanyaOran(kampanya.getKampanyaOran());
			urun.setKampanya(kampanya);
			System.out.println("kampanya turu 1 başarılı");			
		}else if(kampanya.getKampanyaTuru()==2) {
			urun.setKampanyaStatus(true);			
			urun.setKampanyaliFiyat(kampanya.getKampanyaFiyat());
			urun.setKampanya(kampanya);
			System.out.println("kampanya turu 2 başarılı");
			System.out.println("kampanya id :"+ kampanya.getId());
		}
		System.out.println("kampanya turu :" + kampanya.getKampanyaTuru());		
		administrationService.saveUrun(urun);		
		response.sendRedirect("/firma/admin/kampanyalar.html");
	}
	
	@RequestMapping(value="/ziyaretKampanyaEkle",method=RequestMethod.POST)
	@ResponseBody
	public void saveZiyaretKampanya(@RequestParam("subeForKampanya")String[] subeler,@ModelAttribute("kampanya")Kampanya kampanya
			,HttpServletResponse response){
						
		System.out.println("kampanya ad : " + kampanya.getKampanyaAd());
		List<SubeKampanya> subeList = new ArrayList<SubeKampanya>();
		for(String sube :subeler){
			Sube sube_ = administrationService.getSube(Integer.parseInt(sube));
			SubeKampanya subeKampanya = new SubeKampanya();
			subeKampanya.setKampanya(kampanya);
			subeKampanya.setSube(sube_);
			subeList.add(subeKampanya);
			System.out.println(" sube id : "+sube);
		}
		kampanya.setSubeKampanyaList(subeList);
		administrationService.saveKampanya(kampanya);	
	}
	
	@RequestMapping(value="/ozelGunKampanyaEkle",method=RequestMethod.POST)
	@ResponseBody
	public void saveOzelGunKampanyasi(@RequestParam("subeForKampanya")String[] subeler,@ModelAttribute("kampanya")Kampanya kampanya
			,HttpServletResponse response){
		System.out.println("kampanya ad : " + kampanya.getKampanyaAd());
		List<SubeKampanya> subeList = new ArrayList<SubeKampanya>();
		for(String sube :subeler){
			Sube sube_ = administrationService.getSube(Integer.parseInt(sube));
			SubeKampanya subeKampanya = new SubeKampanya();
			subeKampanya.setKampanya(kampanya);
			subeKampanya.setSube(sube_);
			subeList.add(subeKampanya);
			System.out.println(" sube id : "+sube);
		}
		kampanya.setSubeKampanyaList(subeList);
		administrationService.saveKampanya(kampanya);
	}
	
	@RequestMapping(value="/getUrun/{id}")
	public @ResponseBody Urun getUrun(@PathVariable("id")int id){
		return genelController.getUrun(id);
	}
	
	@RequestMapping(value="/urunUpdate",method=RequestMethod.POST)
	@ResponseBody
	public void urunUpdate(@ModelAttribute("urun")Urun urun,@RequestParam("images")MultipartFile[] images,HttpServletResponse response) throws IOException{
		Urun getUrun = administrationService.getUrun(urun.getId());
		
		File directory = new File(context.getRealPath("")+getUrun.getImagePath());
		if(!images[0].isEmpty()){
			System.out.println("image 1 ok");
			File file = new File(context.getRealPath("")+getUrun.getImagePath()+"/"+
			getUrun.getImage());
			
			file.delete();
			fileService.saveFileForSlider(images[0], directory.toString()+"/");
			
			
			getUrun.setImage(images[0].getOriginalFilename());
		}if(!images[1].isEmpty()){
			System.out.println("image 2 ok");
			fileService.saveFileForSlider(images[1], directory.toString()+"/");
			getUrun.setImageOne(images[1].getOriginalFilename());
		}if(!images[2].isEmpty()){
			System.out.println("image 3 ok");
			fileService.saveFileForSlider(images[2], directory.toString()+"/");
			getUrun.setImageTwo(images[2].getOriginalFilename());
		}if(!images[3].isEmpty()){
			System.out.println("image 4 ok");
			fileService.saveFileForSlider(images[3], directory.toString()+"/");
			getUrun.setImageThree(images[3].getOriginalFilename());
		}
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
		getUrun.setUrunControl(false);
		getUrun.setAltKategoriAd(urun.getAltKategoriAd());
		getUrun.setAltKatagoriId(urun.getAltKatagoriId());

		getUrun.setSubKategoriAd(urun.getSubKategoriAd());
		getUrun.setSubKategoriId(urun.getSubKategoriId());
		getUrun.setOdemeKrediKarti(urun.isOdemeKrediKarti());
		
		getUrun.setOdemeHavale(urun.isOdemeHavale());
		getUrun.setOdemeKapida(urun.isOdemeKapida());
		//getUrun.setUrunLink(urun.getUrunLink());
		getUrun.setMarka(urun.getMarka());
		getUrun.setUrunAd(urun.getUrunAd());
		getUrun.setUrunFiyat(urun.getUrunFiyat());
		
		Seo seo = administrationService.getSeoForUrun(getUrun.getUrunLink());
		
		String urunLink = "/" + urun.getUrunAd().replace(" ", "-")+"-"+urun.getPidKod();
		urunLink=Normalizer.normalize(urunLink.toLowerCase(), Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		urunLink=pattern.matcher(urunLink).replaceAll("");		
		seo.setPageName(urunLink);
		administrationService.saveSeoForUrun(seo);
		administrationService.saveUrun(getUrun);
		
		/*
		response.sendRedirect("/firma/admin/");
		*/	
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
		SecurityContext context=SecurityContextHolder.getContext();
				
		Message message = new Message();
		message.setGonderenId(Integer.parseInt(allRequest.get("mesajKimden")));
		Firma firma_=administrationService.getFirmaDetay(context.getAuthentication().getName());
		message.setGonderenUyemi(true);
		
		message.setMesajKimden(firma_.getFirmaOwner());
		message.setMesajContent(firma_.getFirmaName()+" :"+allRequest.get("answer"));
		message.setOkunmaDurum(false);
		System.out.println("mesaj kime ------" + allRequest.get("mesajKime"));
		if(allRequest.get("mesajKime").equals("Admin")){
			System.out.println("mesaj admine------");
			message.setMesajKimeId(-1);
			message.setMesajContent("Admin :"+allRequest.get("answer"));
			 message.setGonderenId(firma_.getId());
			message.setMesajSahipLink("");
		}else{
			message.setMesajKimeId(Integer.parseInt(allRequest.get("mesajAlanId")));
			message.setMesajSahipLink("/firmaDetay"+allRequest.get("mesajKime"));
		}
		administrationService.sentMessage(message);
		//System.out.println("-----content :" + allRequest);
	}

	

}
