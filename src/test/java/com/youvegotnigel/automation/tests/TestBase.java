package com.youvegotnigel.automation.tests;

import com.youvegotnigel.automation.utils.ListenerClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase extends AbstractTestNGCucumberTests {

    public static AppiumDriver driver;
    public static EventFiringWebDriver eventFiringWebDriver;

    public void Android_setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/ToDo.apk");

        caps.setCapability("noReset", true);
        caps.setCapability("fullReset", false);

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
        LogEntries logEntries = driver.manage().logs().get("logcat");
        logEntries.getAll();

    }

    public void loadEventFiringWebDriver(){
        eventFiringWebDriver = new EventFiringWebDriver(driver);
        ListenerClass listener = new ListenerClass();
        eventFiringWebDriver.register(listener);
    }

    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
