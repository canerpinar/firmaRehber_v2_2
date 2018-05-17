package com.firmaRehber.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="seo")
public class Seo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="page_name")
	private String pageName;

	@OneToMany(mappedBy="seo",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<SeoContent> seoContentList;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public List<SeoContent> getSeoContentList() {
		return seoContentList;
	}

	public void setSeoContentList(List<SeoContent> seoContentList) {
		this.seoContentList = seoContentList;
	}
	
	
	
}
