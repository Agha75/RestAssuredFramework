package com.spotify.oauth2.utils;

import java.util.Properties;

//Singleton deign pattern used so the object of this call cannot be created outside this class
public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    //this will created new object of this class only once and will not check for path again and again
    public static DataLoader getInstance() {
        if (dataLoader == null) {
            //calling the above constructor
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    //fetch get_playlist_id from config.properties
    public String getPlaylistId() {
        String prop = properties.getProperty("get_playlist_id");
        if (prop != null) return prop;
        else throw new RuntimeException("property get_playlist_id is not specified in data.properties");
    }

    //fetch update_playlist_id from config.properties
    public String updatePlaylistId() {
        String prop = properties.getProperty("update_playlist_id");
        if (prop != null) return prop;
        else throw new RuntimeException("property update_playlist_id is not specified in data.properties");
    }
}
