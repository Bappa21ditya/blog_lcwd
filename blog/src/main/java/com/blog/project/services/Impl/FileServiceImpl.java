package com.blog.project.services.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.blog.project.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		/// File name
		String name=file.getOriginalFilename();
		
		
		String  randomId=UUID.randomUUID().toString();
		String filename1= randomId.concat(name.substring(name.lastIndexOf(".")));
		//  Full Path
		String filepath=path+File.separator+filename1;
		
		
		// crate folder if not created 
		
		File f=new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		// file copy
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		return filename1;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		String fullpath=path+File.separator+filename;
		InputStream is=new FileInputStream(fullpath);
		// db logic to return inputstream
		return is;
		
	}


}
