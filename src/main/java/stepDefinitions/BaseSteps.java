package stepDefinitions;

import Utils.CommonMethod;
import Utils.RunnerParallel;
import com.google.gson.JsonObject;
import io.cucumber.java.After;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

public class BaseSteps {
    WebDriver driver;
    Properties props;
    static ThreadLocal<BaseSteps> instance = new ThreadLocal<>();
    WebDriverWait wait;
    static int i = 0;


    @BeforeMethod
    public void setUp(Object[] obj) throws IOException {
        System.out.println("This is before Method");
        setUpInstance(obj[2]);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
    }

    public void setUpInstance(Object browserConfig) throws IOException {
        instance.set(new BaseSteps());
        getInstance().setUP((JsonObject) browserConfig);
    }


    public BaseSteps getInstance() {
        return instance.get();
    }

    public void setUP(JsonObject browserConfig) throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//env.properties");
        props = new Properties();
        props.load(file);
        String browser = browserConfig.get("browser").getAsString();
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions optionsBeta = new ChromeOptions();
                optionsBeta.addArguments("--user-data-dir=" + System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User");
                driver = new ChromeDriver(optionsBeta);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }
        file.close();
        driver.manage().window().maximize();
    }


    public String getProperty(String key) {
        return getInstance().props.getProperty(key);
    }


    public WebDriver getDriver() {
        return getInstance().driver;
    }

    public void takeScreenshot(String message) {
        File screenshot = ((TakesScreenshot) getInstance().driver).getScreenshotAs(OutputType.FILE);
        String ScreenshotPath = "Screenshots/" + UUID.randomUUID() + ".png";
        File file = new File(System.getProperty("user.dir") + File.separator + "Reports/" + ScreenshotPath);
//        File file = new File("Demo.jpg");
        try {
            FileUtils.copyFile(screenshot, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String outputMessage = "<a data-featherlight=\"image\" href=\"" + ScreenshotPath + "\"><i class=\"material-icons\">panorama</i></a>";
        CommonMethod.attachScreenshot("<span style='background-color:rgb(0, 102, 255)'> " + message + "</span>" + outputMessage);

    }

    public WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Long.getLong(getInstance().getProperty("waitduration"))));
        }
        return wait;
    }

    public String getXpath(String StringToFormat, String value) {
        return String.format(StringToFormat, value);
    }

    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        boolean pageLoaded = false;
        while (!pageLoaded) {
            String domLoadStatus = js.executeScript("return document.readyState").toString();
            System.out.println(domLoadStatus);
            if (domLoadStatus.equalsIgnoreCase("complete")) {
                pageLoaded = true;
            }
        }
    }


}
