package com.gymadmin.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.gymadmin.config.Constants;
import com.gymadmin.repository.FileUtils;
import com.gymadmin.repository.JSonFactory;

@Controller
public class FileUploadController {
		
	@Autowired
	private ServletContext servletContext;

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/imageupload", method = RequestMethod.POST)
    public void customerAvatarUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file ) throws IOException, NoSuchAlgorithmException {

        byte[] bytes;
        String imagesPath = servletContext.getRealPath(Constants.CUSTOMERS_IMAGE_PATH);

        if (!file.isEmpty()) {
    		bytes = file.getBytes();
            //convert array of bytes into file
            String uniqueName = FileUtils.getUniqueName() + "." + FileUtils.getFileExtension(file.getOriginalFilename());
    		FileOutputStream fileOuputStream = new FileOutputStream(imagesPath + "/" +  uniqueName);
    		request.getSession().setAttribute(Constants.PENDING_FILE_SESS_VAR, uniqueName);
     	    fileOuputStream.write(bytes);
     	    fileOuputStream.close();             
        }
    }
    

}
