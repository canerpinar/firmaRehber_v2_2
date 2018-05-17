package com.firmaRehber.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Kategori;

@Repository
public interface KategoriRepository extends CrudRepository<Kategori, Integer> {
	@Query("select kat from Kategori kat,SubAltKategori subalt where kat.kategoriAd=:kategoriAd and "
			+ "subalt.kategori=kat.kategoriAd")
	public List<Kategori> findKategori(@Param("kategoriAd")String kategoriAd);
	
	
	@Query("select kat from Kategori kat where kat.kategoriAd=:kategoriAd")
	public List<Kategori> findKategoriWithName(@Param("kategoriAd")String kategoriAd);
	
	
	@Transactional
	@Modifying
	@Query("delete from Kategori kategori where kategori.id=:kat_id")
	void deleteKategori(@Param("kat_id")int kat_id);
}
