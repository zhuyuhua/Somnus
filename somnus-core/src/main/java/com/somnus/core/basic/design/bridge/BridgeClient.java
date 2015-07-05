package com.somnus.core.basic.design.bridge;

/**
 * 定义：将抽象部分与它的实现部分分离，使它们可以独立的变化。
 * 
 * 桥接模式的结构:
 * 
 * Abstraction ：抽象类的接口，维护一个指向Implementor类型对象的指针，在本例中为 IPluginPlatform。
 * 
 * RefinedAbstraction ：扩充Abstraction定义的接口，在本例中为 PluginVersionA、PluginVersionB。
 * 
 * Implementor ：定义实现类的接口，该接口不一定要与Abstraction
 * 完全一致；事实上，这两个接口可以完全不同。一般来讲Implementor 接口仅提供基本操作，而Abstraction
 * 则定义了基于这些基本操作的较高层次的操作。
 * 
 * ConcreteImplementor ：实现Implementor 并定义它的具体实现。
 * 
 * @author joe
 *
 */
public class BridgeClient {

	public static void main(String[] args) {
		IPlugin plugin = new PluginVersionA();
		IPluginPlatform platform = new WindowPlugin();
		plugin.setPlatform(platform);
		plugin.start();
		plugin.stop();

	}

}

// 抽象类接口 -->插件
abstract class IPlugin {
	protected IPluginPlatform platform;

	public void setPlatform(IPluginPlatform platform) {
		this.platform = platform;
	}

	public abstract void start();

	public abstract void stop();
}

class PluginVersionA extends IPlugin {

	@Override
	public void start() {
		platform.loadPlugin();
	}

	@Override
	public void stop() {
		platform.unloadPlugin();
	}

}

// 抽象类接口 --> 平台
abstract class IPluginPlatform {
	public abstract void loadPlugin();
	public abstract void unloadPlugin();
}

class WindowPlugin extends IPluginPlatform {

	@Override
	public void loadPlugin() {
		System.out.println("WindowPlugin loading...");
	}

	@Override
	public void unloadPlugin() {
		System.out.println("WindowPlugin unloading...");
	}

}

class LinuxPlugin extends IPluginPlatform {

	@Override
	public void loadPlugin() {
		System.out.println("LinuxPlugin loading ...");
	}

	@Override
	public void unloadPlugin() {
		System.out.println("LinuxPlugin unLoading ...");
	}
}
