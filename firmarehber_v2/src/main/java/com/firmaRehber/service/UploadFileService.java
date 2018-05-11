package com.firmaRehber.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.firmaRehber.entity.Images;
import com.firmaRehber.repository.ImagesRepository;



@Service
public class UploadFileService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	public void saveFileForSlider(MultipartFile files, String uploadFilePath) {					
	        try {

	            byte[] bytes = files.getBytes();
	            Path path = Paths.get(uploadFilePath + files.getOriginalFilename());
	            Files.write(path, bytes);
	            //sj.add(file.getOriginalFilename());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	public void saveFileNameForSlider(Images images){
		imagesRepository.save(images);
	}
	
	public List<Images> getAllImagesForSlider(){
		return (List<Images>) imagesRepository.findAll();
	}
	
}
