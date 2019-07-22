package com.jbarillasgarcia.fileUploadPractice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Entity
@Table(name = "practice_fileEntity")
public class FileEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    @Column(name = "original_filename")
    private String originalFilename;
    
    @Column(name = "stored_filename")
    private String storedFilename;
    
    @Column(name = "content_type")
    private String contentType;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "created_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdTime;

    public FileEntity() {
    }

    public FileEntity(String originalFilename, String storedFilename, String mimeType, Long size, Date createdTime) {
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.contentType = mimeType;
        this.fileSize = size;        
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getStoredFilename() {
        return storedFilename;
    }

    public void setStoredFilename(String storedFilename) {
        this.storedFilename = storedFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    
}
