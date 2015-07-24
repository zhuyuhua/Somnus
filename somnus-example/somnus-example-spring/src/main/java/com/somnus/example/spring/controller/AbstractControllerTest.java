package com.somnus.example.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class AbstractControllerTest extends AbstractController {

	private static final Logger logger = LogManager
			.getLogger(AbstractControllerTest.class);
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	         String msg="AbstractControllerTest";
	         ModelAndView mav=ModelAndViewUtils.getDefaultView();  
	        
	        logger.debug(msg);
	        mav.addObject("message",msg);
	         return mav;
	}

}
