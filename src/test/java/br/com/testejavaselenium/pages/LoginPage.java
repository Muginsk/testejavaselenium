package br.com.testejavaselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By campoUsuario = By.id("user-name");
    private By campoSenha = By.id("password");
    private By botaoLogin = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherUsuario(String usuario) {
        driver.findElement(campoUsuario).sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        driver.findElement(campoSenha).sendKeys(senha);
    }

    public void clicarLogin() {
        driver.findElement(botaoLogin).click();
    }

    public void fazerLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarLogin();
    }

    public String getUrlAtual() {
        return driver.getCurrentUrl();
    }
}
