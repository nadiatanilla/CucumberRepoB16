package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties pro;
    public static Properties readProperties(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            pro = new Properties();
            pro.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();

        }
        return pro;
    }
    public static String getPropertyValue(String key){
        return pro.getProperty(key);
    }
}
