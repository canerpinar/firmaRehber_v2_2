package com.firmaRehber.entity;

import java.sql.Date;

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

@Entity
@Table(name="kampanya")
public class Kampanya {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="urun_id")
	private int urunId;
	
	@Column(name="kampanya_ad")
	private String kampanyaAd;
	
	
	public String getKampanyaAd() {
		return kampanyaAd;
	}

	public void setKampanyaAd(String kampanyaAd) {
		this.kampanyaAd = kampanyaAd;
	}

	@Column(name="kampanya_turu")
	private short kampanyaTuru;
	
	@Column(name="current_fiyat")
	private float currentFiyat;
	
	@Column(name="kampanya_fiyat")
	private float kampanyaFiyat;
	
	@Column(name="kampanya_oran")
	private String kampanyaOran;
	
	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;
	
	@JoinColumn(name="kampanya_urunu")
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Urun kampanyaUrun;
	
	public Urun getKampanyaUrun() {
		return kampanyaUrun;
	}

	public void setKampanyaUrun(Urun kampanyaUrun) {
		this.kampanyaUrun = kampanyaUrun;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUrunId() {
		return urunId;
	}

	public void setUrunId(int urunId) {
		this.urunId = urunId;
	}

	public short getKampanyaTuru() {
		return kampanyaTuru;
	}

	public void setKampanyaTuru(short kampanyaTuru) {
		this.kampanyaTuru = kampanyaTuru;
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

	public String getKampanyaOran() {
		return kampanyaOran;
	}

	public void setKampanyaOran(String kampanyaOran) {
		this.kampanyaOran = kampanyaOran;
	}
	

}
