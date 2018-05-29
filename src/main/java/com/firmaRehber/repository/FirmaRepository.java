package com.firmaRehber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Firma;

@Repository
public interface FirmaRepository extends CrudRepository<Firma,Integer> {

	@Query("select firma from Firma firma where firma.email=:email")
	public Firma getFirma(@Param("email") String email);
	
	@Query("select firma from Firma firma,Urun urun where firma.id=urun.urunSahibiFirma and"+
	" urun.kategoriAd=:kategoriAd")
	public List<Firma> getFirmaWithUrunForKategori(@Param("kategoriAd")String kategoriAd);
	
}
