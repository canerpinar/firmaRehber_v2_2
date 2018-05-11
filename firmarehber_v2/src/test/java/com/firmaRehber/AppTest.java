package com.firmaRehber;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.firmaRehber.service.KategoriService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	
	@Autowired
	private KategoriService kategoriService;
	
	
	@Test
	public void kategorAdd(){
		String json = "{ \"kategoriAd\" : \"halılar\" ,\"altKategori\" : [{\"kategoriAd\" : \"İran\",\"subAltKategori\" :[{\"kategoriAd\" : \"Som\"}"+
		",{\"kategoriAd\" : \"Kom\"}]},{\"kategoriAd\" : \"Turkish\",\"subAltKategori\" :[{\"kategoriAd\" : \"Göreme\"},{\"kategoriAd\" : \"Demirci\"}]}]}";
		
		
	}
	

}
