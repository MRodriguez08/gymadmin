package com.gymadmin.repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class FileUtils {
	
	private FileUtils(){}
	
	public static String getUniqueName() throws NoSuchAlgorithmException {
		return UUID.randomUUID().toString();
	}

	public static String getFileExtension(String fileName) {
		return fileName.split("\\.")[fileName.split("\\.").length - 1];
	}
	
}
