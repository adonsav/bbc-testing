package base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static String baseUrl = "http://www.bbc.com";
    public static WebDriver driver;

    /**
     * This method is called one time for every 'test' tag we have defined in 'regression.xml' file, in order to build
     * the appropriate driver instance based on the file properties.
     *
     * @param os      The operating system of the node (e.g MAC).
     * @param browser The browser we want to run the test on (e.g. chrome).
     * @param node    The remote node url (e.g. http://localhost:5555/wd/hub).
     */
    static WebDriver buildDriver(String os, String browser, String node) throws MalformedURLException {

        System.out.println("Got inside 'buildDriver'\n");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions chromeOpt = new ChromeOptions();
            chromeOpt.setCapability("platform", Platform.fromString(os.toUpperCase()));
            chromeOpt.addArguments("disable-infobars");

            driver = new RemoteWebDriver(new URL(node), chromeOpt);

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions firefoxOpt = new FirefoxOptions();
            firefoxOpt.setCapability("platform", Platform.fromString(os.toUpperCase()));

            driver = new RemoteWebDriver(new URL(node), firefoxOpt);

        } else {

            System.out.println("Driver for '" + browser + "' is not supported.");
            throw new NotImplementedException();

        }

        //Maybe more browsers will be added in this section in the future.

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

        return driver;

    }

}
