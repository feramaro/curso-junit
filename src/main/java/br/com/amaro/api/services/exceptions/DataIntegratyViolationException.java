package br.com.amaro.api.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException {

    public DataIntegratyViolationException(String message) {
        super(message);
    }
}
