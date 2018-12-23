package com.insta.controller;


import com.insta.Props;
import com.insta.services.InstaService;
import com.insta.services.WebDriverService;
import org.apache.commons.lang3.StringUtils;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

@RestController
public class RestApiController {

    private WebDriver driver = null;

    private ScheduledExecutorService scheduler = newSingleThreadScheduledExecutor();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @RequestMapping(value = "/like-hashtag", method = RequestMethod.POST)
    public void likeHashtag(@RequestBody Map<String, Object> data) {
        String hashtag = (String) data.get("hashtag");
        int timeInterval = (int) data.get("time");

        Runnable task = new Runnable() {
            @Override
            public void run() {
                driver.get("http://www.instagram.com/explore/tags/" + hashtag);
                String mostRecentContentId = StringUtils.
                        substringBetween(driver.getPageSource(), "\"shortcode\":\"", "\"");
                String lastPhotoLink = "https://www.instagram.com/p/" + mostRecentContentId;
                driver.get(lastPhotoLink);
                likeTheContent(driver);
            }
        };

        scheduler.scheduleAtFixedRate(task, 0, timeInterval, TimeUnit.SECONDS);
    }

    private boolean isNotLiked(String pageSource) {
        return (pageSource.contains("<span class=\"glyphsSpriteHeart__outline__24__grey_9 u-__7\" aria-label=\"Like"));
    }

    private void likeTheContent(WebDriver driver) {
        if (isNotLiked(driver.getPageSource())) {
            driver.findElement(By.xpath(Props.getInstance().getLikeButtonXpath())).click();
        }
    }

}
