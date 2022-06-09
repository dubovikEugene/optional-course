package com.epam.optionalcourse.dao.connectionpool;

import com.epam.optionalcourse.dao.exception.ConnectionPoolException;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public final class DBResourceManager {

    private static final DBResourceManager INSTANCE = new DBResourceManager();
    private static final String DATABASE_PROPERTIES = "src/main/resources/database.properties";
    private final ResourceBundle bundle = ResourceBundle.getBundle(DATABASE_PROPERTIES);



    private DBResourceManager() {
    }

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    public  String get(String key) {
        return bundle.getString(key);
    }

}
