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
@Table(name="sube")
public class Sube {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="firma_id",nullable=false)
	@JsonBackReference
	private Firma firma;
	
	@Column(name="email")
	private String email;
	
	@Column(name="ad")
	private String ad;
	
	@Column(name="sube_sorumlu")
	private String subeSorumlusu;
	
	@Column(name="sorumlu_cep_tel")
	private String subeSorumlusuTelefon;

	@Column(name="sube_telefon")
	private String subeTelefon;
	
	@Column(name="sube_adres")
	private String subeAdres;
	
	@Column(name="sube_location")
	private String subeKonum;
	
	@Column(name="sube_durum")
	private boolean satisVarMi;
		
	
	public boolean isSatisVarMi() {
		return satisVarMi;
	}

	public void setSatisVarMi(boolean satisVarMi) {
		this.satisVarMi = satisVarMi;
	}

	public String getSubeSorumlusu() {
		return subeSorumlusu;
	}

	public void setSubeSorumlusu(String subeSorumlusu) {
		this.subeSorumlusu = subeSorumlusu;
	}

	public String getSubeSorumlusuTelefon() {
		return subeSorumlusuTelefon;
	}

	public void setSubeSorumlusuTelefon(String subeSorumlusuTelefon) {
		this.subeSorumlusuTelefon = subeSorumlusuTelefon;
	}

	public String getSubeTelefon() {
		return subeTelefon;
	}

	public void setSubeTelefon(String subeTelefon) {
		this.subeTelefon = subeTelefon;
	}

	public String getSubeAdres() {
		return subeAdres;
	}

	public void setSubeAdres(String subeAdres) {
		this.subeAdres = subeAdres;
	}

	public String getSubeKonum() {
		return subeKonum;
	}

	public void setSubeKonum(String subeKonum) {
		this.subeKonum = subeKonum;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}
	
	
	
	
	
	
	
}
