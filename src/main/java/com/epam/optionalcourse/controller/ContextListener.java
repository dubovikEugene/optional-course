package com.epam.optionalcourse.controller;


import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ContextListener.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            pool.initConnectionPool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            pool.closePool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
