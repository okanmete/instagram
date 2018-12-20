package com.insta;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private static final String PROPERTIES_FILE = "./env.properties";

    private static final Logger logger = LogManager.getLogger(Props.class);

    private static final Props instance = new Props();

    private String chromeDriver;

    public String getChromeDriver() {
        return chromeDriver;
    }

    public static Props getInstance() {
        return instance;
    }

    private Props(){
        init();
    }
    private void init() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(PROPERTIES_FILE);
            prop.load(input);
        } catch (Exception ex) {
            logger.warn("Properties file could not been found: {}");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        String chromeDriver = prop.getProperty("chrome_driver");

        this.chromeDriver = defined(chromeDriver) ? chromeDriver : Constants.CHROME_DRIVER;

    }
    private boolean defined(String s) {
        return s != null && !s.isEmpty();
    }
}
