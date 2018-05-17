package com.firmaRehber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="subaltkategori_Seo")
@Entity
public class SubAltKategoriSeo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="kategori_id")
	private int kategoriId;
	
	@Column(name="altkategori_id")
	private int altKategoriId;
	
	@Column(name="subaltkategori_id")
	private int subAltKategoriId;
	
	@Column(name="kategori_ad")
	private String kategoriAd;
	
	@Column(name="altkategori_ad")
	private String altKategoriAd;
	
	@Column(name="subaltkategori_ad")
	private String subAltKategoriAd;
	
	@Column(name="meta_name")
	private String metaName;
	
	@Column(name="meta_content")
	private String metaContent;
	
	@Column(name="page_title")
	private String pageTitle;
	
	@Column(name="seo_content")
	private String seoContent;

	public SubAltKategoriSeo(int kategoriId, int altKategoriId, int subAltKategoriId, String kategoriAd,
			String altKategoriAd, String subAltKategoriAd, String metaName, String metaContent, String pageTitle,String seoContent) {
		super();
		this.kategoriId = kategoriId;
		this.altKategoriId = altKategoriId;
		this.subAltKategoriId = subAltKategoriId;
		this.kategoriAd = kategoriAd;
		this.altKategoriAd = altKategoriAd;
		this.subAltKategoriAd = subAltKategoriAd;
		this.metaName = metaName;
		this.metaContent = metaContent;
		this.pageTitle = pageTitle;
		this.seoContent=seoContent;
	}

	public SubAltKategoriSeo() {
		// TODO Auto-generated constructor stub
	}

	public String getSeoContent() {
		return seoContent;
	}

	public void setSeoContent(String seoContent) {
		this.seoContent = seoContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId(int kategoriId) {
		this.kategoriId = kategoriId;
	}

	public int getAltKategoriId() {
		return altKategoriId;
	}

	public void setAltKategoriId(int altKategoriId) {
		this.altKategoriId = altKategoriId;
	}

	public int getSubAltKategoriId() {
		return subAltKategoriId;
	}

	public void setSubAltKategoriId(int subAltKategoriId) {
		this.subAltKategoriId = subAltKategoriId;
	}

	public String getKategoriAd() {
		return kategoriAd;
	}

	public void setKategoriAd(String kategoriAd) {
		this.kategoriAd = kategoriAd;
	}

	public String getAltKategoriAd() {
		return altKategoriAd;
	}

	public void setAltKategoriAd(String altKategoriAd) {
		this.altKategoriAd = altKategoriAd;
	}

	public String getSubAltKategoriAd() {
		return subAltKategoriAd;
	}

	public void setSubAltKategoriAd(String subAltKategoriAd) {
		this.subAltKategoriAd = subAltKategoriAd;
	}

	public String getMetaName() {
		return metaName;
	}

	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}

	public String getMetaContent() {
		return metaContent;
	}

	public void setMetaContent(String metaContent) {
		this.metaContent = metaContent;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	
	
	
}
