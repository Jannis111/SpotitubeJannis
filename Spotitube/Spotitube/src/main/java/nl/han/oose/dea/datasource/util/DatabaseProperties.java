package nl.han.oose.dea.datasource.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;


    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }

    }

    public String getConnectionString() {
        return properties.getProperty("connectionstring");
    }

    public String getDriver(){
        return properties.getProperty("driver");
    }
    public Properties test(){
        return properties;
    }
}
