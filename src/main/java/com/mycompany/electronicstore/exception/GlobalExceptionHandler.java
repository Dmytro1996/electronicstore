/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.exception;

import java.io.IOException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author dmytr
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ModelAndView nullEntityReferenceExceptionHandler(HttpServletRequest request,
            NullEntityReferenceException exception) {
        logger.info("NullEntityReferenceException");
        return getModelAndView(request, HttpStatus.BAD_REQUEST, exception);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView entityNotFoundExceptionHandler(HttpServletRequest request,
            EntityNotFoundException exception) {
        logger.info("EntityNotFoundException");
        return getModelAndView(request, HttpStatus.NOT_FOUND, exception);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView internalServerErrorHandler(HttpServletRequest request, Exception exception) {
        logger.info("Not Found");        
        return getModelAndView(request, HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }    
        
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleAccessDenied(HttpServletRequest request,AccessDeniedException accessEx){
        logger.info("AccessDenied");
        return getModelAndView(request, HttpStatus.FORBIDDEN, accessEx);
    }
        
    private ModelAndView getModelAndView(HttpServletRequest request, HttpStatus httpStatus, Exception exception) {        
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", httpStatus.value() + " / " + httpStatus.getReasonPhrase());
        modelAndView.addObject("message", exception.getMessage()+exception.getClass().getCanonicalName());
        return modelAndView;
    }
}
