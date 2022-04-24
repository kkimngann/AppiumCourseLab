package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

public class FormSwipeHorizontal {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.createDriver(Platforms.android);
        try {
            MobileElement menuSwipeElement = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            menuSwipeElement.click();

            //Swipe vertical
            MobileElement cardElement = appiumDriver.findElement(MobileBy.AccessibilityId("Carousel"));
            Dimension cardSize = cardElement.getSize();
            int cardHeight = cardSize.getHeight();
            int cardWidth = cardSize.getWidth();

            int cardXStart = cardElement.getLocation().getX();
            int cardYStart = cardElement.getLocation().getY();

            int xStartPoint = cardXStart + cardWidth/2;
            int yStartPoint = cardYStart + cardHeight;

            int xEndPoint = xStartPoint;
            int yEndPoint = 0;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .longPress(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
