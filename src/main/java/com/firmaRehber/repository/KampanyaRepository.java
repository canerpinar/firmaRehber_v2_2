package com.firmaRehber.repository;

import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Kampanya;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface KampanyaRepository extends CrudRepository<Kampanya,Integer> {

}
