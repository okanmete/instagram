package com.insta;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverService {
    public static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");



        WebDriver driver = new ChromeDriver();

        return driver;
    }

    public static void main(String[] args) {
        WebDriver driver = createChromeDriver();

    }

}
