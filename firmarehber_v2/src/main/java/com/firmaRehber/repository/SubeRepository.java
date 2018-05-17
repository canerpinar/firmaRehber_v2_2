package com.firmaRehber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.firmaRehber.entity.Sube;
public interface SubeRepository extends CrudRepository<Sube,Integer> {

	@Query("select sube from Sube sube where sube.firma.id=:firma_id")
	public List<Sube> getSubeForFirma(@Param("firma_id") Integer firmaId);
	
}
