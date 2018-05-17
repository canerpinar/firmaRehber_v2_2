package com.firmaRehber.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="reklam")
public class Reklam {

	enum reklamPosition{
		UP,
		BOTTOM,
		LEFT,
		RIGHT;
	}
	
	static List<String> reklamPositionList = new ArrayList<String>();
	
	public List<String> getReklamPositionList() {
		return reklamPositionList;
	}

	static{
		reklamPositionList.add("UST");
		reklamPositionList.add("ALT");
		reklamPositionList.add("SOL");
		reklamPositionList.add("SAĞ");
	}
	

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reklamAd")
	private String reklamAd;
	
	@Column(name="reklamImage")
	private String reklamImage;
	
	@Column(name="reklamLink")
	private String reklamLink;
	
	@Column(name="reklamPosition")
	private String reklamPosition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReklamAd() {
		return reklamAd;
	}

	public void setReklamAd(String reklamAd) {
		this.reklamAd = reklamAd;
	}

	public String getReklamImage() {
		return reklamImage;
	}

	public void setReklamImage(String reklamImage) {
		this.reklamImage = reklamImage;
	}

	public String getReklamLink() {
		return reklamLink;
	}

	public void setReklamLink(String reklamLink) {
		this.reklamLink = reklamLink;
	}

	public String getReklamPosition() {
		return reklamPosition;
	}

	public void setReklamPosition(String position) {
		this.reklamPosition = position;
	}
	
	
}
