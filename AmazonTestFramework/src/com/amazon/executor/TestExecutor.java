package com.amazon.executor;

import org.testng.annotations.Test;

import com.amazon.pageobjects.CatgorySelection;
import com.amazon.pageobjects.LandingPage;
import java.io.IOException;
import java.util.logging.*;
import org.apache.commons.logging.Log;
import org.testng.Reporter;

/**
 * Created by Pramila Fulari on 9/11/2019.
 */

public class TestExecutor {

	//TestScenario : Launching Amazon app and Checking presence of WebElement
	@Test
	public void testLandingPAndShopByCatgoryage() throws InterruptedException, SecurityException, IOException {
		//Logger Initializing
		Logger logger = Logger.getLogger(Log.class.getName());
		//Calling Landing Page by instantiating the LandingPage
		LandingPage loginPage = new LandingPage();
		Reporter.log("Hamburger button Clicked");
		//Selecting ShopByCategory and verifying presence of Kindle Element
		CatgorySelection cs = loginPage.clickOnHamburger();
		cs.clickOnShopByCategory();
		Reporter.log("Kindle E-Readers element is verification done");
		//Saving Logs to TestExecutor.log at default project location
		FileHandler fileHandler = new FileHandler("TestExecutor.log", true);        
	    logger.addHandler(fileHandler);

	}

}
