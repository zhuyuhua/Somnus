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
package com.somnus.bdd.jbehave.core.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.annotations.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stories/Stroy/Scenario 执行前后的打印日志
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class BeforeAfterSteps {

	private static Logger logger = LoggerFactory.getLogger(BeforeAfterSteps.class);

	@BeforeStories
	public void beforeStories() {
		logger.debug("Before Stories ...");
	}

	@AfterStories
	public void afterStories() {
		logger.debug("After Stories ...");
	}

	@BeforeStory
	public void beforeStory(@Named("author") String author) {
		if (author.length() > 0) {
			logger.debug("This story is authored by " + author);
		} else {
			logger.debug("Before Story ...");
		}
	}

	@AfterStory
	public void afterStory(@Named("theme") String theme) {
		if (theme.length() > 0) {
			logger.debug("After Story with theme '" + theme + "'.");
		} else {
			logger.debug("After Story ...");
		}
	}

	@BeforeStory(uponGivenStory = true)
	public void beforeGivenStory() {
		logger.debug("Before Given Story ...");
	}

	@AfterStory(uponGivenStory = true)
	public void afterGivenStory() {
		logger.debug("After Given Story ...");
	}

	@BeforeScenario
	public void beforeScenario(@Named("theme") String theme) {
		if (theme.length() > 0) {
			logger.debug("Before Normal Scenario with theme: " + theme);
		} else {
			logger.debug("Before Normal Scenario ...");
		}
	}

	@BeforeScenario(uponType = ScenarioType.EXAMPLE)
	public void beforeExampleScenario() {
		logger.debug("Before Example Scenario ...");
	}

	@BeforeScenario(uponType = ScenarioType.ANY)
	public void beforeAnyScenario() {
		logger.debug("Before Any Scenario ...");
	}

	@AfterScenario(uponType = ScenarioType.NORMAL)
	public void afterScenario(@Named("variant") String variant, @Named("theme") String theme) {
		if (variant.length() > 0 && theme.length() > 0) {
			logger.debug("After Normal Scenario with variant '" + variant + "' and theme '" + theme + "'.");
		} else {
			logger.debug("After Normal Scenario with any outcome ...");
		}
	}

	@AfterScenario(uponType = ScenarioType.NORMAL, uponOutcome = Outcome.FAILURE)
	public void afterFailedScenario(@Named("theme") String theme) {
		if ("parametrisation".equals(theme)) {
			logger.debug("After Normal Scenario with failed outcome with theme 'parametrisation'.");
		} else {
			logger.debug("After Normal Scenario with failed outcome ...");
		}
	}

	@AfterScenario(uponType = ScenarioType.NORMAL, uponOutcome = Outcome.SUCCESS)
	public void afterSuccessfulScenario() {
		logger.debug("After Normal Scenario with successful outcome ...");
	}

	@AfterScenario(uponType = ScenarioType.EXAMPLE)
	public void afterExampleScenario() {
		logger.debug("After Example Scenario ...");
	}

	@AfterScenario(uponType = ScenarioType.ANY)
	public void afterAnyScenario() {
		logger.debug("After Any Scenario ...");
	}

	@Given("a setup")
	public void givenASetup() {
		logger.debug("Doing a setup");
	}

	@Then("a teardown")
	public void thenATeardown() {
		logger.debug("Doing a teardown");
	}

	public static void main(String[] args) {
		logger.debug(BeforeAfterSteps.class.getName());
	}
}
