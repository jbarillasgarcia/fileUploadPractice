package com.jbarillasgarcia.fileUploadPractice.service;

import com.jbarillasgarcia.fileUploadPractice.dao.FileRepository;
import com.jbarillasgarcia.fileUploadPractice.model.FileEntity;
import com.jbarillasgarcia.fileUploadPractice.util.FileConfiguration;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Service
public class FileService {
    
    private final Logger logger = LoggerFactory.getLogger(FileService.class);
    
    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private FileRepository fileRepository;
    
    public FileEntity save(MultipartFile file) throws Exception {
        File internalFile = File.createTempFile(
                "fileUploadPractice_", // filename prefix
                String.format(".%s", FilenameUtils.getExtension(file.getOriginalFilename())), // keep original extension
                new File(this.fileConfiguration.getFileDirectory())); // folder to store files
        
        // Transfer the file to the recently created temporary file
        file.transferTo(internalFile);
                
        FileEntity newFile = new FileEntity(
                file.getOriginalFilename(),
                internalFile.getName(),
                file.getContentType(),
                file.getSize(),                
                new Date()
        );
        
        return this.fileRepository.save(newFile);
    }
    
    public List<FileEntity> list() {
        return (List<FileEntity>) this.fileRepository.findAll();
    }
    
}
