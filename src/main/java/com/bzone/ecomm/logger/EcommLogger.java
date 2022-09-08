package com.bzone.ecomm.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EcommLogger {

    private Logger logger;

    private EcommLogger(Logger logger) {
        this.logger = logger;
    }

    public static EcommLogger getLogger(Class<?> c) {
        Logger logger = LoggerFactory.getLogger(c);
        return new EcommLogger(logger);
    }

    public void debug(String log) {
        this.logger.info(log);
    }

    public void debug(String log, Throwable throwable) {
        this.logger.info(log, throwable);
    }

    public void debug(String log, Object... obj) {
        this.logger.info(log, obj);
    }

    public void info(String log) {
        this.logger.info(log);
    }

    public void info(String log, Throwable throwable) {
        this.logger.info(log, throwable);
    }

    public void info(String log, Object... obj) {
        this.logger.info(log, obj);
    }

    public void error(String log) {
        this.logger.error(log);
    }

    public void error(String log, Throwable throwable) {
        this.logger.error(log, throwable);
    }

    public void error(String log, Object... obj) {
        this.logger.error(log, obj);
    }

    public void trace(String log) {
        this.logger.trace(log);
    }

    public void warn(String log) {
        this.logger.warn(log);
    }

    public void warn(String log, Throwable throwable) {
        this.logger.warn(log, throwable);
    }

    public void warn(String log, Object... obj) {
        this.logger.warn(log, obj);
    }

}
