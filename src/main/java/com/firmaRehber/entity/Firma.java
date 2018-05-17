package com.firmaRehber.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="firma")
public class Firma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="firma_mail")
	private String email;
	
	@Column(name="firma_bg_image")
	private String firmaBackgroundImage;
	
	@Column(name="firma_ad")
	private String firmaName;
	
	@Column(name="firma_sahip")
	private String firmaOwner;
	
	@Column(name="firma_password")
	private String password;
	
	@Column(name="firma_image")
	private String firmaImage;
	
	@Column(name="firmaControl")
	private boolean firmaControl;
	
	@Column(name="website")
	private String website;
	
	@Column(name="haftasonuacik_mi")
	private boolean haftasonustatus;
	
	
	
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isHaftasonustatus() {
		return haftasonustatus;
	}

	public void setHaftasonustatus(boolean haftasonustatus) {
		this.haftasonustatus = haftasonustatus;
	}

	public String getFirmaBackgroundImage() {
		return firmaBackgroundImage;
	}

	public void setFirmaBackgroundImage(String firmaBackgroundImage) {
		this.firmaBackgroundImage = firmaBackgroundImage;
	}

	@OneToMany(mappedBy="firma",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Referanslar> referansList;
	
	@Column(name="firma_active")
	private boolean firmaActiveStatus;
	


	public boolean isFirmaActiveStatus() {
		return firmaActiveStatus;
	}

	public void setFirmaActiveStatus(boolean firmaActiveStatus) {
		this.firmaActiveStatus = firmaActiveStatus;
	}

	@OneToMany(mappedBy="firma",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Sube> subeList;
	
	public Firma() {}

	public Firma(int id, String email, String firmaName, String firmaOwner, String password,String image) {
		super();
		this.id = id;
		this.email = email;
		this.firmaName = firmaName;
		this.firmaOwner = firmaOwner;
		this.password = password;
		this.firmaImage=image;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirmaName() {
		return firmaName;
	}

	public void setFirmaName(String firmaName) {
		this.firmaName = firmaName;
	}

	public String getFirmaOwner() {
		return firmaOwner;
	}

	public void setFirmaOwner(String firmaOwner) {
		this.firmaOwner = firmaOwner;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirmaImage() {
		return firmaImage;
	}

	public void setFirmaImage(String firmaImage) {
		this.firmaImage = firmaImage;
	}

	public List<Referanslar> getReferansList() {
		return referansList;
	}

	public void setReferansList(List<Referanslar> referansList) {
		this.referansList = referansList;
	}

	public List<Sube> getSubeList() {
		return subeList;
	}

	public void setSubeList(List<Sube> subeList) {
		this.subeList = subeList;
	}

	public boolean isFirmaControl() {
		return firmaControl;
	}

	public void setFirmaControl(boolean firmaControl) {
		this.firmaControl = firmaControl;
	}
	
	

	
	
	
}
