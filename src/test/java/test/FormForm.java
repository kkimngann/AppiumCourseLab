package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormForm {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.createDriver(Platforms.android);
        try {
            MobileElement menuFormElement = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            menuFormElement.click();

            MobileElement inputFieldElement = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            inputFieldElement.sendKeys("Hello, This is Form input");

            MobileElement inputtedFieldElement = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));
            System.out.println(inputtedFieldElement.getText());

            MobileElement switchElement = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            switchElement.click();

            MobileElement dropdownElement = appiumDriver.findElement(MobileBy.AccessibilityId("Dropdown"));
            dropdownElement.click();
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
            MobileElement selectListDialogElement = (MobileElement) wait.until(
                    ExpectedConditions.visibilityOfElementLocated(MobileBy.id("com.wdiodemoapp:id/select_dialog_listview")));

            final int TEXT_THIS_APP_IS_AWESOME_INDEX = 3;
            MobileElement optionElement = selectListDialogElement.findElements(MobileBy.id("android:id/text1")).get(TEXT_THIS_APP_IS_AWESOME_INDEX);
            optionElement.click();

            MobileElement btnActiveElement = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            btnActiveElement.click();
            WebElement activeDialogTitleElem = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));
            System.out.println("Alert title: " + activeDialogTitleElem.getText());

        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
