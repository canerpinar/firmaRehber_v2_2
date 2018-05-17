package com.firmaRehber.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.firmaRehber.entity.Sube;
public interface SubeRepository extends CrudRepository<Sube,Integer> {

	@Query("select sube from Sube sube,Firma firma where sube.firma=firma.id  and firma.id=:firma_id")
	public List<Sube> getSubeForFirma(@Param("firma_id") Integer firmaId);
	
	@Transactional
	@Modifying
	@Query("delete from Sube sube where sube.id=:sube_id")
	public void deleteSube(@Param("sube_id")int id);
	
}
