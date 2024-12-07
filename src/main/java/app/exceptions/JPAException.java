package app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAException extends RuntimeException {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JPAException.class);

    public JPAException(String message) {
        super(message);
        writeToLog(message);
    }

    private void writeToLog(String message) {
        logger.error(message);
    }
}