package com.firmaRehber.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.SubAltKategori;

@Repository
public interface SubAltKategoriRepository extends CrudRepository<SubAltKategori, Integer> {
	@Transactional
	@Modifying
	@Query("delete from SubAltKategori subAlt where subAlt.id=:subAltId")
	void deleteSubAlt(@Param("subAltId")int subAltId);
	
	@Query("select subalt from SubAltKategori subalt where subalt.subAltKategoriAd=:subAltKategoriAd")
	List<SubAltKategori> getSubAltKategoriWithName(@Param("subAltKategoriAd")String subAltKategoriAd);
}
