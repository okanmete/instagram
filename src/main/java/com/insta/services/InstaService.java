package com.insta.services;

import org.openqa.selenium.WebDriver;

public class InstaService {
    private String find_between(String value, String a, String b) {
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

    public String findMostRecentContent(String pageSource) {
        return find_between(pageSource,"goztepe","karsiyaka");
    }

    public void likeTheContent()

}
