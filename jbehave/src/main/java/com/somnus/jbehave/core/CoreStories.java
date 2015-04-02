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
package com.somnus.jbehave.core;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML_TEMPLATE;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML_TEMPLATE;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.context.Context;
import org.jbehave.core.context.ContextView;
import org.jbehave.core.context.JFrameContextView;
import org.jbehave.core.embedder.PropertyBasedEmbedderControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ContextOutput;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ContextStepMonitor;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共核心Story运行示例，其他Stories必须继承这个类
 * 
 * @author:zhuyuhua
 * @date:2015年3月4日 下午5:16:50
 * @version 0.0.1
 */
public class CoreStories extends JUnitStories {

	private static Logger logger = LoggerFactory.getLogger(CoreStories.class);

	private final CrossReference xref = new CrossReference();
	private Context context = new Context();
	private Format contextFormat = new ContextOutput(context);
	private ContextView contextView = new JFrameContextView().sized(640, 120);
	private ContextStepMonitor contextStepMonitor = new ContextStepMonitor(
			context, contextView, xref.getStepMonitor());

	public CoreStories() {
		configuredEmbedder().embedderControls()
				.doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(false).doIgnoreFailureInView(true)
				.doVerboseFailures(true).useThreads(2)
				.useStoryTimeoutInSecs(60);
		configuredEmbedder().useEmbedderControls(
				new PropertyBasedEmbedderControls());

	}

	@Override
	public Configuration configuration() {
		// avoid re-instantiating configuration for the steps factory
		// alternative use #useConfiguration() in the constructor
		if (super.hasConfiguration()) {
			return super.configuration();
		}
		Class<? extends Embeddable> embeddableClass = this.getClass();
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "true");
		viewResources.put("reports", "ftl/jbehave-reports-with-totals.ftl");
		// Start from default ParameterConverters instance
		ParameterConverters parameterConverters = new ParameterConverters();
		// factory to allow parameter conversion and loading from external
		// resources (used by StoryParser too)
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
				new LocalizedKeywords(),
				new LoadFromClasspath(embeddableClass), parameterConverters,
				new TableTransformers());
		// add custom converters
		parameterConverters.addConverters(new DateConverter(
				new SimpleDateFormat("yyyy-MM-dd")),
				new ExamplesTableConverter(examplesTableFactory));
		return new MostUsefulConfiguration()
				.useStoryLoader(new LoadFromClasspath(embeddableClass))
				.useStoryParser(new RegexStoryParser(examplesTableFactory))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(
										CodeLocations
												.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withViewResources(viewResources)
								.withFormats(contextFormat, CONSOLE, TXT,
										HTML_TEMPLATE, XML_TEMPLATE)
								.withFailureTrace(true)
								.withFailureTraceCompression(true)
								.withCrossReference(xref))
				.useParameterConverters(parameterConverters)
				// 使用 '%' 代替 '$' 来标示参数
				.useStepPatternParser(
						new RegexPrefixCapturingPatternParser("%"))
				.useStepMonitor(contextStepMonitor);
	}

	@Override
	protected List<String> storyPaths() {

		String filter = System.getProperty("story.filter", "**/*.story");
		return new StoryFinder().findPaths(
				codeLocationFromClass(this.getClass()), filter,
				"**/failing_before*.story");
	}

}
