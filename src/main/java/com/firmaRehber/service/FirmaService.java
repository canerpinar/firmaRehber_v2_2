package com.firmaRehber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firmaRehber.entity.Firma;
import com.firmaRehber.repository.FirmaRepository;

@Service
public class FirmaService {

	@Autowired
	private FirmaRepository firmaRepository;
	
	public void saveFirma(Firma firma){
		firmaRepository.save(firma);
	}
	
	public Firma getFirma(String email) {
		return firmaRepository.getFirma(email);
	}
	
	public Firma getFirma(int id) {
		return firmaRepository.findOne(id);
	}
	
	public Firma getFirmaDetayForUser(String firmaAd){
		return firmaRepository.getFirmaDetay(firmaAd);
	}
	
}
