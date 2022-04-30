package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Context.Contexts;
import test.Context.WaitContexts;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lab17_Contexts_Screenshot {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.createDriver(Platforms.android);
        try {
            MobileElement menuLoginElement = appiumDriver.findElement(MobileBy.AccessibilityId("Webview"));
            menuLoginElement.click();

            //Wait context
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(new WaitContexts(appiumDriver));

            //Switch web
            appiumDriver.context(Contexts.WEB_VIEW);

            //Find element on web view
            WebElement btnNavigateElement = appiumDriver.findElementByXPath("//button[@class = \"navbar__toggle clean-btn\"]");
            btnNavigateElement.click();
            List<MobileElement> menuItemsElement = appiumDriver.findElementsByXPath("//li[@class = \"menu__list-item\"]/a");
            if(menuItemsElement.size() == 0){
                throw new RuntimeException("[ERROR] Not found any menu item");
            }
            Map<String, String> menuItemsText = new HashMap<>();
            for (MobileElement item : menuItemsElement) {
                String itemText = item.getText();
                if(itemText.isEmpty()){
                    String itemLink = item.getAttribute("href");
                    menuItemsText.put("Github", itemLink);
                }else{
                    String itemLink = item.getAttribute("href");
                    menuItemsText.put(itemText, itemLink);
                }
            }
            menuItemsText.keySet().forEach(key -> {
                System.out.println(key + ": " + menuItemsText.get(key));
            });

            //Take screenshot
            File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("Webview.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));


            //Switch to app
            appiumDriver.context(Contexts.NATIVE_APP);
            MobileElement navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("button-LOGIN")));
             //Take screenshot
            base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("Appview.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
