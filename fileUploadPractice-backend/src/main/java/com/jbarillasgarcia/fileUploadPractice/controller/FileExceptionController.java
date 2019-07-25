package com.jbarillasgarcia.fileUploadPractice.controller;

import com.jbarillasgarcia.fileUploadPractice.dto.ResponseDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@ControllerAdvice
public class FileExceptionController {
    
    private final Logger logger = LoggerFactory.getLogger(FileExceptionController.class);

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseDto> maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException exc, HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug(String.format("@%s::maxUploadSizeExceededExceptionHandler(%d, HttpServletRequest, HttpServletResponse)", this.getClass().getName(), exc.getMaxUploadSize()));
        
        ResponseDto responseDto = new ResponseDto("ERROR", "Hey!|You cannot upload files larger than 20MB.", null);
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(responseDto);        
    }    
    
}
