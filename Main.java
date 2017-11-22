package proxy;


import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Main {
    public static WebDriver driver;
    public static BrowserMobProxyServer server;

    @BeforeClass
    public void setup() throws Exception {

        server = new BrowserMobProxyServer();
        server.start();
        int port = server.getPort();
        Proxy proxy = ClientUtil.createSeleniumProxy(server);

        DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver.exe");

        seleniumCapabilities.setCapability(CapabilityType.PROXY, proxy);

        driver = new ChromeDriver(seleniumCapabilities);
        System.out.println("Port started:" + port);
    }

    @Test
    public void google_test1() throws InterruptedException{

        server.newHar("youtube.com");


        driver.get("http://www.google.com/xhtml");
        driver.manage().window().maximize();
        Thread.sleep(15000);
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("PORN FREE!");
        searchBox.submit();
        Thread.sleep(5000);
    }


    @AfterClass
    public void shutdown() {
        try {

            // Get the HAR data
            Har har = server.getHar();


            File harFile = new File("C:\\Webdrivers\\google_test.json");
            har.getLog().getEntries().removeIf(x-> !x.getRequest().getMethod().equals("GET"));
            har.writeTo(harFile);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        driver.quit();
        server.stop();
    }
}
