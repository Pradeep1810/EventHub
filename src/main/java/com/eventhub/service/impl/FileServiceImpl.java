package com.eventhub.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventhub.service.FileService;




@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadFile(String path, MultipartFile file) {
		// getting file name
		
		String fileName = file.getOriginalFilename();
		
		// Create a random name for our file to be stored in the db 
		
		String randomId = UUID.randomUUID().toString();
		
		// adding the extension of our uploaded file 
		
		String fileNameDb = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		// full storage path 
		
		String fileStoragePath = path + File.separator + fileNameDb;
		
		//creating the storage folder if it doesn't exists (for now img ) 
		
		File f = new File(path);
		
		if(!f.exists()) {
			
			f.mkdir();
			
		}
		
		// coping the file in our folder / directory 
		
		try {
			Files.copy(file.getInputStream(),Paths.get(fileStoragePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileNameDb;
	}

	
	
}
