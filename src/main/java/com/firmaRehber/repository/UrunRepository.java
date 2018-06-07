package com.firmaRehber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Urun;

@Repository
public interface UrunRepository extends CrudRepository<Urun,Integer> {

	@Query("select urun from Urun urun where urun.urunSahibiFirma=:firmaId")
	public List<Urun> getUrunForFirma(@Param("firmaId") int firmaId);
	
	@Query("select urun from Urun urun where urun.urunLink=:urunLink")
	public Urun getUrunWithLink(@Param("urunLink")String urunLink);
	
	@Query("select urun from Urun urun where urun.bulunduguSubeorFirma=:sube_id")
	public List<Urun> getUrunWithBulunduguSube(@Param("sube_id")int subeId);
}
