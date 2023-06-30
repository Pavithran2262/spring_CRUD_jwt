package com.example.CRUD.ExcetionHandling;

import com.example.CRUD.ApiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalException {

    @Autowired
    private ApiResponse apiResponse;
@ExceptionHandler(IllegalAuthException.class)
    public ApiResponse handleIllegalAuthException(IllegalAuthException c){
    apiResponse.setMessage("Handled Global Exception");
    apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    apiResponse.setData(null);
    return apiResponse;
}

}