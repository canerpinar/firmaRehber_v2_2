package com.firmaRehber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="slider")
public class Images {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="resimName")
	private String resimName;
	
	@Column(name="resimAltText")
	private String resimAltText;
	
	@Column(name="resimUstText")
	private String resimUstText;
	
	@Column(name="resimLink")
	private String resimLink;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResimName() {
		return resimName;
	}

	public void setResimName(String resimName) {
		this.resimName = resimName;
	}

	public String getResimAltText() {
		return resimAltText;
	}

	public void setResimAltText(String resimAltText) {
		this.resimAltText = resimAltText;
	}

	public String getResimUstText() {
		return resimUstText;
	}

	public void setResimUstText(String resimUstText) {
		this.resimUstText = resimUstText;
	}

	public String getResimLink() {
		return resimLink;
	}

	public void setResimLink(String resimLink) {
		this.resimLink = resimLink;
	}
	
	
}
