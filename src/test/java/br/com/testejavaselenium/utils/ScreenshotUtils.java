package br.com.testejavaselenium.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static String tirarScreenshot(WebDriver driver, String nomeArquivo) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File origem = ts.getScreenshotAs(OutputType.FILE);
        String destino = "reports/screenshots/" + nomeArquivo + ".png"; // 👈 Aqui
        try {
            Files.createDirectories(Paths.get("reports/screenshots/"));
            Files.copy(origem.toPath(), Paths.get(destino));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destino;
    }
}
