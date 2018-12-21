package com.insta.controller;


import com.insta.services.InstaService;
import com.insta.services.WebDriverService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.openqa.selenium.WebDriver;

import java.util.Map;

@RestController
public class RestApiController {

    private WebDriver driver = null;

    private InstaService instaService;

    @RequestMapping(value = "/login", method =RequestMethod.POST)
    public void login(@RequestBody Map<String, String> data) {
        String userName = data.get("username");
        String password = data.get("password");

        driver = WebDriverService.createChromeDriver();
        driver.get("https://www.instagram.com/accounts/login/");

        WebElement userNameField = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        userNameField.sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[3]/button")).click();
    }

    @RequestMapping(value = "/like-hashtag", method =RequestMethod.POST)
    public void likeHashtag(@RequestBody Map<String, String> data) {
        String hashtag = data.get("hashtag");
        String timeInterval = data.get("time");
        driver.get("http://www.instagram.com/explore/tags/" + hashtag);
        //System.out.println(driver.getPageSource());
        //instaService.findMostRecentContent(driver.getPageSource());
        String lastPhotoLink = "https://www.instagram.com/p/BrpiTrXnE8u/";
        driver.get(lastPhotoLink);
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/article/div[2]/section[1]/span[1]/button/span")).click();

    }






}
