package Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.cucumber.testng.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apiguardian.api.API;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import stepDefinitions.BaseSteps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Objects;

@API(
        status = API.Status.STABLE
)
public abstract class RunnerParallel extends BaseSteps {

    private TestNGCucumberRunner testNGCucumberRunner;

    public RunnerParallel() {

    }


    @BeforeClass(
            alwaysRun = true
    )
    public void setUpClass(ITestContext context) {
        XmlTest currentXmlTest = context.getCurrentXmlTest();
        Objects.requireNonNull(currentXmlTest);
        CucumberPropertiesProvider properties = currentXmlTest::getParameter;
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), properties);
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper, JsonObject browserConfig) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    public Object[][] dp() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() throws FileNotFoundException {
        System.out.println(System.getProperty("user.dir") + "//src//BrowserConfig//local_ch.json");
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\BrowserConfig\\"+System.getProperty("browserconfig")+".json"));
        int numberOfConfig = jsonElement.getAsJsonArray().size();

        Object[][] obj = dp();
        int x = obj.length * numberOfConfig;
        int y = obj[0].length + 1;
        Object[][] a = new Object[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y - 1; j++) {
                a[i][j] = obj[i % obj.length][j];
            }

            a[i][2] = jsonElement.getAsJsonArray().get(i / obj.length).getAsJsonObject();
        }

        return a;
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }

}
