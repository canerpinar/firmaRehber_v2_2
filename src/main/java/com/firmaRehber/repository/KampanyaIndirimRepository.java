package com.firmaRehber.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.firmaRehber.entity.KampanyaIndirim;
import com.firmaRehber.entity.Urun;

public interface KampanyaIndirimRepository extends CrudRepository<KampanyaIndirim,Integer> {

	@Query("select indirimKampanyasi from KampanyaIndirim indirimKampanyasi,Urun urun where "+
	"indirimKampanyasi.urun=:urun_id")
	public KampanyaIndirim getKampanyaIndirim(@Param("urun_id") Urun urun_id);
	
}
