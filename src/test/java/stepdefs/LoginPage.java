package stepdefs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Keyword.ActionClass1;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import locators.PageObjects;

public class LoginPage {

	WebDriver driver = null;

	ActionClass1 actionclass1obj;
	PageObjects pageobj;

	@Before
	public void launch_Browser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rishabh.jain\\eclipse-workspace\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	// @Before("@First,@Second")
	// public void before_second_scenario()
	// {
	// System.out.println("This will execute before the execution of the scenario
	// tagged with @First and @Second both");
	//
	// }
	// public void running_in_background()
	// {
	//
	// }
	@Given("^I have open the browser$")
	public void openBrowser() {

	}

	@And("^I navigate to Available examples website$")
	public void navigate_To_Examples_Website() {
		driver.get("http://10.0.31.161:9292/");

	}

	@When("I navigate to Form Authentication")
	public void clicking_Form_Authentication_Link() {

		driver.findElement(By.linkText("Form Authentication")).click();

	}

	@Then("Login page will display")
	public void check_Login_Page() {

		Assert.assertEquals("http://10.0.31.161:9292/login", driver.getCurrentUrl());
		driver.close();
	}

	@Given("^I have the LoginPage$")
	public void openBrowser1() {
		actionclass1obj = new ActionClass1(driver);
		pageobj = new PageObjects();
		driver.get("http://10.0.31.161:9292/login");
	}

	@When("^I enter correct username (.*) and correct password (.*)$")
	public void i_enter_username_tomsmith_and_password_SuperSecretPassword(String userName, String password)
			throws Throwable {

		actionclass1obj.sending_Credentials(userName, password);
		pageobj.get_Login_Button(driver).click();
	}

	@Then("^I will be  successfully logged in$")
	public void i_am_able_to_login_using_correct_credentials() throws Throwable {
		Assert.assertEquals("http://10.0.31.161:9292/secure", driver.getCurrentUrl());
		driver.close();

	}

	
	@When("^I enter incorrect username (.*) and incorrect password (.*)$")
	public void i_enter_incorrect_username_and_incorrect_password(String userName, String password) throws Throwable {
		System.out.println("usrname" + userName);
		System.out.println("pwd" + password);

		actionclass1obj.sending_Credentials(userName, password);
		pageobj.get_Login_Button(driver).click();
	}

	@Then("^I will see an error message$")
	public void i_am_able_to_see_error_msg() throws Throwable {
		boolean check = false;
		check = driver.findElement(By.xpath("//*[@id=\"flash\"]")).isDisplayed();
		Assert.assertTrue(check);
		driver.close();

	}

	@Given("^I have successfully logged in$")
	public void navigate_to_secure_area() {
		driver.get("http://10.0.31.161:9292/login");
		actionclass1obj = new ActionClass1(driver);
		pageobj = new PageObjects();
		actionclass1obj.sending_Credentials("tomsmith", "SuperSecretPassword!");
		pageobj.get_Login_Button(driver).click();
	}

	@And("^Logout button is displayed$")
	public void logout_button_is_displayed() {
		boolean check = false;
		check = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a")).isDisplayed();
		Assert.assertTrue(check);

	}

	@When("^I Logout$")
	public void click_on_Logout() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/a")).click();
	}

	@Then("^I will navigate back to Login page$")
	public void should_navigate_back_to_Login_page() {
		Assert.assertEquals("http://10.0.31.161:9292/login", driver.getCurrentUrl());
		driver.close();
	}

	

}
