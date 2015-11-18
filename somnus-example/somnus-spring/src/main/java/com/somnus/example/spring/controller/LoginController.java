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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.somnus.example.spring.domain.LoginForm;
import com.somnus.example.spring.model.User;
import com.somnus.example.spring.service.UserService;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, LoginForm command)
			throws Exception {
		logger.debug(command);
		String username = command.getUsername();

		User user = new User();
		user.setUserName(username);
		user.setPassword(command.getPassword());
		userService.addUser(user);

		ModelAndView view = ModelAndViewUtils.getDefaultView();
		view.addObject("message", user.toString());

		return view;
	}

	public void show() {
		System.out.println("-----show----");
	}
	
	public static void main(String[] args) {
		logger.debug("aaa");
	}

}
