package com.firmaRehber.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.FetchType;

@Entity
@Table(name="hediye_kampanyasi")
public class KampanyaHediye {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="urun_id")
	@JsonBackReference
	private Urun urun;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="hediye_id")
	@JsonBackReference
	private Urun urun_hediye;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="kampanya_id")
	@JsonBackReference
	private Kampanya kampanya;
	
	@Column(name="current_fiyat")
	private float currentFiyat;
	
	@Column(name="kampanya_fiyat")
	private float kampanyaFiyat;

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

	public Urun getUrun_hediye() {
		return urun_hediye;
	}

	public void setUrun_hediye(Urun urun_hediye) {
		this.urun_hediye = urun_hediye;
	}

	public Kampanya getKampanya() {
		return kampanya;
	}

	public void setKampanya(Kampanya kampanya) {
		this.kampanya = kampanya;
	}

	public float getCurrentFiyat() {
		return currentFiyat;
	}

	public void setCurrentFiyat(float currentFiyat) {
		this.currentFiyat = currentFiyat;
	}

	public float getKampanyaFiyat() {
		return kampanyaFiyat;
	}

	public void setKampanyaFiyat(float kampanyaFiyat) {
		this.kampanyaFiyat = kampanyaFiyat;
	}
	
	
	
}
