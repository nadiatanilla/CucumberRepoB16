package steps;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {
    @Before
    public void start() {
        openBrowserAndLaunchApplication();
    }

    @After
    public void end(Scenario scenario) {
        // here capture ss
        // weare going to capture ss
        byte[] pic;
     //   pic = takeScreenshot(scenario.getName());
        if (scenario.isFailed()) {
            pic=takeScreenshot("failed/"+scenario.getName());
        }else{
            pic=takeScreenshot("passed/"+scenario.getName());
        }
            // attatch this ss in the report
            scenario.attach(pic, "image/png", scenario.getName());

            closeBrowser();
        }

    }
