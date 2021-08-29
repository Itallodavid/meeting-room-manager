package itallodavid.github.meetingroommanager.exception;

import org.springframework.http.HttpStatus;

public abstract class ResponseException extends RuntimeException {

    public ResponseException(String message) {
        super(message);
    }

    public abstract String getDescription();

    public abstract HttpStatus getHttpStatus();
}
