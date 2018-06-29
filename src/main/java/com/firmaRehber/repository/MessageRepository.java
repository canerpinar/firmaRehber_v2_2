package com.firmaRehber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{

	@Query("select mesaj from Message mesaj where mesaj.mesajKimeId=:id")
	public List<Message> getAllMessageFromFirma(@Param("id") int id);
}
