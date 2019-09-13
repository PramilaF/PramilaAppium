package com.amazon.setup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.commons.logging.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Pramila Fulari on 9/11/2019.
 */

public class BaseSetup {

	private DesiredCapabilities dsrdCaps = new DesiredCapabilities();
	protected static AndroidDriver androidDriver = null;

	private String appiumServerIpPort;
	public Logger log = Logger.getLogger(Log.class.getName());	 

	@BeforeTest
	@Parameters({ "port", "deviceName" })
	public void setup(String port, String deviceName) throws SecurityException, IOException {

		//androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		initDriver(port, deviceName);
		log.info("setup Called");
		System.out.println("Inside Setup Before Class page constructor");
	}

	
	
	public AndroidDriver getDriver1() {
		return androidDriver;
	}

	private void initDriver(String port, String deviceName) throws SecurityException, IOException {

		appiumServerIpPort = port;
		log.info("Capabilities setting is started");
		dsrdCaps.setCapability("automationName", "UiAutomator2");
		dsrdCaps.setCapability("platformVersion", "9");
		dsrdCaps.setCapability("platformName", "Android");
		dsrdCaps.setCapability("deviceName", "emulator");
		dsrdCaps.setCapability("udid", deviceName);
		dsrdCaps.setCapability("noReset", "true");
		dsrdCaps.setCapability("platformName", "Android");
		dsrdCaps.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		dsrdCaps.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		dsrdCaps.setCapability("app", "C:\\Pramila\\AmazonEmaulator\\amazon.apk");
		log.info("Capabilities setting is completed");
		log.info("Amazon Application Launched Successfully!!");
		Reporter.log("Amazon Application Launched Successfully!!");
		
		String serverUrl = "http://" + appiumServerIpPort + "/wd/hub"; 
		
		FileHandler fileHandler = new FileHandler("setUpLog.log", true);        
	    log.addHandler(fileHandler);
		try {
			System.out.println("Argument to driver object : " + serverUrl);
			androidDriver = new AndroidDriver(new URL(serverUrl), dsrdCaps);

		} catch (NullPointerException | MalformedURLException ex) {
			throw new RuntimeException("appium driver could not be initialised for device ");
		}

	}

	@AfterTest
	public void tearDown() {
		androidDriver.quit();
		log.info("quit done successly");
	}

}
