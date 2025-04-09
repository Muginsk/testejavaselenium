package br.com.testejavaselenium.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeAll
    static void configurarRelatorio() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/relatorio.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeEach
    void iniciarTeste() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void finalizar() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // Gera o relatório ao final de cada teste
    }
}
