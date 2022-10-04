package JavaStream.config;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public final class Config {

    public static Connection getH2Connection() throws Exception {
        var inputStream = new FileInputStream("src/main/resources/application.properties");
        var properties = new Properties();
        properties.load(inputStream);

        return DriverManager.getConnection(properties.getProperty("database.h2.url"), properties.getProperty("database.h2.username"), properties.getProperty("database.h2.password"));
    }

    public static Gson getConfiguredJsonSerializer() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, LocalDateTypeAdapter.getInstance())
                .create();
    }
}
