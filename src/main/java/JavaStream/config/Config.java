package JavaStream.config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class Config {

    public static Connection getH2Connection() throws Exception {
        var inputStream = new FileInputStream("src/main/resources/application.properties");
        var properties = new Properties();
        properties.load(inputStream);

        var url = properties.getProperty("database.h2.url");
        var username = properties.getProperty("database.h2.username");
        var password = properties.getProperty("database.h2.password");

        return DriverManager.getConnection(properties.getProperty("database.h2.url"), properties.getProperty("database.h2.username"), properties.getProperty("database.h2.password"));
    }
}
