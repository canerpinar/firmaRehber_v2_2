package com.firmaRehber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="kategori_Seo")
@Entity
public class KategoriSeo {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="kategori_id")
	private int kategoriId;
	
	@Column(name="kategori_ad")
	private String kategoriAd;
	
	@Column(name="meta_name")
	private String metaName;
	
	@Column(name="meta_content")
	private String metaContent;
	
	@Column(name="page_title")
	private String pageTitle;
	
	@Column(name="seo_content")
	private String seoContent;

	public KategoriSeo(int kategoriId, String kategoriAd, String metaName, String metaContent, String pageTitle,String seoContent) {
		super();
		this.kategoriId = kategoriId;
		this.kategoriAd = kategoriAd;
		this.metaName = metaName;
		this.metaContent = metaContent;
		this.pageTitle = pageTitle;
		this.seoContent=seoContent;
	}

	public KategoriSeo() {
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

	public String getKategoriAd() {
		return kategoriAd;
	}

	public void setKategoriAd(String kategoriAd) {
		this.kategoriAd = kategoriAd;
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
