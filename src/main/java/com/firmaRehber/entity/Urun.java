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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Transient
	private Firma urunSahibi;		
	
	public Firma getUrunSahibi() {
		return urunSahibi;
	}

	public void setUrunSahibi(Firma urunSahibi) {
		this.urunSahibi = urunSahibi;
	}

	@Column(name="urunad")
	private String urunAd;
	
	@Column(name="urunfiyat")
	private float urunFiyat;
	
	@Column(name="urunhakkinda")
	private String urunHakkinda;
	
	@Column(name="urun_seo_status")
	private boolean seoStatus;
	
	
	public boolean isSeoStatus() {
		return seoStatus;
	}

	public void setSeoStatus(boolean seoStatus) {
		this.seoStatus = seoStatus;
	}

	@Column(name="urunsahibi_firma")
	private int urunSahibiFirma;
	
	@Column(name="urunstokdurumu")
	private boolean stokDurumu;
	
	@Column(name="uruncontrol")
	private boolean urunControl;
	
	@Column(name="image")
	private String image;
	
	@Column(name="imageone")
	private String imageOne;
	
	@Column(name="imagetwo")
	private String imageTwo;
	
	@Column(name="imagethree")
	private String imageThree;
	
	@Column(name="urun_kampanya")
	private boolean kampanyaStatus;
	
	@Column(name="urun_kampanya_fiyat")
	private float kampanyaliFiyat;
	
	@Column(name="urun_kampanya_oran")
	private String kampanyaOran;
	
	@Column(name="urun_odeme_kart")
	private boolean odemeKrediKarti;
	
	@Column(name="urun_odeme_kapida")
	private boolean odemeKapida;
	
	@Column(name="urun_odeme_havale")
	private boolean odemeHavale;
	
	@Column(name="urun_marka")
	private String marka;
	
	

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public boolean isOdemeKrediKarti() {
		return odemeKrediKarti;
	}

	public void setOdemeKrediKarti(boolean odemeKrediKarti) {
		this.odemeKrediKarti = odemeKrediKarti;
	}

	public boolean isOdemeKapida() {
		return odemeKapida;
	}

	public void setOdemeKapida(boolean odemeKapida) {
		this.odemeKapida = odemeKapida;
	}

	public boolean isOdemeHavale() {
		return odemeHavale;
	}

	public void setOdemeHavale(boolean odemeHavale) {
		this.odemeHavale = odemeHavale;
	}

	@JoinColumn(name="kampanya_id")
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Kampanya kampanya;
	


	public Kampanya getKampanya() {
		return kampanya;
	}

	public void setKampanya(Kampanya kampanya) {
		this.kampanya = kampanya;
	}

	public String getKampanyaOran() {
		return kampanyaOran;
	}

	public void setKampanyaOran(String kampanyaOran) {
		this.kampanyaOran = kampanyaOran;
	}

	public float getKampanyaliFiyat() {
		return kampanyaliFiyat;
	}

	public void setKampanyaliFiyat(float kampanyaliFiyat) {
		this.kampanyaliFiyat = kampanyaliFiyat;
	}

	public boolean isKampanyaStatus() {
		return kampanyaStatus;
	}

	public void setKampanyaStatus(boolean kampanyaStatus) {
		this.kampanyaStatus = kampanyaStatus;
	}

	public String getImageOne() {
		return imageOne;
	}

	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}

	public String getImageTwo() {
		return imageTwo;
	}

	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}

	public String getImageThree() {
		return imageThree;
	}

	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
	}

	@Column(name="urunwhereis_id")
	private int bulunduguSubeorFirma;

	@Column(name="urun_adres")
	private String bulunduguAdres;
	
	@Column(name="urun_link")
	private String urunLink;
	
	@Column(name="urun_image_path")
	private String imagePath;	
	
	@Column(name="urun_pid")
	private String pidKod;
	
	
	public String getPidKod() {
		return pidKod;
	}

	public void setPidKod(String pidKod) {
		this.pidKod = pidKod;
	}

	public String getUrunLink() {
		return urunLink;
	}

	public void setUrunLink(String urunLink) {
		this.urunLink = urunLink;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

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
