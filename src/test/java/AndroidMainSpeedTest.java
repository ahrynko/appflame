import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.Test;

public class AndroidMainSpeedTest {

    private AppiumDriver<AndroidElement> appiumDriver;
    private Logger logger = Logger.getLogger(String.valueOf(AndroidMainSpeedTest.class)); //register  Logger

    @BeforeTest
    public void initDriverAndConnectDevice() throws IOException {

        final Dotenv environment = Dotenv.configure().filename("./src/test/java/environment/capabilities.env").load();

        final DesiredCapabilities desiredCap = new DesiredCapabilities();   // settings

        desiredCap.setCapability("platformName",environment.get("platform"));
        desiredCap.setCapability("platformVersion",environment.get("version"));
        desiredCap.setCapability("deviceName",environment.get("device"));
        desiredCap.setCapability("udid",environment.get("udid"));

        desiredCap.setCapability("app",
                new File(".").getCanonicalPath().replaceAll("\\\\" , "/") +
                        "/src/test/java/environment/Speedtest_4.4.13.apk");

        desiredCap.setCapability("appPackage","org.zwanoo.android.speedtest");  //package
        desiredCap.setCapability("appActivity","com.ookla.mobile4.screens.main.MainActivity"); //page
        desiredCap.setCapability("autoGrantPermissions","true");

        appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCap); //implement = AndroidDriver

    }

    @BeforeClass
    public void startSpeedTest() {
        WebDriverWait driverWait = new WebDriverWait(appiumDriver, 10);

        try {
            WebElement nextButton = driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.Button[@text='Next']")));

            driverWait.until(ExpectedConditions.visibilityOf(nextButton));
            nextButton.click();

            WebElement continueButton = appiumDriver.findElementByXPath("//android.widget.Button[@text='Continue']");
            continueButton.click();

        }catch (TimeoutException e){
            e.fillInStackTrace();
        }

        WebElement goButton  = appiumDriver.findElement(By.xpath("//android.view.View[@content-desc='Start a Speedtest']"));
        goButton.click();

    }

    @Test
    public void networkConnectionToTime(){

        WebDriverWait driverWait = new WebDriverWait(appiumDriver,50);
        WebElement goButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc='Start a Speedtest']")));

        Assert.assertTrue(goButton.isDisplayed());

    }

    @Test
    public void checkDownloadValue(){

        WebDriverWait driverWait = new WebDriverWait(appiumDriver,50);
        WebElement goButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc='Start a Speedtest']")));

        WebElement downloadText  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.FrameLayout[@content-desc='DOWNLOAD']")));

        Assert.assertTrue(downloadText.isDisplayed());

        WebElement downloadValue  = downloadText.findElement(
                By.xpath("//*[@resource-id='org.zwanoo.android.speedtest:id/txt_test_result_value']"));

        Assert.assertTrue(downloadValue.isDisplayed());
        logger.info(String.format(" Download Mbps = %s", downloadValue.getText()));

    }

    @Test
    public void checkUploadValue(){

        WebDriverWait driverWait = new WebDriverWait(appiumDriver,50);
        WebElement goButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc='Start a Speedtest']")));

        WebElement uploadText  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.FrameLayout[@content-desc='UPLOAD']")));

        Assert.assertTrue(uploadText.isDisplayed());

        WebElement uploadValue  = uploadText.findElement(
                By.xpath("//*[@resource-id='org.zwanoo.android.speedtest:id/txt_test_result_value']"));

        Assert.assertTrue(uploadValue.isDisplayed());
        logger.info(String.format(" Upload Mbps = %s", uploadValue.getText()));

    }

    @Test
    public void checkPingValue(){

        WebDriverWait driverWait = new WebDriverWait(appiumDriver,50);
        WebElement goButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc='Start a Speedtest']")));

        WebElement pingText  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='Ping']")));

        Assert.assertTrue(pingText.isDisplayed());

        WebElement pingValue  = pingText.findElement(
                By.xpath("//*[@resource-id='org.zwanoo.android.speedtest:id/txt_test_result_value']"));

        Assert.assertTrue(pingValue.isDisplayed());
        logger.info(String.format(" Ping = %s", pingValue.getText()));

    }

    @Test
    public void checkJitterValue(){

        WebDriverWait driverWait = new WebDriverWait(appiumDriver,50);
        WebElement goButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc='Start a Speedtest']")));

        WebElement jitterText  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='Jitter']")));

        Assert.assertTrue(jitterText.isDisplayed());

        WebElement jitterValue  = jitterText.findElement(
                By.xpath("//*[@resource-id='org.zwanoo.android.speedtest:id/txt_test_result_value']"));

        Assert.assertTrue(jitterValue.isDisplayed());
        logger.info(String.format(" Jitter = %s", jitterValue.getText()));

    }

    @Test
    public void checkLossValue(){

        WebDriverWait driverWait = new WebDriverWait(appiumDriver,50);
        WebElement goButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc='Start a Speedtest']")));

        WebElement lossText  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@resource-id='org.zwanoo.android.speedtest:id/test_result_item_packet_loss']")));

        Assert.assertTrue(lossText.isDisplayed());

        WebElement lossValue = lossText.findElement(
                By.xpath("//*[@resource-id='org.zwanoo.android.speedtest:id/txt_test_result_value']"));

        Assert.assertTrue(lossValue.isDisplayed());
        logger.info(String.format(" Loss = %s", lossValue.getText()));

    }

    @AfterTest(alwaysRun = true)
    public void tearDownDriver(){

        appiumDriver.quit();

    }
}



