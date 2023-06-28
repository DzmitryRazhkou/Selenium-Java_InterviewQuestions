package org.example.utils;

import org.example.constants.Paths;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties initProperties() {
        Properties prop = null;
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(Paths.CONFIG);
            try {
                prop.load(ip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return prop;
    }
}