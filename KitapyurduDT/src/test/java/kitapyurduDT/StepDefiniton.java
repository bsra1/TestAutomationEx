package kitapyurduDT;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefiniton {
	
WebDriver driver;
	
	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}
	
	@Given("Launch the browser")
	public void launch_the_browser() {
	    driver = new ChromeDriver();
	}
	
	@When("Launch the web site")
	public void launch_the_web_site() {
	    driver.navigate().to("https://www.kitapyurdu.com/");
		String actualTitle = driver.getTitle();
		String expectedTitle = "Kitapyurdu, Kitapla buluşmanın en kolay yolu";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@When("Launch the login page")
	public void launch_the_login_page() {
		WebElement logInButton = driver.findElement(By.cssSelector("div.menu-top-button.login"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();	
	}
	
	@When("Accept cookies")
	public void accept_cookies() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars");
	}
	
	@When("user enter valid data on the page")
	public void user_enter_valid_data_on_the_page(DataTable dataTable) {
	    String emailData = dataTable.cell(1, 1);
	    WebElement email = driver.findElement(By.cssSelector("input#login-email"));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.elementToBeClickable(email));
		email.sendKeys(emailData);
		String emailText = email.getText();
		Assert.assertNotNull(emailText);
		
		String passwdData = dataTable.cell(2, 1);
		WebElement password = driver.findElement(By.cssSelector("input#login-password"));
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait2.until(ExpectedConditions.elementToBeClickable(password));
		password.sendKeys(passwdData);
		String passwdText = password.getText();
		Assert.assertNotNull(passwdText);
	}
	
	@Then("Login should success")
	public void login_should_success() {
		driver.findElement(By.cssSelector("button.ky-btn.ky-btn-orange.w-100.ky-login-btn")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("button.ky-btn.ky-btn-orange.w-100.ky-login-btn")).isSelected());
	}
	
	@After
	public void cleanUp() throws InterruptedException {
		Thread.sleep(300);
		driver.quit();
	}

}
