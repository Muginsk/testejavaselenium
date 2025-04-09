package br.com.testejavaselenium.tests;

import br.com.testejavaselenium.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Login com dados válidos")
    void loginComDadosValidos() {
        driver.get("https://www.saucedemo.com/v1/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fazerLogin("standard_user", "secret_sauce");

        String urlEsperada = "https://www.saucedemo.com/v1/inventory.html";
        assertEquals(urlEsperada, loginPage.getUrlAtual(), "Login falhou!");
    }
}
