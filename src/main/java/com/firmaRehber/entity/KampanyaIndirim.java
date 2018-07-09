package com.firmaRehber.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="indirim_kampanyasi")
public class KampanyaIndirim {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="urun_id")
	@JsonBackReference
	private Urun urun;
	
	@Column(name="indirim_oran")
	private String indirimOran;
	
	@Column(name="current_fiyat")
	private float currentFiyat;
	
	@Column(name="kampanya_fiyat")
	private float kampanyaliFiyat;
	
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="kampanya_id")
	@JsonBackReference
	private Kampanya kampanya;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Urun getUrun() {
		return urun;
	}

	public void setUrun(Urun urun) {
		this.urun = urun;
	}

	public String getIndirimOran() {
		return indirimOran;
	}

	public void setIndirimOran(String indirimOran) {
		this.indirimOran = indirimOran;
	}

	public float getCurrentFiyat() {
		return currentFiyat;
	}

	public void setCurrentFiyat(float currentFiyat) {
		this.currentFiyat = currentFiyat;
	}

	public float getKampanyaliFiyat() {
		return kampanyaliFiyat;
	}

	public void setKampanyaliFiyat(float kampanyaliFiyat) {
		this.kampanyaliFiyat = kampanyaliFiyat;
	}

	public Kampanya getKampanya() {
		return kampanya;
	}

	public void setKampanya(Kampanya kampanya) {
		this.kampanya = kampanya;
	}

	
	
	
	
	
}
