package com.jbarillasgarcia.fileUploadPractice.dto;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
public class ResponseDto<T> {

    private String code;
    private String message;
    private T object;

    public ResponseDto() {
    }

    public ResponseDto(String code, String message, T object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }    
    
}
