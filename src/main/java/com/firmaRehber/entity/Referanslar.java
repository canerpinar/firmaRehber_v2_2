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
@Table(name="referanslar")
public class Referanslar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="firma_Id",nullable=false)
	@JsonBackReference
	private Firma firma;
	
	@Column(name="referans_Adi")
	private String referansAdi;
	
	@Column(name="referans_Image")
	private String referansImage;
	
	@Column(name="referans_Link")
	private String referansLink;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public String getReferansAdi() {
		return referansAdi;
	}

	public void setReferansAdi(String referansAdi) {
		this.referansAdi = referansAdi;
	}

	public String getReferansImage() {
		return referansImage;
	}

	public void setReferansImage(String referansImage) {
		this.referansImage = referansImage;
	}

	public String getReferansLink() {
		return referansLink;
	}

	public void setReferansLink(String referansLink) {
		this.referansLink = referansLink;
	}
	
	
	
}
