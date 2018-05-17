package com.firmaRehber.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.AltKategori;

@Repository
public interface AltKategoriRepository extends CrudRepository<AltKategori, Integer> {
	@Transactional
	@Modifying
	@Query("delete from AltKategori alt where alt.id=:altId")
	void deleteAltKategori(@Param("altId")int altId);
	
	@Query("select altkat from AltKategori altkat where altkat.altKategoriAd=:altKategoriAd")
	List<AltKategori> getAltKategoriWithName(@Param("altKategoriAd")String altKategoriAd);
}
