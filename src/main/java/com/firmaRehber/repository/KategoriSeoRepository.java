package com.firmaRehber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.KategoriSeo;
@Repository
public interface KategoriSeoRepository extends CrudRepository<KategoriSeo,Integer> {

}
