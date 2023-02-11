package de.htwberlin.kbe.gruppe4.impl;

import org.apache.log4j.Logger;

public class EmptyDeckException extends Exception {

    private static final Logger logger =  Logger.getLogger(EmptyDeckException.class);
    public EmptyDeckException(String message) {

        super(message);
        logger.warn(message);

    }
}
