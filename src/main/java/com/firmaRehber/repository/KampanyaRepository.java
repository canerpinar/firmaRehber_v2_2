package com.firmaRehber.repository;

import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Kampanya;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface KampanyaRepository extends CrudRepository<Kampanya,Integer> {

	
	@Query("select kampanya from Kampanya kampanya,Urun urun where kampanya.urunId=urun.id and "+
	"urun.urunSahibiFirma=:firmaId")
	public List<Kampanya> getKampanyaForFirma(@Param("firmaId") int firmaId);
}
