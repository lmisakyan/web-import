package com.dimimport;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SaveCookies {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/demo/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> accounts = Arrays.asList(
                    "katarina464@yandex.ru",
                    "maxxezh@yandex.ru",
                    "rossturvl@yandex.ru",
                    "analytics@agencysgm.com",
                    "azimut.vluki@yandex.ru");
            Collection<Collection<Cookie>> cookies = new ArrayList<>(accounts.size());
            for (String a : accounts) {
                driver.get("https://www.facebook.com/548469171978134");
                WebElement email = driver.findElement(By.name("email"));
                email.sendKeys(a);
                WebElement pass = driver.findElement(By.name("pass"));
                pass.sendKeys("Wobot2015");
                pass.submit();
                cookies.add(driver.manage().getCookies());
                driver.manage().deleteAllCookies();
            }
            try {
                objectMapper.writeValue(new PrintWriter("src/demo/resources/cookies.json", "UTF-8"), cookies);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            driver.quit();
        }
    }
}


