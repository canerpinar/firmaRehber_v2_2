package com.firmaRehber.controller;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.firmaRehber.entity.AltKategori;
import com.firmaRehber.entity.Firma;
import com.firmaRehber.entity.Images;
import com.firmaRehber.entity.Kategori;
import com.firmaRehber.entity.Reklam;
import com.firmaRehber.entity.SubAltKategori;
import com.firmaRehber.entity.User;
import com.firmaRehber.entity.UserRole;
import com.firmaRehber.service.KategoriService;
import com.firmaRehber.service.RoleService;
import com.firmaRehber.service.UploadFileService;
import com.firmaRehber.service.UserService;
import com.firmaRehber.service.WebAdministrationService;

@Controller
public class UrlResolver {


	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private KategoriService kategoriService;
	
	@Autowired
	private WebAdministrationService administrationService;
	
	
	
	
	List<AltKategori> listAltKategori = new ArrayList<>();
	
	
	public List<AltKategori> getListAltKategori() {
		return listAltKategori;
	}

	public void setListAltKategori(List<AltKategori> listAltKategori) {
		this.listAltKategori = listAltKategori;
	}
	
	
	List<SubAltKategori> listSubAltKategori = new ArrayList<>();

	
	@RequestMapping(value="admin/mesajlar")
	public ModelAndView mesajPage(){
		ModelAndView model = new ModelAndView("admin/mesaj");

		return model;
		
		
	}
	
	
	@RequestMapping("/saveUser")
	@ResponseBody
	public void saveUser(){
		User user = new User();
		user.setUsername("caner");
		user.setPassword(passwordEncoder.encode("caner"));
		
		userService.saveUser(user);
		
		UserRole userRole = new UserRole();
		userRole.setRoleUsername("caner");
		userRole.setRole("ROLE_ADMIN");
		
		roleService.saveRole(userRole);
	}
	
	@RequestMapping("/admin/kampanyalar")
	public String pageKampanyalar(){
		return "admin/kampanyalar";
	}
	
