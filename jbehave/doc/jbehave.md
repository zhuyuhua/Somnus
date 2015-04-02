### JBehave 备忘录

####1、JUnitStory、JUnitStories和JUnitStoryMaps区别

* org.jbehave.core.junit.JUnitStory
* org.jbehave.core.junit.JUnitStories
* org.jbehave.core.junit.JUnitStoryMaps


 1.  JUnitStory：执行单个Story文件，该Stroy文件名必须和Stories类名相同（驼峰的话使用下划线对应），Story文件必须和Story类同个根目录下。

	源码如下：org.jbehave.core.junit.JUnitStory.run()
		@Test
	    public void run() throws Throwable {        
	        Embedder embedder = configuredEmbedder();
	        StoryPathResolver pathResolver = embedder.configuration().storyPathResolver();
	        String storyPath = pathResolver.resolve(this.getClass());
	        try {
	            embedder.runStoriesAsPaths(asList(storyPath));
	        } finally {
	            embedder.generateCrossReference();
	        }
	    }
  

 2.  JUnitStories：可以执行多个Story文件，文件名称和路径可以自定义，定义的方法在：org.jbehave.core.junit.JUnitStories.storyPaths()
 
 
    