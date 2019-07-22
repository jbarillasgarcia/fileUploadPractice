/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbarillasgarcia.fileUploadPractice.dao;

import com.jbarillasgarcia.fileUploadPractice.model.FileEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
public interface FileRepository extends CrudRepository<FileEntity, Long>{
    
}
