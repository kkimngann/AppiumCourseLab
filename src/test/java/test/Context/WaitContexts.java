package test.Context;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;

public class WaitContexts implements ExpectedCondition<Boolean> {
    private final AppiumDriver<MobileElement> appiumDriver;

    public WaitContexts(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return appiumDriver.getContextHandles().size() > 1;
    }
}
