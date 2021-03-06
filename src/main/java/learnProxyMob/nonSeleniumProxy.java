package learnProxyMob;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class nonSeleniumProxy {


    public static void main(String[] args) {


        System.setProperty("webdriver.chrome.driver", "/Users/smiroshn/work/chromedriver/chromedriver");
        // Creating browsermob proxy in order to use browsermob
        BrowserMobProxyServer browserMobProxyServer = new BrowserMobProxyServer();

        Proxy seleniumProxy = new Proxy();




        browserMobProxyServer.start();

        // creating selenium proxy in order to bind it to browsermob ?


        // binding chromedriver to work with selenium proxy
        DesiredCapabilities browserCapabilities = DesiredCapabilities.chrome();
        browserCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        WebDriver driver = new ChromeDriver(browserCapabilities);

        // what is point of creating  tag ?
        browserMobProxyServer.newHar("wpa.har");
        driver.get("google.com");

        Har har = browserMobProxyServer.getHar();

        driver.quit();
        driver.close();


    }
}
