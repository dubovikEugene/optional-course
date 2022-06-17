package com.epam.optionalcourse.dao.connectionpool;

import java.util.ResourceBundle;

public final class DBResourceManager {

    private static final DBResourceManager INSTANCE = new DBResourceManager();
    private static final String DATABASE_PROPERTIES = "database";
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
