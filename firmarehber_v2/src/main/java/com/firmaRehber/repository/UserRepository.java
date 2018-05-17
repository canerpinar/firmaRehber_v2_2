package com.firmaRehber.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.User;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByUsername(String username);
	@Query("select r.role from UserRole r,User user where user.username=:username and r.roleUsername=user.username")
	public List<String> findRoleByUsername(@Param("username") String username);
	
	@Query("select user from User user where user.username=:username and user.password=:password")
	public User getFirmaUserIsAuthentication(@Param("username")String username,@Param("password")String password);
	
}
