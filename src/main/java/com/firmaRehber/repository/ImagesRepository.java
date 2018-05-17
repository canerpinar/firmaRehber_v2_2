package com.firmaRehber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Images;

@Repository
public interface ImagesRepository extends CrudRepository<Images,Integer> {

}
