package com.firmaRehber.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="subaltkategori")
public class SubAltKategori {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="kategori_id")
	@JsonBackReference
	private Kategori kategori;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="altkategori_id")
	@JsonBackReference
	private AltKategori altKategori;
	
	@Column(name="subaltkategoriad")
	private String subAltKategoriAd;
	
	@Column(name="seo_avaliable")
	private boolean seoAvaliable;
	

	public boolean isSeoAvaliable() {
		return seoAvaliable;
	}

	public void setSeoAvaliable(boolean seoAvaliable) {
		this.seoAvaliable = seoAvaliable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public AltKategori getAltKategori() {
		return altKategori;
	}

	public void setAltKategori(AltKategori altKategori) {
		this.altKategori = altKategori;
	}

	public String getSubAltKategoriAd() {
		return subAltKategoriAd;
	}

	public void setSubAltKategoriAd(String subAltKategoriAd) {
		this.subAltKategoriAd = subAltKategoriAd;
	}
	
	
	
}
