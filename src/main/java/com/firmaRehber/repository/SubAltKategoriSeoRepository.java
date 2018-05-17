package com.firmaRehber.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.firmaRehber.entity.SubAltKategoriSeo;

public interface SubAltKategoriSeoRepository extends CrudRepository<SubAltKategoriSeo, Integer> {

	@Transactional
	@Modifying
	@Query("select subAltSeo from SubAltKategoriSeo subAltSeo where subAltSeo.subAltKategoriId=:subAltKategoriId")
	public List<SubAltKategoriSeo> getSubAltKategoriSeo(@Param("subAltKategoriId") int id);
	
	@Transactional
	@Modifying
	@Query("delete from SubAltKategoriSeo subAltSeo where subAltSeo.id=:id")
	public void deleteSubAltKategoriSeo(@Param("id") int id);
	
}
