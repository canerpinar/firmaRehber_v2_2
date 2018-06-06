package com.firmaRehber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.SubeKampanya;

@Repository
public interface SubeKampanyaRepository extends CrudRepository<SubeKampanya, Integer> {

}
