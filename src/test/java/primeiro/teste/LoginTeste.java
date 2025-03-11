package primeiro.teste;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class LoginTeste {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Configurar o WebDriver para abrir o navegador (Chrome, Edge ou Firefox)
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe"); // Atualize este caminho

        // Inicializando o WebDriver com Chrome (troque para EdgeDriver() ou FirefoxDriver() se quiser testar outros)
        driver = new ChromeDriver();

        // Definir um tempo máximo para carregamento de elementos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximizar a janela
        driver.manage().window().maximize();
    }

    @Test
    void testLoginValido() {
        // Abrir o site
        driver.get("https://www.saucedemo.com/v1/");

        // Encontrar os elementos do login
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Inserir credenciais válidas
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");

        // Clicar no botão de login
        loginButton.click();

        // Verificar se o login foi bem-sucedido conferindo a URL da página de produtos
        assertEquals("https://www.saucedemo.com/v1/inventory.html", driver.getCurrentUrl(), "Login falhou!");
    }

    @Test
    void testAdicionarItensAoCarrinho() {
        // Primeiro, fazer login
        driver.get("https://www.saucedemo.com/v1/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Verificar se está na página de inventário
        assertEquals("https://www.saucedemo.com/v1/inventory.html", driver.getCurrentUrl(), "Login falhou!");

        // Adicionar os primeiros 3 itens ao carrinho
        List<WebElement> botoesAdicionar = driver.findElements(By.cssSelector(".btn_inventory"));
        for (int i = 0; i < 3; i++) {
            botoesAdicionar.get(i).click();
        }

        // Verificar se o número de itens no carrinho está correto
        WebElement carrinho = driver.findElement(By.className("shopping_cart_badge"));
        assertEquals("3", carrinho.getText(), "Número de itens no carrinho incorreto!");
    /*}

    @AfterEach
    void tearDown() {
        // Fechar o navegador após o teste
        if (driver != null) {
            driver.close();
        }*/
    }
}
