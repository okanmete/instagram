package com.insta.services;


import com.insta.Props;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverService {
    public static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", Props.getInstance().getChromeDriver());
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static void main(String[] args) {
        WebDriver driver = createChromeDriver();

    }

}
