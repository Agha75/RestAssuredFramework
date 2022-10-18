package com.spotify.oauth2.utils;

import java.util.Properties;

//Singleton deign pattern used so the object of this call cannot be created outside this class
public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    //this will created new object of this class only once and will not check for path again and again
    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            //calling the above constructor
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    //fetch clientId from config.properties
    public String getClientId() {
        String prop = properties.getProperty("client_id");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in config.properties");
    }

    //fetch clientSecret from config.properties
    public String getSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in config.properties");
    }
    //fetch refresh_token from config.properties
    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if (prop != null) return prop;
        else throw new RuntimeException("property refresh_token is not specified in config.properties");
    }
    //fetch user_id from config.properties
    public String getUserId() {
        String prop = properties.getProperty("user_id");
        if (prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in config.properties");
    }
    //fetch grant_type from config.properties
    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop != null) return prop;
        else throw new RuntimeException("property grant_type is not specified in config.properties");
    }
}
