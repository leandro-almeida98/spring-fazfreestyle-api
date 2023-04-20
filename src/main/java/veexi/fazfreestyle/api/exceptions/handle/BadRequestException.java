package veexi.fazfreestyle.api.exceptions.handle;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}