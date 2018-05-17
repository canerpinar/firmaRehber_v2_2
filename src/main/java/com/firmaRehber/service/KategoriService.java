package com.firmaRehber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.transaction.Transactional;

import com.firmaRehber.entity.AltKategori;
import com.firmaRehber.entity.Kategori;
import com.firmaRehber.entity.SubAltKategori;
import com.firmaRehber.repository.AltKategoriRepository;
import com.firmaRehber.repository.KategoriRepository;
import com.firmaRehber.repository.SubAltKategoriRepository;

@Service
public class KategoriService {

	@Autowired
	private KategoriRepository kategoriRepository;
	
	@Autowired
	private AltKategoriRepository altKategoriRepository;
	
	@Autowired
	private SubAltKategoriRepository subAltKategoriRepository;
	
	@Transactional
	public void saveKategori(Kategori kategori) {
		kategoriRepository.save(kategori);
	}
	
	public List<Kategori> getAnaKategori(String kategoriAd) {
		return kategoriRepository.findKategori(kategoriAd);
	}
	
	public List<Kategori> getKategoriWithName(String kategoriAd){
		return kategoriRepository.findKategoriWithName(kategoriAd);
	}
	
	public List<AltKategori> getAltKategoriWithName(String kategoriAd){
		return altKategoriRepository.getAltKategoriWithName(kategoriAd);
	}
	
	public Kategori getAnaKategori(int id) {
		return kategoriRepository.findOne(id);
	}
	
	public AltKategori getAltKategori(int id){
		return altKategoriRepository.findOne(id);
	}
	
	public SubAltKategori getSubAltKategori(int id){
		return subAltKategoriRepository.findOne(id);
	}
	
	public Iterable<Kategori> getAllKategori(){
		return kategoriRepository.findAll();
	}
	
	public void updateKategori(Kategori kategori) {
		kategoriRepository.save(kategori);
	}
	
	public void saveAltKategori(AltKategori altKategori){
		altKategoriRepository.save(altKategori);
	}
	
	public void saveSubKategori(SubAltKategori subAltKategori){
		subAltKategoriRepository.save(subAltKategori);
	}
	
	public void saveArraySubKategori(List<SubAltKategori> listSubArray){
		subAltKategoriRepository.save(listSubArray);
	}
	@Transactional
	public void deleteAltKategori(AltKategori altKategori){
		altKategoriRepository.deleteAltKategori(altKategori.getId());
	}
	@Transactional
	public void deleteSubAltKategori(SubAltKategori subAltKategori){
		subAltKategoriRepository.deleteSubAlt(subAltKategori.getId());
	}
	
	public List<AltKategori> getAllAltKategori(){
		return (List<AltKategori>) altKategoriRepository.findAll();
	}
	
	public List<SubAltKategori> getAllSubAltKategori(){
		return (List<SubAltKategori>) subAltKategoriRepository.findAll();
	}
	@Transactional
	public void deleteKategori(Kategori kategori) {
		kategoriRepository.deleteKategori(kategori.getId());
	}
	
	public List<SubAltKategori> getSubAltKategoriWithName(String name){
		return subAltKategoriRepository.getSubAltKategoriWithName(name);
	}
	
}
