package com.example.stepDefinitions;


import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.pageObjects.HomePage;
import com.example.pageObjects.LoginPage;
import com.example.pageObjects.MyAccountPage;
import com.example.utilities.DataReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Steps {
	static WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage macc;

	List<HashMap<String, String>> datamap; //Data driven

	Logger logger; //for logging
	ResourceBundle rb; // for reading properties file
	String br; //to store browser name



	@Before
	public void setup()    //Junit hook - executes once before starting
	{
		//for logging
		logger= LogManager.getLogger(this.getClass());
		//Reading config.properties (for browser)
		rb=ResourceBundle.getBundle("config");
		br=rb.getString("browser");
		System.out.println("br value>>>>"+br);
	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status ======>"+scenario.getStatus());
		if(scenario.isFailed()) {

			TakesScreenshot ts=(TakesScreenshot) driver;
			byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png",scenario.getName());

		}
		driver.quit();
	}

	@Given("User Launch browser")
	public void user_launch_browser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		System.out.println("opened url....");
	}

	@Given("opens URL {string}")
	public void opens_url(String url) {
		System.out.println("url is>>>...."+url);
		driver.get(url);
		driver.manage().window().maximize();
	}


	@When("User navigate to MyAccount menu")
	public void user_navigate_to_my_account() {
		hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account ");

	}

	@When("click on Login")
	public void click_on_login() {
		hp.clickLogin();
		logger.info("Clicked on Login ");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
		lp=new LoginPage(driver);

		lp.setEmail(email);
		logger.info("Provided Email ");
		lp.setPassword(pwd);
		logger.info("Provided Password ");
	}
	@When("Select Role is {string}")
	public void select_role_is(String string) {

		lp.selectRoleType(string);
	}


@When("Click on Submit button")
public void click_on_submit_button() {
	lp.submitLogin();
	logger.info("Clicked on Login button");
}


	@Then("User navigates to {string}")
	public void user_navigates_to(String string) {
		
		String title=driver.getTitle();
		System.out.println("title>>>>"+title);
		System.out.println("string>>>>"+string);
		Assert.assertEquals(title, string);
	}
	




}
