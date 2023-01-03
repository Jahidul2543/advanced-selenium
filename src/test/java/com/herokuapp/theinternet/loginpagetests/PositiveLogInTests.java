package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;

import java.util.Map;

public class PositiveLogInTests extends TestUtilities {

	@Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void logInTest(Map<String, String> testData) {

		String no = testData.get("no");
		String username  = testData.get("username");
		String password = testData.get("password");
		String expectedErrorMessage = testData.get("expectedMessage");
		String description = testData.get("description");

		log.info("Starting negativeLogInTest #" + no + " for " + description);
		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();
		takeScreenshot("WelcomePage opened");

		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
		takeScreenshot("LoginPage opened");
		
		// Add new cookie
		Cookie ck = new Cookie("username", username, "the-internet.herokuapp.com", "/", null);
		loginPage.setCookie(ck);
		
		// execute log in
		SecureAreaPage secureAreaPage = loginPage.logIn(username, "SuperSecretPassword!");
		takeScreenshot("SecureAreaPage opened");
		
		// Get cookies
		String usernameFromCookie = secureAreaPage.getCookie("username");
		log.info("Username cookie: " + usernameFromCookie);
		String session = secureAreaPage.getCookie("rack.session");
		log.info("Session cookie: " + session);
		
		
		// Verifications
		// New page url is expected
		Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

		// log out button is visible
		Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "LogOut Button is not visible.");

		// Successful log in message
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
						+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
	}
}
