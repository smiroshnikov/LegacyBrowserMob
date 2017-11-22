package proxy;

import java.io.File;
import java.io.IOException;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;





public class MobProxy {
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
    public void teknosa_test1() throws InterruptedException {

        server.newHar("teknosa.har");

        driver.get("http://www.teknosa.com");
        driver.manage().window().maximize();
        Thread.sleep(15000);
    }

    @AfterClass
    public void shutdown() {
        try {

            // Get the HAR data
            Har har = server.getHar();
            File harFile = new File("C:\\Webdrivers\\teknosa_test.har");
            har.writeTo(harFile);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        driver.quit();
        server.stop();
    }
}