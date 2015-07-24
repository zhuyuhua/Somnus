package com.somnus.example.spring.controller;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public final class ModelAndViewUtils {

	private static final String DEFAULT_VIEW_NAME = "result/result";

	public static ModelAndView getModelAndView(String viewName,
			Map<String, ?> model) {

		ModelAndView mav = new ModelAndView(viewName, model);
		return mav;
	}

	public static ModelAndView getModelAndView(String viewName) {
		return getModelAndView(viewName, null);
	}

	public static ModelAndView getModelAndView(Map<String, ?> model) {
		return getModelAndView(DEFAULT_VIEW_NAME, model);
	}

	public static ModelAndView getDefaultView() {
		return getModelAndView(DEFAULT_VIEW_NAME);
	}

}
