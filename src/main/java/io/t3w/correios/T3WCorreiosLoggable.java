package io.t3w.correios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface T3WCorreiosLoggable {

    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}