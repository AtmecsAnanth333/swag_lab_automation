package tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyTheFilter {
	WebDriver driver;
	Properties properties;

	@BeforeTest
	public void propertie() throws IOException {
		String userDireString = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filepathString = userDireString + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "tasks" + pathSeparator + "configsaucelab.properties";
		File file = new File(filepathString);
		FileInputStream fileReder = new FileInputStream(file);
		properties = new Properties();
		properties.load(fileReder);
	}

	@Test
	public void browse() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(properties.getProperty("link"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		WebElement userName = driver.findElement(By.xpath(properties.getProperty("userName")));
		userName.sendKeys("standard_user");

		WebElement password = driver.findElement(By.xpath(properties.getProperty("password")));
		password.sendKeys("secret_sauce");

		boolean logoIsDisplayed = driver.findElement(By.xpath(properties.getProperty("login"))).isDisplayed();

		if (logoIsDisplayed == true) {
			System.out.println("Login is displayed");
		} else {
			System.out.println("Login is not displayed");
		}

		WebElement login = driver.findElement(By.xpath(properties.getProperty("login")));
		login.click();

		boolean filterIsDisplayed = driver.findElement(By.xpath(properties.getProperty("filter"))).isDisplayed();
		if (filterIsDisplayed == true) {
			System.out.println("filter is displayed");
		} else {
			System.out.println("filter is not displayed");
		}

		WebElement filter = driver.findElement(By.xpath(properties.getProperty("filter")));
		filter.click();

		Select select = new Select(filter);

		select.selectByIndex(0);

		String defaultvalue = select.getFirstSelectedOption().getText();
		System.out.println(defaultvalue);

		List<WebElement> alloptions = select.getOptions();

		for (WebElement element : alloptions) {
			System.out.println(element.getText());

		}
		select.selectByIndex(1);

		ArrayList<String> nameArrayList = new ArrayList<String>();
		List<WebElement> nameList = driver.findElements(By.xpath(properties.getProperty("productname")));
		for (WebElement elements : nameList) {
			System.out.println("---Products Names---");

			String nameList1 = elements.getText();
			// System.out.println("---Products Names---");
			System.out.println(nameList1);
		}
		ArrayList<String> priceArrayList = new ArrayList<String>();
		List<WebElement> priceList = driver.findElements(By.xpath(properties.getProperty("productprice")));
		for (WebElement elements : priceList) {
			String priceList1 = elements.getText();

			System.out.println("---Price List---");
			System.out.println(priceList1);
		}
		WebElement menuButton = driver.findElement(By.xpath(properties.getProperty("menuButton")));
		menuButton.click();

		boolean logOutIsDisplayed = driver.findElement(By.xpath(properties.getProperty("logOut"))).isDisplayed();

		if (logOutIsDisplayed == true) {
			System.out.println("logOut is displayed");
		} else {
			System.out.println("logOut is not displayed");
		}
		WebElement logOut = driver.findElement(By.xpath(properties.getProperty("logOut")));
		logOut.click();

	}

}
