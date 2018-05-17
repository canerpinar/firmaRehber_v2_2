package com.firmaRehber.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="altkategori")
public class AltKategori {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="altkategoriad")
	private String altKategoriAd;
	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="kategori_id")
	@JsonBackReference
	private Kategori kategori;
	
	
	@OneToMany(mappedBy="altKategori",cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true)
	@JsonManagedReference
	private List<SubAltKategori> subaltKategori = new ArrayList<SubAltKategori>();
	
	@Column(name="seo_avaliable")
	private boolean seoAvaliable;
	

	public boolean isSeoAvaliable() {
		return seoAvaliable;
	}

	public void setSeoAvaliable(boolean seoAvaliable) {
		this.seoAvaliable = seoAvaliable;
	}

	public List<SubAltKategori> getSubaltKategori() {
		return subaltKategori;
	}

	public void setSubaltKategori(List<SubAltKategori> subaltKategori) {
		this.subaltKategori = subaltKategori;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAltKategoriAd() {
		return altKategoriAd;
	}

	public void setAltKategoriAd(String altKategoriAd) {
		this.altKategoriAd = altKategoriAd;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}
	

}
