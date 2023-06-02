package com.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//locators +WebElement 
	
	@FindBy(xpath = "//*[@id='eml']")
	WebElement email;
	
	@FindBy(xpath = "//*[@id=\"pass\"]")
	WebElement password;
	
	@FindBy(xpath = "//*[@name=\"type\"]")
	WebElement roleType;
	
	@FindBy(xpath = "//button")
	WebElement submit;
	

	//All Actions
	
	public void setEmail(String emaildata)
	{
		email.sendKeys(emaildata);
	}
	
	public void setPassword(String passwordData)
	{
		password.sendKeys(passwordData);
	}
	
	
	public void selectRoleType(String role)
	{
		Select roleList=new Select(roleType);
		roleList.selectByVisibleText(role);
	}
	
	public void selectAdminRole()
	{
		Select roleList=new Select(roleType);
		roleList.selectByValue("1");
	}
	
	public void submitLogin()
	{
		submit.click();
	}
}
