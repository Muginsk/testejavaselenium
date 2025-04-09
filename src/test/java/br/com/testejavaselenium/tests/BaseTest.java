package br.com.testejavaselenium.tests;

import br.com.testejavaselenium.config.DriverManager;
import br.com.testejavaselenium.config.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        extent = ReportManager.getInstance();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        driver = DriverManager.getDriver();
        test = extent.createTest(testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @AfterAll
    public static void tearDownReport() {
        ReportManager.flush();
    }
}
