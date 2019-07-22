package com.jbarillasgarcia.fileUploadPractice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Configuration
public class FileConfiguration {

    @Value("${fileUpload.fileDirectory}")
    private String fileDirectory;

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }    
    
}
