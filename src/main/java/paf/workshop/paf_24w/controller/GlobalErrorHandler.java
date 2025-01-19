package paf.workshop.paf_24w.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalErrorHandler {
    
    @ExceptionHandler({DataAccessException.class, Exception.class})
    public ModelAndView errorPage(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("msg", e.getMessage());
        return mav;
    }
}
