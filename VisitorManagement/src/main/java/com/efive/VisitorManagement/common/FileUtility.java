package com.efive.VisitorManagement.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtility {
 
	static HttpServletRequest request;

	// File Upload To server
	public static String uploadFile(MultipartFile fileObj, String currentFilename,
			String dirName, String filenameSaveAs) {
		try {
			File theDir = null;
			String extention = null;
			String uploadpath = null;

			String serverPath = request.getContextPath(); //.getRealPath("/"); // or Contex path

			if (null != fileObj && fileObj.getSize() > 0) {
				extention = currentFilename.substring(currentFilename
						.lastIndexOf("."));
				uploadpath = "uploadcontent/" + dirName + "/" + filenameSaveAs
						+ extention;

				// Now Check Is Dir Exist
				theDir = new File("uploadcontent/" + dirName + "/");
				if (!theDir.exists())
					theDir.mkdir(); // It will create Directories according to
									// 'theDir' path

				// Now Check is File already Exist
				File f = new File(uploadpath);
				if (f.exists()) { // if File is Exist then Existing file will be
									// delete
					f.delete();
				}

				// Now File Copy in Dir
				File srcFile = new File(request.getRealPath(uploadpath));
				try {
					FileUtils.copyFile((File) fileObj, srcFile); // File will copy
															// source obj to
															// Destination Dir
					return uploadpath;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Delete File from server
	public boolean fileDelete(File fileObj) {
		try {
			if (fileObj.exists()) // if File is Exist then Existing file will be
									// delete
				fileObj.delete();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// File Rename
	// File Move to another Dir
	public static String newUploadFile(File fileObj, String currentFilename ,String dirName, String  filenameSaveAs,String serverPath){
		  try{
				  File theDir =null;
				  String extention  =null;
				  String uploadpath=null;
				  
				  	if(null!=fileObj  && fileObj.length()>0){
					  		extention = currentFilename.substring(currentFilename.lastIndexOf("."));
					  		uploadpath = "/"+dirName+"/"+filenameSaveAs+ extention;
					  		
					  	// Now Check Is Dir   Exist  
					  		theDir =  new File(serverPath+"/"+dirName+"/");
					  		if (!theDir.exists()) 
					  			theDir.mkdirs();	 	// It will create Directories according to  'theDir' path 
					  		
					  		// Now Check is File already Exist
						  		File  f = new File(serverPath+"/"+uploadpath);
						  		if(f.exists()){	// if File is Exist   then  Existing  file will be delete
									f.delete();			
								}
						  		
						  		//Now File Copy in Dir 
						  		File srcFile = new File(serverPath+"/"+uploadpath);
						  		try {	
						  				Files.copy(new FileInputStream(fileObj),srcFile.toPath());
						  				//FileUtils.copyFile(fileObj,srcFile);		// File will copy source obj  to Destination Dir
						  				return uploadpath;
									} catch (IOException e) {
										e.printStackTrace();
									}
				  	}
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return null;
	}
	
	public static File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
}
