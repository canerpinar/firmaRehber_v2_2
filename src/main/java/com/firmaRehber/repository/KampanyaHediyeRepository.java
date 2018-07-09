package com.firmaRehber.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.firmaRehber.entity.KampanyaHediye;
import com.firmaRehber.entity.Urun;

public interface KampanyaHediyeRepository extends CrudRepository<KampanyaHediye,Integer>{
	@Query("select hediyeKampanyasi from KampanyaHediye hediyeKampanyasi,Urun urun where "+
	"hediyeKampanyasi.urun=:urun_id")
	public KampanyaHediye getKampanyaHediye(@Param("urun_id") Urun urun_id);
}