	@RequestMapping("/admin/hizmetler")
	public String pageHizmetler(){
		return "admin/hizmetler";
	}
	
	
	@RequestMapping("/admin")
	public String loginpage(Model model){
	      SecurityContext context = SecurityContextHolder.getContext();	      
	      model.addAttribute("message", "You are logged in as " + context.getAuthentication().getName());
		return "admin/index";
	}
	@RequestMapping(value={"/","index.html"})
	public ModelAndView indexPage_(){
		ModelAndView model = new ModelAndView("index");
		List<Kategori> listKategori = (List<Kategori>) kategoriService.getAllKategori();
		List<Images> listImagesForSlider = uploadFileService.getAllImagesForSlider();
		model.addObject("images", listImagesForSlider);
		model.addObject("kategoriler", listKategori);
		return model;
		
		
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	private SiteGenelController siteGenelController;	
	
	@RequestMapping(value="/admin/siteGenel/kategoriEdit/{id}")
	public ModelAndView updateKategoriForm(@PathVariable("id")String id){
		Kategori kategori = siteGenelController.getKategori(id);
		System.out.println(kategori.getKategoriAd());
		ModelAndView model = new ModelAndView("admin/kategoriUpdate");
		model.addObject("kategori", kategori);
		return model;
	}
	
	@RequestMapping(value="admin/siteGenel/updateKategori")
	@ResponseBody
	public void updateKategori(@ModelAttribute("kategori") Kategori kategori){
		Kategori newKategori = kategoriService.getAnaKategori(kategori.getId());
		newKategori.setKategoriAd(kategori.getKategoriAd());
		List<AltKategori> altKategoriList = new ArrayList<AltKategori>();
		for (AltKategori altKategori : kategori.getAltKategori()) {
			AltKategori newAltKategori = altKategori;
			newAltKategori.setAltKategoriAd(altKategori.getAltKategoriAd());
			newAltKategori.setKategori(newKategori);
			altKategoriList.add(newAltKategori);
			List<SubAltKategori> subkategoriList = new ArrayList<SubAltKategori>();			
			for (SubAltKategori subKategori : altKategori.getSubaltKategori()) {
				SubAltKategori newsubAlt = subKategori;
				newsubAlt.setSubAltKategoriAd(subKategori.getSubAltKategoriAd());
				newsubAlt.setAltKategori(newAltKategori);
				newsubAlt.setKategori(newKategori);
				subkategoriList.add(newsubAlt);							
			}
			newAltKategori.setSubaltKategori(subkategoriList);
			newKategori.setAltKategori(altKategoriList);			
		}		
		kategoriService.updateKategori(newKategori);
		
	}
	
	
	@RequestMapping(value="sliderDelete",method=RequestMethod.POST)
	public void deleteImageForSlider(@RequestParam("imageName") String[] imageName,@RequestParam("Deneme") String[] imageId,HttpServletResponse response){
		for (int i = 0; i < imageId.length; i++) {
			System.out.println(imageId[i]);
			try {
				Files.deleteIfExists(Paths.get(context.getRealPath("")+"imagesForSlider\\"+imageName[i]));
				administrationService.deleteImageForSlider(Integer.parseInt(imageId[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			response.sendRedirect("/admin/siteGenel");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	//admin sayfası için genel update işlemleri
	@RequestMapping("/admin/siteGenel")
	public ModelAndView adminGenel(){
		ModelAndView model = new ModelAndView("admin/anasayfa");		
		model.addObject("kategoriler", new Kategori());

		List<Kategori> listKategori = (List<Kategori>) kategoriService.getAllKategori();
		
		model.addObject("kategoriList", listKategori);
		model.addObject("altKategori", new AltKategori());
		model.addObject("subAltKategori", new SubAltKategori());
		model.addObject("images", new Images());
		model.addObject("reklam", new Reklam());
		model.addObject("sliderList", administrationService.getAllImagesForSlider());

		model.addObject("reklamList", administrationService.getAllReklam());

		
	
		listKategori.forEach(s->{
			System.out.println(s.getKategoriAd());
			List<AltKategori> altKategoriList = s.getAltKategori();
				altKategoriList.forEach(a->{
					AltKategori altKategori = new AltKategori();

					altKategori.setId(a.getId());
					altKategori.setAltKategoriAd(a.getAltKategoriAd());
					listAltKategori.add(altKategori);
					for (SubAltKategori subAltKategori : a.getSubaltKategori()) {
						System.out.println(subAltKategori.getSubAltKategoriAd());
					}
					System.out.println("--".concat(a.getAltKategoriAd()));					
				});			
		});

		model.addObject("listAltKategori", listAltKategori);
		return model;
	}
	
	@RequestMapping(value="/admin/siteGenel/addKategori",method = RequestMethod.POST)
	@ResponseBody
	public Kategori addKat(@RequestBody String kategoriJson) throws JSONException{
			System.out.println(kategoriJson);
			
		try {
			
			JSONObject objectKategori = new JSONObject(kategoriJson);
			JSONArray objectArray = objectKategori.getJSONArray("altKategori");
			Kategori kategori = new Kategori();
			kategori.setKategoriAd(objectKategori.get("kategoriAd").toString());
			List<AltKategori> altKategoriList = new ArrayList<AltKategori>();
			
			
			for(int k = 0; k<objectArray.length();k++){
				JSONObject objectAltKategori = new JSONObject(objectArray.get(k).toString());
				AltKategori altKategori = new AltKategori();
				altKategori.setAltKategoriAd(objectAltKategori.getString("altKategoriAd"));
				altKategori.setKategori(kategori);
				List<SubAltKategori> subAltKategoriList = new ArrayList<SubAltKategori>();
				altKategoriList.add(altKategori);
				JSONArray _objectAltKategori = objectAltKategori.getJSONArray("subAltKat");
				System.out.println("-----"+objectAltKategori.getString("altKategoriAd")+"------");
				for(int t= 0; t<_objectAltKategori.length();t++){
					JSONObject __objectAltKategori = new JSONObject(_objectAltKategori.get(t).toString());
					SubAltKategori subAltKategori = new SubAltKategori();
					subAltKategori.setSubAltKategoriAd(__objectAltKategori.get("subAltKategoriAd").toString());
					subAltKategori.setAltKategori(altKategori);
					subAltKategori.setKategori(kategori);					
					subAltKategoriList.add(subAltKategori);
					 

				}
				altKategori.setSubaltKategori(subAltKategoriList);
				
			}
			kategori.setAltKategori(altKategoriList);
			System.out.println("----------"+kategoriJson+"---------");
			kategoriService.saveKategori(kategori);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println(kategori);
		
		/*
		System.out.println(altkat.getAltKategoriAd());
		System.out.println(subkat.getSubAltKategoriAd());
		*/
			return null;
	}

	//slider için resim ekleme
	@PostMapping("/addFiles")
	public @ResponseBody void saveFilesForSlider(@ModelAttribute("file") MultipartFile file,@ModelAttribute("images") Images images,HttpServletResponse response) throws IOException{		
		String uploadFilePath = context.getRealPath("")+"imagesForSlider\\";
		//System.out.println(uploadFilePath);
		images.setResimName(file.getOriginalFilename());
		System.out.println("images name" + images.getResimName());
		administrationService.saveImages(images);
		uploadFileService.saveFileForSlider(file, uploadFilePath);
		response.sendRedirect("/admin/siteGenel");
		
	}
	
	@PostMapping("/addReklam")
	public @ResponseBody void saveReklam(@ModelAttribute("reklam") Reklam reklam,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException{
		reklam.setReklamImage(file.getOriginalFilename());
		String uploadFilePath = context.getRealPath("")+"imagesForReklam\\";
		administrationService.saveReklam(reklam);
		uploadFileService.saveFileForSlider(file, uploadFilePath);
		response.sendRedirect("/admin/siteGenel");
	}
	
	@RequestMapping(value="/admin/siteGenel/addAltKategori",method = RequestMethod.POST)
	@ResponseBody
	public AltKategori addAltKat(@RequestBody String kategoriJson){
		System.out.println(kategoriJson);
		try {
			JSONArray object =new JSONArray(kategoriJson);
			JSONObject jsonObject = object.getJSONObject(0);
			
			AltKategori altKategori = new AltKategori();
			altKategori.setAltKategoriAd(jsonObject.getString("altKategoriAd"));
			Kategori kategori = kategoriService.getAnaKategori(Integer.parseInt(jsonObject.getString("kategoriId")));
			
			
			JSONArray subAlt = new JSONArray(jsonObject.get("subAlt").toString());
			List<SubAltKategori> subkategoriList = new ArrayList<SubAltKategori>();
			for(int s=0;s<subAlt.length();s++){
				SubAltKategori subAltKategori = new SubAltKategori();
				subAltKategori.setSubAltKategoriAd(subAlt.getJSONObject(s).getString("subkategoriad"));
				subAltKategori.setAltKategori(altKategori);
				subAltKategori.setKategori(kategori);
				subkategoriList.add(subAltKategori);
			}
			
			altKategori.setSubaltKategori(subkategoriList);
			altKategori.setKategori(kategori);
			
			kategoriService.saveAltKategori(altKategori);			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		

	}
	@RequestMapping(value="/admin/siteGenel/addSubAltKategori",method = RequestMethod.POST)
	@ResponseBody
	public SubAltKategori subAltKat(@RequestBody String kategoriJson){
		System.out.println(kategoriJson);
		int kategoriId,altKategoriId;
		
		try {
			JSONArray object =new JSONArray(kategoriJson);
			JSONObject jsonObject = object.getJSONObject(0);
			kategoriId = Integer.parseInt(jsonObject.getString("kategoriId"));
			altKategoriId = Integer.parseInt(jsonObject.getString("altKategoriId"));
			Kategori kategori = kategoriService.getAnaKategori(kategoriId);
			AltKategori altKategori = kategoriService.getAltKategori(altKategoriId);
			
			JSONArray subKategori = new JSONArray(jsonObject.get("subAlt").toString());
			
			for (int i = 0; i < subKategori.length(); i++) {
				SubAltKategori subAltKategori = new SubAltKategori();
				subAltKategori.setSubAltKategoriAd(subKategori.getJSONObject(i).getString("subAltKategoriAd"));
				subAltKategori.setAltKategori(altKategori);
				subAltKategori.setKategori(kategori);	
				altKategori.getSubaltKategori().add(subAltKategori);
			}			
			altKategori.setKategori(kategori);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		kategoriService.saveArraySubKategori(listSubAltKategori);
		return null;
	}
	
	@RequestMapping(value="company-sign.html")
	public ModelAndView firmaSignUp(){
		ModelAndView model = new ModelAndView("company-sign");
		model.addObject("firma", new Firma());
		return model;
	}
	@RequestMapping(value="/signupFirma",method=RequestMethod.POST)
	public String saveFirma(@ModelAttribute("firma") Firma firma,@RequestParam("resim") MultipartFile file,HttpServletResponse response){
		User user = new User();
		user.setUsername(firma.getEmail());
		user.setPassword(passwordEncoder.encode(firma.getPassword()));
		
		userService.saveUser(user);
		
		UserRole userRole = new UserRole();
		userRole.setRoleUsername(user.getUsername());
		userRole.setRole("ROLE_COMPANY");
		roleService.saveRole(userRole);
		firma.setFirmaImage(file.getOriginalFilename());
		File directory = new File(context.getRealPath("")+user.getUsername());
		if(!directory.exists()){
			directory.mkdir();
			File directory_ = new File(context.getRealPath("")+user.getUsername()+"/bg_image");
			directory_.mkdir();
		}
		uploadFileService.saveFileForSlider(file,directory.toString()+"\\");
		administrationService.saveFirma(firma);
		administrationService.sendEmailForSaveFirma(firma.getEmail(),user.getUsername(),user.getPassword());
		return "/firma/admin";
	}
	@RequestMapping(value="company-profile.html")
	public String companyProfile(){
		return "company-profile";
	}
	
}
