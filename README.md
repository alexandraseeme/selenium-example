# selenium-example

The project serves as example of UI test automation</br>

Automation framework: Selenium WebDriver 3.0
</br>
Testing framework: TestNG
</br>
Reporting extension: Allure
</br>

The following patterns, approaches and extensions are used in the project:
- Page object pattern
- Factory approach
- Logger (log4j)
- Property Reader
- Test Listeners 
- Screenshooting

<b>Getting Started</b>

To run the tests you need to install maven and git. 
<pre>
$ git clone https://github.com/alexandraseeme/selenium-example.git
$ mvn clean test
</pre>

To generate Allure Report you should perform following steps:
<pre>$ mvn io.qameta.allure:allure-maven:serve</pre>
