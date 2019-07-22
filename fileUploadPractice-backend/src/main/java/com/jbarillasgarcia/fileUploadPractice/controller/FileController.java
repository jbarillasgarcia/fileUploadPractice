package com.jbarillasgarcia.fileUploadPractice.controller;

import com.jbarillasgarcia.fileUploadPractice.dto.ResponseDto;
import com.jbarillasgarcia.fileUploadPractice.model.FileEntity;
import com.jbarillasgarcia.fileUploadPractice.service.FileService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@RestController
@RequestMapping("/file")
public class FileController {
    
    private final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private FileService fileService;
    
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> upload(@RequestParam("file") MultipartFile file) {
        this.logger.debug(String.format("@%s::upload(%s)", this.getClass().getName(), file.getOriginalFilename()));
        
        ResponseDto<FileEntity> response = new ResponseDto();
                
        try {
            this.logger.info(String.format("Uploading file %s", file.getOriginalFilename()));
            
            response.setObject(this.fileService.save(file));            
            response.setCode("OK");
            response.setMessage("Success");
        } catch (Exception e) {
            this.logger.error(String.format("Couldn't upload file. %s", e.getMessage()));
            
            response.setCode("ERROR");
            response.setMessage(e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> list() {
        ResponseDto<List> response = new ResponseDto();
        
        try {            
            response.setObject(this.fileService.list());            
            response.setCode("OK");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("ERROR");
            response.setMessage(e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

}
