package com.firmaRehber.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.AltKategoriSeo;

@Repository
public interface AltKategoriSeoRepository extends CrudRepository<AltKategoriSeo,Integer> {

	@Transactional
	@Modifying
	@Query("select altSeo from AltKategoriSeo altSeo where altSeo.altKategoriId=:altKategoriId")
	public List<AltKategoriSeo> getAltKategoriSeo(@Param("altKategoriId") int id);
	
	@Transactional
	@Modifying
	@Query("delete from AltKategoriSeo altSeo where altSeo.id=:id")
	public void deleteAltKategoriSeo(@Param("id") int id);
	
	
}
