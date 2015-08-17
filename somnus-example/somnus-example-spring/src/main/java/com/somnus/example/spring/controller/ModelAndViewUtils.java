/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.somnus.example.spring.controller;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public final class ModelAndViewUtils {

	private static final String DEFAULT_VIEW_NAME = "result/result";

	public static ModelAndView getModelAndView(String viewName, Map<String, ?> model) {

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
