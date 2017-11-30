package learnProxyMob;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class bmpNewerApproach {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Users/smiroshn/work/chromedriver/chromedriver");
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        // replace 'somedirectory' with a suitable temp dir on your filesystem
//        options.addArguments("--user-data-dir=somedirectory");

//        DesiredCapabilities capabilities = new DesiredCapabilities();

        options.setCapability(CapabilityType.PROXY, seleniumProxy);
//        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        // start the browser up
        WebDriver driver = new ChromeDriver(options);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "yahoo.com"
        proxy.newHar();

        // open yahoo.com
        driver.get("http://www.lits.com.ua");
        driver.get("http://www.google.com.ua");

        // get the HAR data
        Har har = proxy.getHar();
        System.out.println("Entries count :" + har.getLog().getEntries().size());
        System.out.println("content " + har.getLog().getEntries().toString());
        File harFile = new File("/Users/smiroshn/work/test.har");
        try {
            //harFile.mkdirs();
            harFile.createNewFile();
            har.writeTo(harFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

