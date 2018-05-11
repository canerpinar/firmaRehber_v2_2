package com.firmaRehber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firmaRehber.entity.UserRole;
import com.firmaRehber.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public void saveRole(UserRole userRole){		
		repository.save(userRole);
	}
}
