package com.JBank.src.features;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

import io.github.bonigarcia.wdm.WebDriverManager;


public class HomePageFeaturesTest {
	
	private static final String scriptPath = System.getProperty("user.dir") + "\\scripts\\frontendStart.sh";
	private static Process p;
	
	private WebDriver driver;
	
	private String expectedText;
	private WebElement testElement;
	
	private Callable<Boolean> pageLoaded(final WebElement element, final String expected) {
		return new Callable<Boolean>() {
			public Boolean call() throws Exception {
				return element.getText().contains(expected);
			}
		};
	}
	
	@BeforeClass
	public static void serverSetup() throws IOException {
		p = Runtime.getRuntime().exec("cmd.exe /c" + scriptPath);
		WebDriverManager.firefoxdriver().setup();	
	}

	@AfterClass
	public static void serverClose() throws Exception {
		p.destroy();
		Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe"); // for geckodriver
	}
	
	@Before
	public void initialize() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:3000/home");
		await().atMost(4, SECONDS).until(pageLoaded(driver.findElement(By.tagName("body")), "Stock Train"));
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	// display tests
	// 1 - Navbar
	
	private void navBarAssertions(String expected) {
		testElement = driver.findElement(By.tagName("nav"));
		assertTrue(testElement.getText().contains(expected));
	}
	
	@Test
	public void pageHasLogo() {
		navBarAssertions("Stock Train");
	}
	
	@Test
	public void pageHasHome() {
		//navBarAssertions("Home");
	}
	
	@Test
	public void pageHasLogin() {
		navBarAssertions("Log In");
	}
	
	@Test
	public void pageHasSignUp() {
		navBarAssertions("Sign Up");
	}
	
	@Test
	public void pageHasSearch() {
		navBarAssertions("Search");
	}
	
	// signup actions
	
	@Test
	public void signUpTestClickFormShowsUsernameField() {
		
	}
}
