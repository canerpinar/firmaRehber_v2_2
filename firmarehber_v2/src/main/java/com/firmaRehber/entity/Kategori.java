package com.firmaRehber.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="kategoriler")
public class Kategori {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="kategoriad")
	private String kategoriAd;
	
	@OneToMany(mappedBy="kategori",cascade=CascadeType.ALL)
	@JsonManagedReference
	public List<AltKategori> altKategori = new ArrayList<AltKategori>();
	
	@Column(name="seo_avaliable")
	private boolean seoAvaliable;
	

	public boolean isSeoAvaliable() {
		return seoAvaliable;
	}

	public void setSeoAvaliable(boolean seoAvaliable) {
		this.seoAvaliable = seoAvaliable;
	}

	public List<AltKategori> getAltKategori() {
		return altKategori;
	}

	public void setAltKategori(List<AltKategori> altKategori) {
		this.altKategori = altKategori;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKategoriAd() {
		return kategoriAd;
	}

	public void setKategoriAd(String kategoriAd) {
		this.kategoriAd = kategoriAd;
	}
	
/*	@Override
	public String toString(){
		return "id" + getId() + ",ad"+
	}*/

}
