package com.firmaRehber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firmaRehber.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{

	@Query("select DISTINCT mesaj.mesajKimden,mesaj.gonderenUyemi,mesaj.gonderenId,mesaj.mesajKimeId from Message AS mesaj where mesaj.mesajKimeId=:id")
	public List<Object[]> getAllMessageFromFirma(@Param("id") int id);
	
	@Query("select mesaj from Message mesaj where mesaj.mesajKimden=:kimden and mesaj.okunmaDurum=false")
	public List<Message> getMessageOkunmamis(@Param("kimden")String kimden);
	
	@Query("select mesaj from Message mesaj where (mesaj.mesajKimeId=:kime_id and mesaj.gonderenId=:gonderen_id) or (mesaj.mesajKimeId=:gonderen_id and mesaj.gonderenId=:kime_id)")
	public List<Message> getAllMessageFromFirmaForList(@Param("kime_id") int kime_id,@Param("gonderen_id")int gonderen_id);
	
	//@Query("select mesaj from Message mesaj where ")
	
	@Query("select Count(mesaj) from Message mesaj where mesaj.mesajKimeId=:kime_id and mesaj.okunmaDurum=false")
	public int getOkunmamisMessageCount(@Param("kime_id")int kime_id);
}
