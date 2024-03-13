package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Value("${upload.dir}")
	private String UPLOAD_DIRECTORY;
	
	public String generateUniqueFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        String uniqueID = UUID.randomUUID().toString();
        String uniqueFileName = hashString(originalFilename + uniqueID) + extension;
        return uniqueFileName;
    }
	
	private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf("."));
        } else {
            return "";
        }
    }

    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
        	throw new RuntimeException("Falha para geração do HASH do arquivo.");
        }
    }
    
    public Resource getFileByName(String directory, String fileName) {
		String filePath = this.UPLOAD_DIRECTORY + directory + fileName;
		return new FileSystemResource(filePath);
	}
    
    public void deleteFile(String directory, String fileName) {
    	String filePath = this.UPLOAD_DIRECTORY + directory + fileName;
    	Path fileNameAndPath = Paths.get(filePath).normalize().toAbsolutePath();
    	if (Files.exists(fileNameAndPath)) {
    		try {
    			Files.delete(fileNameAndPath);
			} catch (IOException  e) {
				throw new RuntimeException("Falha ao deletar arquivo.");
			}
		}
	}
	
	public String saveFile(String directory, MultipartFile file) {
		try {
			String fileName = this.generateUniqueFileName(file);
			File directoryPath = new File(this.UPLOAD_DIRECTORY + directory);
			if (!directoryPath.exists()) {
				directoryPath.mkdirs();
			}
			Path fileNameAndPath = Paths.get(directoryPath.getPath(), fileName).normalize().toAbsolutePath();
			Files.write(fileNameAndPath, file.getBytes());
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException("Falha para salvar o arquivo.");
		}
	}
	
}
