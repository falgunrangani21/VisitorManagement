package com.efive.VisitorManagement.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("FileUploadUtil")
public class FileUploadUtilImp implements FileUploadUtil {
	
	@Autowired
	HttpServletRequest request ;

	private  Path  rootLocation = Paths.get("/");
	@Override
	public String uploadFileFromServer(MultipartFile file1,String newFilename,String dirName) {
	 	String extention  =null;
	 	String uploadpath=null;
		String fileName = file1.getOriginalFilename();
		System.out.println(fileName);
		try{
			String rootPath = System.getProperty("user.dir");
			String path = rootPath + File.separator+"src"+File.separator+"main"+ File.separator + "resources"+File.separator+"static"+ File.separator+ "uploadimage"+File.separator+dirName+""+File.separator+"";
			rootLocation =Paths.get(path);
			File dir = new File(path);
			System.out.println("root path : "+path);
			extention = fileName.substring(fileName.lastIndexOf("."));
			uploadpath = path + newFilename+ extention;
			
			if (!dir.exists())
				dir.mkdirs();
			
			File  f = new File(uploadpath);
		  	 if(f.exists()){	// if File is Exist   then  Existing  file will be delete
		  		 f.delete();	
		  	 }
		  	 Files.copy(file1.getInputStream(), this.rootLocation.resolve(newFilename+extention));
			System.out.println("uploaded Path:"+uploadpath);
			return uploadpath; 
		}
		catch (Exception e) 
		{
		throw new RuntimeException("FAIL!");
		}
		
	}

}