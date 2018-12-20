package com.insta.controller;


import com.insta.WebDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

@RestController
public class RestApiController {

    private WebDriver driver = null;



    @RequestMapping(value = "/login", method =RequestMethod.POST)
    public void login(@RequestBody Map<String, String> data) {
        String userName = data.get("username");
        String password = data.get("password");
        driver = WebDriverService.createChromeDriver();

    }

}
