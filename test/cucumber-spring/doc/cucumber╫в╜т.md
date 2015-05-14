
##注解

	@RunWith(Cucumber.class)
	@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report",
			"json:target/cucumber-json-report.json" }, features = { "classpath:features/account/transfer" })
	public class BankAccountTransferTest {
	}


* @RunWith 就是告诉 JUnit 用我的 Cucumber 这个 Runner 来跑测试。
* @CucumberOptions中的 plugin 指定生成的测试报告，后续可以 target/ 目录下找到。
* @CucumberOptions中的 features 指定 features 文件的路径，该例中是 classpath 下的 features/account/transfer 目录下。

