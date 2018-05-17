package com.firmaRehber.repository;

import org.springframework.data.repository.CrudRepository;

import com.firmaRehber.entity.UserRole;

public interface RoleRepository extends CrudRepository<UserRole,Integer> {

}
