package com.dimimport;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;

public class SaveCookies {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/demo/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/548469171978134");
        Collection<String> cookies = new ArrayList<>();
        for (Cookie loadedCookie : driver.manage().getCookies()) {
            cookies.add(loadedCookie.toString());
        }
        try {
            Files.write(FileSystems.getDefault().getPath("src/demo/resources/cookies.txt"), cookies, StandardOpenOption.CREATE);
        } catch (IOException    e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}


