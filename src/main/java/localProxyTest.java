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

import java.io.File;
import java.io.IOException;

public class localProxyTest {
    public static WebDriver driver;
    public static BrowserMobProxyServer server;

    public static void main(String[] args) {


        server = new BrowserMobProxyServer();
        server.start();
        int port = server.getPort();
        Proxy proxy = ClientUtil.createSeleniumProxy(server);

        DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver", "/Users/smiroshn/work/chromedriver/chromedriver");

        seleniumCapabilities.setCapability(CapabilityType.PROXY, proxy);

        driver = new ChromeDriver();
        System.out.println("Port started:" + port);
        server.newHar("http://localhost:8000/proxy/login#/web-security-analytics");
        driver.get("http://localhost:8000/proxy/login#/web-security-analytics");

        try {

            // Get the HAR data
            Har har = server.getHar();


            File harFile = new File("/Users/smiroshn/work/WSA.json");

            har.getLog().getEntries().removeIf(x-> !x.getRequest().getMethod().equals("GET"));
            har.getLog().getEntries();
            har.writeTo(harFile);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        driver.quit();
        server.stop();
    }

}
