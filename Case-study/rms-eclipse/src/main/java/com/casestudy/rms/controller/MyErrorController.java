package com.casestudy.rms.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Represents an Error Controller.
 */
@Controller
public class MyErrorController implements ErrorController {

	/**
	 * Method handles error.
	 * 
	 * @return error page
	 */
	@RequestMapping("/error")
	public String handleError() {
		// do something like logging
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}