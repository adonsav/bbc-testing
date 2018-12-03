package base;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestBase {

    private List<WebDriver> driverInstances = new ArrayList<>();
    private ATUTestRecorder recorder;

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

    @BeforeSuite
    public void startRecording() throws ATUTestRecorderException {

        DateFormat dateFormat = new SimpleDateFormat("y-m-d H-M-S");
        Date date = new Date();

        recorder = new ATUTestRecorder(
                "src/test/testVideos", "testRun-" + dateFormat.format(date), false);
        recorder.start();

    }

    /**
     * This method is called before every test case and initializes the driver based on the 'regression.xml' file
     * properties.
     *
     * @param os      The operating system of the node (e.g MAC).
     * @param browser The browser we want to run the test on (e.g. chrome).
     * @param node    The remote node url (e.g. http://localhost:5555/wd/hub).
     */
    @BeforeTest
    @Parameters({"os", "browser", "node"})
    public void testInitialize(String os, String browser, String node) throws MalformedURLException {

        driverInstances.add(DriverFactory.buildDriver(os, browser, node));

    }

    /**
     * This method is called after all test cases have been executed and closes all browser windows and safely ends the
     * session.
     */
    @AfterTest
    public void testTeardown() {

        System.out.println("Got inside 'testTeardown'");

        if (driverInstances.size() != 0) {

            for (WebDriver driverInstance :
                    driverInstances) {

                driverInstance.quit();

            }

        }

    }

    @AfterSuite
    public void stopRecording() throws ATUTestRecorderException {

        recorder.stop();

    }

}
