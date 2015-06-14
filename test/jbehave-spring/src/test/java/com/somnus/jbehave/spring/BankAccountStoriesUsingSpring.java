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
package com.somnus.jbehave.spring;

import java.util.List;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.somnus.jbehave.core.CoreStories;

/**
 * 使用Spring来运行核心Story
 * 
 * @author:zhuyuhua
 * @date:2015年3月4日 下午5:24:09
 * @version 0.0.1
 */
public class BankAccountStoriesUsingSpring extends CoreStories {

	private static Logger logger = LoggerFactory
			.getLogger(BankAccountStoriesUsingSpring.class);

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), createContext());
	}

	protected ApplicationContext createContext() {
		return new SpringApplicationContextFactory("steps.xml")
				.createApplicationContext();
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()),
				"**/*.story", "");

	}

}
