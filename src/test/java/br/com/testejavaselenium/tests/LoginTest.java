package br.com.testejavaselenium.tests;

import br.com.testejavaselenium.pages.LoginPage;
import br.com.testejavaselenium.utils.ScreenshotUtils;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Login com dados válidos")
    void loginComDadosValidos() {
        test = extent.createTest("Login com dados válidos");

        driver.get("https://www.saucedemo.com/v1/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fazerLogin("standard_user1", "secret_sauce");

        String urlEsperada = "https://www.saucedemo.com/v1/inventory.html";
        String urlAtual = loginPage.getUrlAtual();

        if (urlEsperada.equals(urlAtual)) {
            test.log(Status.PASS, "Login bem-sucedido!");
        } else {
            String caminhoScreenshot = ScreenshotUtils.tirarScreenshot(driver, "login_falhou");
            test.addScreenCaptureFromPath("screenshots/login_falhou.png");
            test.log(Status.FAIL, "Login falhou! URL atual: " + urlAtual);
        }

        assertEquals(urlEsperada, urlAtual, "Login falhou!");
    }
}
