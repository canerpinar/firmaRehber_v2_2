package com.firmaRehber.service;

import java.util.List;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.firmaRehber.entity.AltKategoriSeo;
import com.firmaRehber.entity.Firma;
import com.firmaRehber.entity.Images;
import com.firmaRehber.entity.Kampanya;
import com.firmaRehber.entity.KategoriSeo;
import com.firmaRehber.entity.Message;
import com.firmaRehber.entity.Reklam;
import com.firmaRehber.entity.Seo;
import com.firmaRehber.entity.SubAltKategoriSeo;
import com.firmaRehber.entity.Sube;
import com.firmaRehber.entity.SubeKampanya;
import com.firmaRehber.entity.Urun;
import com.firmaRehber.repository.AltKategoriSeoRepository;
import com.firmaRehber.repository.FirmaRepository;
import com.firmaRehber.repository.ImagesRepository;
import com.firmaRehber.repository.KampanyaRepository;
import com.firmaRehber.repository.KategoriSeoRepository;
import com.firmaRehber.repository.MessageRepository;
import com.firmaRehber.repository.ReklamRepository;
import com.firmaRehber.repository.SeoContentRepository;
import com.firmaRehber.repository.SeoRepository;
import com.firmaRehber.repository.SubAltKategoriSeoRepository;
import com.firmaRehber.repository.SubeKampanyaRepository;
import com.firmaRehber.repository.SubeRepository;
import com.firmaRehber.repository.UrunRepository;

