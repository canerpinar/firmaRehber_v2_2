package com.firmaRehber.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.firmaRehber.entity.KampanyaZiyaret;
import com.firmaRehber.entity.Sube;
import com.firmaRehber.entity.Urun;

public interface KampanyaZiyaretRepository extends CrudRepository<KampanyaZiyaret,Integer>{

}
