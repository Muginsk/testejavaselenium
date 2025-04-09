package br.com.testejavaselenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTeste {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    static void setupReport() {
        // metodo usando ExtentSparkReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        test = extent.createTest(testInfo.getDisplayName()); // Cria um novo teste no relatório
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Login com Credenciais Válidas")
    void testLoginValido() {
        try {
            driver.get("https://www.saucedemo.com/v1/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            assertEquals("https://www.saucedemo.com/v1/inventory.html", driver.getCurrentUrl(), "Login falhou!");
            test.log(Status.PASS, "Login realizado com sucesso");
        } catch (Exception e) {
            captureScreenshot("testLoginValido");
            test.log(Status.FAIL, "Teste falhou: " + e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Adicionar Itens ao Carrinho")
    void testAdicionarItensAoCarrinho() {
        try {
            driver.get("https://www.saucedemo.com/v1/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            assertEquals("https://www.saucedemo.com/v1/inventory.html", driver.getCurrentUrl(), "Login falhou!");

            List<WebElement> botoesAdicionar = driver.findElements(By.cssSelector(".btn_inventory"));
            for (int i = 0; i < 3; i++) {
                botoesAdicionar.get(i).click();
            }

            WebElement carrinho = driver.findElement(By.className("shopping_cart_badge"));
            assertEquals("3", carrinho.getText(), "Número de itens no carrinho incorreto!");

            test.log(Status.PASS, "Itens adicionados ao carrinho com sucesso");
        } catch (Exception e) {
            captureScreenshot("testAdicionarItensAoCarrinho");
            test.log(Status.FAIL, "Teste falhou: " + e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    static void tearDownReport() {
        extent.flush(); // Finaliza o relatório Extent Reports
    }

    private void captureScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("reports/screenshots"));
            Files.copy(srcFile.toPath(), Paths.get("reports/screenshots/" + testName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + testName + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
