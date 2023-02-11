package de.htwberlin.kbe.gruppe4.export;

import org.apache.log4j.Logger;

public class InvalidCardException extends Exception {

    private static final Logger logger =  Logger.getLogger(InvalidCardException.class);

    public InvalidCardException(){}
    public InvalidCardException(String message) {

        super(message);
        logger.warn(message);

    }
}
