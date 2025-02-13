package vn.edu.t3h.bookshopmanager.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigInit {
    public static String DB_URL;
    public static String DB_USERNAME;
    public static String DB_PASSWORD;

    public static String BASE_URL;
    static {
        try (InputStream input = ConfigInit.class.getClassLoader().getResourceAsStream("application.properties")){
            if(input == null){
                throw new IOException("application.properties not found");
            }
            Properties properties = new Properties();
            properties.load(input);

            DB_URL = properties.getProperty("db.url");
            DB_USERNAME = properties.getProperty("db.username");
            DB_PASSWORD = properties.getProperty("db.password");
            BASE_URL = properties.getProperty("base.url");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load config from application.properties");
        }
    }
}
