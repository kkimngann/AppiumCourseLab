package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.util.List;

public class NarrowDown {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.createDriver(Platforms.android);
        try {
            Dimension screenSize = appiumDriver.manage().window().getSize();
            int screenHeight = screenSize.getHeight();
            int screenWidth = screenSize.getWidth();

            int xStartPoint = screenWidth/2;
            int yStartPoint = 0;

            int xEndPoint = xStartPoint;
            int yEndPoint = 90 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .longPress(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();

            List<MobileElement> listNotiHeaderElements = appiumDriver.findElements(MobileBy.id("android:id/app_name_text"));
            if(listNotiHeaderElements.size() == 0){
                throw new RuntimeException("Not found any notification to test");
            }
            for (MobileElement notiHeaderElement : listNotiHeaderElements) {
                System.out.println("Notification header: " + notiHeaderElement.getText());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
