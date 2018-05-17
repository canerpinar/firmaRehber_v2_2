package com.firmaRehber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.firmaRehber.entity.User;
import com.firmaRehber.entity.UserAuthenticaion;
import com.firmaRehber.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repository.findByUsername(username);
		List<String> userRoleList = null;
		if(user == null){
			 new UsernameNotFoundException("bulunamadı");
		}else{
			 userRoleList= repository.findRoleByUsername(username);
			return new UserAuthenticaion(user,userRoleList);
		}
		return new UserAuthenticaion(user, userRoleList);
	}
	
	public void saveUser(User user){
		repository.save(user);
	}
	
	public User getFirmaUserIsAuthentication(String username,String password) {
		return repository.getFirmaUserIsAuthentication(username, password);
	}

	
	
	
}
