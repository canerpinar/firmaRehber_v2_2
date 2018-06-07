package com.firmaRehber.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.SubeKampanya;

@Repository
public interface SubeKampanyaRepository extends CrudRepository<SubeKampanya, Integer> {
/*
	@Query("select kampanya from SubeKampanya kampanya where kampanya.sube=:id")
	public SubeKampanya getSubeKampanya(@Param("id")int id);*/
	
}
