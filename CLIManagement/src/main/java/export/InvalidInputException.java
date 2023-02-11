package export;

import de.htwberlin.kbe.gruppe4.export.GameDao;
import org.apache.log4j.Logger;

public class InvalidInputException extends Exception {
    private static final Logger logger =  Logger.getLogger(InvalidInputException.class);
    public InvalidInputException() {

    }
    public InvalidInputException(String message) {

        super(message);
        logger.warn(message);

    }
}