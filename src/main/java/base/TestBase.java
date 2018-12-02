package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestBase {

    protected WebDriver driver;

    /**
     * This method is called before any test case starts running and initializes the grid's hub and nodes.
     */
    @BeforeSuite
    public void gridInitialization() {

        System.out.println("Before Suite annotation is running.\n");

        try {

            Runtime.getRuntime().exec("./executors/launchHub.sh");
            Runtime.getRuntime().exec("./executors/launchNodes.sh");

            System.out
                    .println("======================================================================================");
            System.out
                    .println("                              HUB and NODES Started ");
            System.out
                    .println("====================================================================================== \n");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /**
     * This method is called before every test case and initializes the driver based on the 'regression.xml' file
     * properties.
     *
     * @param os      The operating system of the node (e.g MAC).
     * @param browser The browser we want to run the test on (e.g. chrome).
     * @param node    The remote node url (e.g. http://localhost:5555/wd/hub).
     */
    @BeforeTest(alwaysRun = true)
    @Parameters({"os", "browser", "node"})
    public void testInitialize(String os, String browser, String node) throws MalformedURLException {

        driver = DriverFactory.buildDriver(os, browser, node);

    }

    /**
     * This method is called after all test cases have been executed and closes all browser windows and safely ends the
     * session.
     */
    @AfterTest(alwaysRun = true)
    public void testTeardown() {

        System.out.println("Got inside 'testTeardown'");
        if (driver != null) {

            driver.quit();
            
        }

    }

}
