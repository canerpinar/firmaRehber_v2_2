package com.firmaRehber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="mesaj_Kime")
	private int mesajKimeId;
	
	@Column(name="mesaj_Kimden")
	private String mesajKimden;
	
	
	@Column(name="mesaj_Content")
	private String mesajContent;

	public Message() {}

	
	
	public Message(int id, int mesajKimeId, String mesajKimden, String mesajContent) {
		super();
		this.id = id;
		this.mesajKimeId = mesajKimeId;
		this.mesajKimden = mesajKimden;
		this.mesajContent = mesajContent;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getMesajKimeId() {
		return mesajKimeId;
	}


	public void setMesajKimeId(int mesajKimeId) {
		this.mesajKimeId = mesajKimeId;
	}


	public String getMesajKimden() {
		return mesajKimden;
	}


	public void setMesajKimden(String mesajKimden) {
		this.mesajKimden = mesajKimden;
	}


	public String getMesajContent() {
		return mesajContent;
	}


	public void setMesajContent(String mesajContent) {
		this.mesajContent = mesajContent;
	}
	
	
	
	
}
