package com.firmaRehber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * user_role uses to uses company for their role
 * */
@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="role_username")
	private String roleUsername;
	
	public String getRoleUsername() {
		return roleUsername;
	}

	public void setRoleUsername(String roleUsername) {
		this.roleUsername = roleUsername;
	}

	@Column(name="rolename")
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}