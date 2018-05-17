package com.firmaRehber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{

}
