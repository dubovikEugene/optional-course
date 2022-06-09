package com.epam.optionalcourse.dao.connectionpool;

import com.epam.optionalcourse.dao.exception.ConnectionPoolException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final DBResourceManager resourceManager = DBResourceManager.getInstance();
    private static final String DRIVER_KEY = "db.driver";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "db.poolsize";
    private static final Integer DEFAULT_POOL_SIZE_KEY = 5;
    private static BlockingQueue<Connection> connectionPool;
    private static List<Connection> sourceConnections;


    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection get() throws ConnectionPoolException {
        try {
            return connectionPool.take();
        } catch (InterruptedException exception) {
            throw new ConnectionPoolException("Can`t take Connection from pool", exception);
            // TODO: 6/6/2022 logger
        }
    }

    private void initConnectionPool() throws ConnectionPoolException {
        loadDriver();

        var propertiesPoolSize = resourceManager.get(POOL_SIZE_KEY);
        var poolSize = propertiesPoolSize == null ? DEFAULT_POOL_SIZE_KEY : Integer.parseInt(propertiesPoolSize);

        connectionPool = new ArrayBlockingQueue<>(poolSize);
        sourceConnections = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            var connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionPool.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close")
                            ? connectionPool.add((Connection) proxy)
                            : method.invoke(connection, args));

            connectionPool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    private Connection open() throws ConnectionPoolException {
        try {
            return DriverManager.getConnection(
                    resourceManager.get(URL_KEY),
                    resourceManager.get(USERNAME_KEY),
                    resourceManager.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new ConnectionPoolException("Invalid username, password or url", e);
        }


    }

    public void closePool() throws ConnectionPoolException {
        try {
            for (Connection sourceConnection : sourceConnections) {
                sourceConnection.close();
            }
        } catch (SQLException exception) {
            throw new ConnectionPoolException("Close pool exception", exception);
        }

    }

    private void loadDriver() throws ConnectionPoolException {
        try {
            Class.forName(resourceManager.get(DRIVER_KEY));
        } catch (ClassNotFoundException exception) {
            throw new ConnectionPoolException("Can`t find the JDBC driver", exception);
            // TODO: 6/6/2022 logger
        }
    }


}
