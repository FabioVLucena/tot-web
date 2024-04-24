package com.aeon.tot.profile.api.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aeon.tot.profile.api.entity.File;
import com.aeon.tot.profile.api.exception.WarningException;
import com.aeon.tot.profile.api.repository.FileRepository;

@Service
public class FileService {

	private FileRepository fileRepository;
	
	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	public File storeFile(MultipartFile multipartFile) throws Exception {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		if (fileName.contains("..")) {
			throw new WarningException("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		File file = new File(fileName,
				multipartFile.getContentType(),
				multipartFile.getBytes());
		
		return this.fileRepository.save(file);
	}

	public void deleteFile(File file) {
		this.fileRepository.delete(file);
	}
}
