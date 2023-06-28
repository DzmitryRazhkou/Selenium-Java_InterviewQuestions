package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.example.constants.Paths;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    public static String takeScreenshot(WebDriver driver, String methodName) {
        String directory = Paths.SCREENSHOTS;
        new File(directory).mkdirs();

        String filePath = directory + getScreenshotFileName(methodName);
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return filePath;
    }

    private static String getScreenshotFileName(String methodName) {
        return methodName + "_" + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date()) + ".png";
    }
}
