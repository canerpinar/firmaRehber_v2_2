package com.firmaRehber.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.SeoContent;

@Repository
public interface SeoContentRepository extends CrudRepository<SeoContent,Integer> {
	
	//@Query("select seo from Seo seo,Urun urun where ");
	@Transactional
	@Modifying
	@Query("delete from SeoContent seo where seo.id=:id")
	void deleteSeoContentWithId(@Param("id")int id);
}
