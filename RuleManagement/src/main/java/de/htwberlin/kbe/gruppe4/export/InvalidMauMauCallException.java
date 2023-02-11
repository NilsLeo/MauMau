package de.htwberlin.kbe.gruppe4.impl;

import org.apache.log4j.Logger;

public class InvalidMauMauCallException extends Exception {

    private static final Logger logger =  Logger.getLogger(InvalidMauMauCallException.class);

    public InvalidMauMauCallException(){}
    public InvalidMauMauCallException(String message) {

        super(message);
        logger.warn(message);

    }
}
