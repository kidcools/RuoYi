package com.ruoyi.system.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.LinkedBlockingQueue;

public  class DriverUtil {

    //private static final WebDriver driver;
    private static LinkedBlockingQueue webDrivers;
    static{
        webDrivers = new LinkedBlockingQueue<WebDriver>(5);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        //建立selenium 驱动
        for(int i=0;i<5;i++){
            System.setProperty("webdriver.chrome.driver","F:\\chromedriver.exe");
            System.out.println("milestar");
            WebDriver driver = new ChromeDriver(options);
            try {
                webDrivers.put(driver);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static WebDriver getDriver(){
        try {
            return (WebDriver) webDrivers.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用完成回收
     * @param driver
     */
    public static void recycle(WebDriver driver){
        try {
           webDrivers.put(driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
