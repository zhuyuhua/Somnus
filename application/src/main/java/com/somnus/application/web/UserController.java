/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.somnus.cucumber.isolaction.User;
import com.somnus.cucumber.isolaction.UserRepository;

/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年2月10日 下午5:33:29
 * @version 0.0.1
 */
@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		User user = userRepository.findOne(id);
		// User user = new User("aaa");
		// List<Message> list = new ArrayList<Message>();
		// list.add(new Message(user, "aaaa"));

		// user.setMessages(list);
		model.addAttribute("user", user);

		return "users/show";
	}

	@RequestMapping("/add/{name}")
	public String add(@PathVariable String name) {
		User user = new User(name);
		userRepository.save(user);

		return "redirect:/users/" + user.getId();
	}

}