@Service
public class WebAdministrationService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private ReklamRepository reklamRepository;
	
	@Autowired
	private SeoRepository seoRepository;
	
	@Autowired
	private FirmaRepository firmaRepository;
	
	@Autowired
	private SubeRepository subeRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UrunRepository urunRepository;
	
	@Autowired
	private KategoriSeoRepository kategoriSeoRepository;
	
	@Autowired
	private AltKategoriSeoRepository altKategoriSeoRepository;
	
	@Autowired
	private SubAltKategoriSeoRepository subAltKategoriSeoRepository;
	
	@Autowired
	private KampanyaRepository kampanyaRepository;
	
	@Autowired
	private JavaMailSender javaMailSender; 
	
	@Autowired
	private SeoContentRepository seoContentRepository;
	
	@Autowired
	private SubeKampanyaRepository subeKampanyaRepository;
	
	public void saveImages(Images images) {
		imagesRepository.save(images);
	}
	
	public void saveReklam(Reklam reklam){
		reklamRepository.save(reklam);
	}
	
	public void updateReklam(Reklam reklam){
		reklamRepository.save(reklam);
	}
	
	public Reklam getReklam(int id){
		return reklamRepository.findOne(id);
	}
	
	public List<Reklam> getAllReklam(){
		return (List<Reklam>) reklamRepository.findAll();
	}
	
	public void deleteReklam(String id){
		reklamRepository.delete(Integer.parseInt(id));
	}
	
	public List<Seo> getAllSeo(){
		return (List<Seo>) seoRepository.findAll();
	}
	
	public List<Firma> getAllFirma(){
		return (List<Firma>) firmaRepository.findAll();
	}
	
	public Firma getFirma(String id) {
		return firmaRepository.findOne(Integer.parseInt(id));
	}
	
	public void sentMessage(Message message) {
		messageRepository.save(message);
	}
	
	public Urun getUrun(int urunId){
		return urunRepository.findOne(urunId);
	}
	
	public List<Urun> getAllUrun(){
		return (List<Urun>) urunRepository.findAll();
	}
	
	public List<Urun> getAllUrunForFirma(int firmaId){
		return urunRepository.getUrunForFirma(firmaId);
	}
	
	public void saveUrun(Urun urun){
		urunRepository.save(urun);
	}
	
	public List<Sube> getSubeForFirma(Integer firmaId){
		return subeRepository.getSubeForFirma(firmaId);
	}
	
	public Sube getSube(Integer firmaId){
		return subeRepository.findOne(firmaId);
	}
	
	
	
	public void deleteSube(int id){
		subeRepository.deleteSube(id);
	}
	

	public void saveSube(Sube sube) {
		subeRepository.save(sube);
	}
	

	public void saveFirma(Firma firma){
		firmaRepository.save(firma);
	}
	
	public List<Images> getAllImagesForSlider(){
		return (List<Images>) imagesRepository.findAll();
	}
	
	public void deleteImageForSlider(Integer imageId){
		imagesRepository.delete(imageId);
	}
	
	public void kategoriSeoSave(KategoriSeo kategoriSeo){
		kategoriSeoRepository.save(kategoriSeo);
	}
	
	public void altKategoriSeoSave(AltKategoriSeo altKategoriSeo){
		altKategoriSeoRepository.save(altKategoriSeo);
	}
	

	
	public void subAltKategoriSeoSave(SubAltKategoriSeo subAltKategoriSeo){
		subAltKategoriSeoRepository.save(subAltKategoriSeo);
	}
	
	
	public List<AltKategoriSeo> getAltKategoriSeoList(int altKategoriId){
		return altKategoriSeoRepository.getAltKategoriSeo(altKategoriId);
	}
	
	public List<SubAltKategoriSeo> getSubAltKategoriSeoList(int subAltKategoriId){
		return subAltKategoriSeoRepository.getSubAltKategoriSeo(subAltKategoriId);
	}
	
	public void deleteAltKategoriSeo(int id) {
		altKategoriSeoRepository.deleteAltKategoriSeo(id);
	}
	
	public void deleteSubAltKategoriSeo(int id) {
		subAltKategoriSeoRepository.deleteSubAltKategoriSeo(id);
	}
	
	public void sendEmailForSaveFirma(String email,String username,String password) {
		javaMailSender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
				messageHelper.setFrom("burak_turkmen_34_@hotmail.com");	
		
				messageHelper.setTo(email);
				messageHelper.setSubject("Hoşgeldiniz lütfen kaydınızı aktif ediniz");
				String sendText = "Linke tıkladığınızda işlem gerçekleşmiştir.<br/>"+
				"<a href=\"http://localhost:8080/firma/admin/companySuccess?username="+email+"&password="+password+"\">Buraya tıklayarak devam edebilirsiniz.</a>";
				messageHelper.setText("text/plain",sendText);				
			}
		});
	}
	
	public List<Sube> getSatisNoktasiForFirma(int id){
		return subeRepository.getSatisNoktasiForFirma(id);
	}
	
	public Urun getUrunWithLink(String link) {
		return urunRepository.getUrunWithLink(link);
	}
	
	public List<Urun> getAllUrunForFirma(Integer firmaId){
		return urunRepository.getUrunForFirma(firmaId);
	}
	
	public void saveKampanya(Kampanya kampanya) {
		kampanyaRepository.save(kampanya); 
	}
	
	public List<Firma> getFirmaWithUrunForKategori(String kategoriAd){
		return firmaRepository.getFirmaWithUrunForKategori(kategoriAd);
	}
	
	public List<Kampanya> getKampanyaForFirma(int firmaId){
		return kampanyaRepository.getKampanyaForFirma(firmaId);
	}
	
	public void saveSeoForUrun(Seo seo){
		seoRepository.save(seo);
	}
	
	public Seo getSeoForUrun(String urunLink){
		return seoRepository.getSeoWithUrunLink(urunLink);
	}
	
	public void deleteSeoContentWithId(int id){
		seoContentRepository.deleteSeoContentWithId(id);
	}
	public Kampanya getKampanya(int id)
	{
		return kampanyaRepository.findOne(id);
	}
	
	public Sube isKampanyaIsActiveForSube(int id){
		return subeRepository.getSubeForSubeKampanya(id);
	}
	
	public List<Urun> getUrunForMap(int id){
		return urunRepository.getUrunWithBulunduguSube(id);
	}
	
	public List<Sube> getAllSube(){
		return (List<Sube>) subeRepository.findAll();
	}
	
}
