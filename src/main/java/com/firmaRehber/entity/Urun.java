package com.firmaRehber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="urunler")
public class Urun {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="urunkategori_id")
	private int katagoriId;
	
	@Column(name="urunaltkategori_id")
	private int altKatagoriId;	
	
	@Column(name="urunsubkategori_id")
	private int subKategoriId;
	
	@Column(name="urunkategori_ad")
	private String kategoriAd;
	
	@Column(name="urunaltkategori_ad")
	private String altKategoriAd;
	
	@Column(name="urunsubkategori_ad")
	private String subKategoriAd;
	
	
	@Column(name="urunad")
	private String urunAd;
	
	@Column(name="urunfiyat")
	private float urunFiyat;
	
	@Column(name="urunhakkinda")
	private String urunHakkinda;
	
	@Column(name="urunsahibi_firma")
	private int urunSahibiFirma;
	
	@Column(name="urunstokdurumu")
	private boolean stokDurumu;
	
	@Column(name="uruncontrol")
	private boolean urunControl;
	
	@Column(name="image")
	private String image;
	
	@Column(name="urunwhereis_id")
	private int bulunduguSubeorFirma;

	@Column(name="urun_adres")
	private String bulunduguAdres;	
	

	public String getBulunduguAdres() {
		return bulunduguAdres;
	}

	public void setBulunduguAdres(String bulunduguAdres) {
		this.bulunduguAdres = bulunduguAdres;
	}

	public int getBulunduguSubeorFirma() {
		return bulunduguSubeorFirma;
	}

	public void setBulunduguSubeorFirma(int bulunduguSubeorFirma) {
		this.bulunduguSubeorFirma = bulunduguSubeorFirma;
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

	public String getSubKategoriAd() {
		return subKategoriAd;
	}

	public void setSubKategoriAd(String subKategoriAd) {
		this.subKategoriAd = subKategoriAd;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	



	public Urun(int katagoriId, int altKatagoriId, int subKategoriId, String kategoriAd, String altKategoriAd,
			String subKategoriAd, String urunAd, float urunFiyat, String urunHakkinda, int urunSahibiFirma,
			boolean stokDurumu, boolean urunControl, String image, int bulunduguSubeorFirma, String bulunduguAdres) {
		super();
		this.katagoriId = katagoriId;
		this.altKatagoriId = altKatagoriId;
		this.subKategoriId = subKategoriId;
		this.kategoriAd = kategoriAd;
		this.altKategoriAd = altKategoriAd;
		this.subKategoriAd = subKategoriAd;
		this.urunAd = urunAd;
		this.urunFiyat = urunFiyat;
		this.urunHakkinda = urunHakkinda;
		this.urunSahibiFirma = urunSahibiFirma;
		this.stokDurumu = stokDurumu;
		this.urunControl = urunControl;
		this.image = image;
		this.bulunduguSubeorFirma = bulunduguSubeorFirma;
		this.bulunduguAdres = bulunduguAdres;
	}

	public Urun(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKatagoriId() {
		return katagoriId;
	}

	public void setKatagoriId(int katagoriId) {
		this.katagoriId = katagoriId;
	}

	public int getAltKatagoriId() {
		return altKatagoriId;
	}

	public void setAltKatagoriId(int altKatagoriId) {
		this.altKatagoriId = altKatagoriId;
	}

	public int getSubKategoriId() {
		return subKategoriId;
	}

	public void setSubKategoriId(int subKategoriId) {
		this.subKategoriId = subKategoriId;
	}

	public String getUrunAd() {
		return urunAd;
	}

	public void setUrunAd(String urunAd) {
		this.urunAd = urunAd;
	}

	public float getUrunFiyat() {
		return urunFiyat;
	}

	public void setUrunFiyat(float urunFiyat) {
		this.urunFiyat = urunFiyat;
	}

	public String getUrunHakkinda() {
		return urunHakkinda;
	}

	public void setUrunHakkinda(String urunHakkinda) {
		this.urunHakkinda = urunHakkinda;
	}

	public int getUrunSahibiFirma() {
		return urunSahibiFirma;
	}

	public void setUrunSahibiFirma(int urunSahibiFirma) {
		this.urunSahibiFirma = urunSahibiFirma;
	}

	public boolean isStokDurumu() {
		return stokDurumu;
	}

	public void setStokDurumu(boolean stokDurumu) {
		this.stokDurumu = stokDurumu;
	}

	public boolean isUrunControl() {
		return urunControl;
	}

	public void setUrunControl(boolean urunControl) {
		this.urunControl = urunControl;
	}
	
	

}
