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
package com.somnus.bdd.jbehave.core;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单个Story执行的核心类，所有单个Story执行必须继承这个,所有的Story名字是当前Story的名字，并且位置是在当前目录下。
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public abstract class CoreStory extends JUnitStory {

	private static Logger logger = LoggerFactory.getLogger(CoreStory.class);

	private final CrossReference xref = new CrossReference();

	public CoreStory() {
		configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
				.doIgnoreFailureInView(true).useThreads(1).useStoryTimeoutInSecs(60);
	}

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "true");
		// Start from default ParameterConverters instance
		ParameterConverters parameterConverters = new ParameterConverters();
		// factory to allow parameter conversion and loading from external
		// resources (used by StoryParser too)
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
				new LoadFromClasspath(embeddableClass), parameterConverters, new TableTransformers());
		// add custom converters
		parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
				new ExamplesTableConverter(examplesTableFactory));

		return new MostUsefulConfiguration()
				.useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
				.useStoryLoader(
						new LoadFromClasspath(embeddableClass))
				.useStoryParser(
						new RegexStoryParser(
								examplesTableFactory))
				.useStoryPathResolver(new UnderscoredCamelCaseResolver())
				.useStoryReporterBuilder(new StoryReporterBuilder()
						.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass)).withDefaultFormats()
						.withPathResolver(new ResolveToPackagedName()).withViewResources(viewResources)
						.withFormats(CONSOLE, TXT, HTML, XML).withFailureTrace(true).withFailureTraceCompression(true)
						.withCrossReference(xref))
				.useParameterConverters(parameterConverters)
				.useStepPatternParser(new RegexPrefixCapturingPatternParser("%")).useStepMonitor(xref.getStepMonitor());
	}

}
