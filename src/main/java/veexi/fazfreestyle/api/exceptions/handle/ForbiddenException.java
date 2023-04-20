package veexi.fazfreestyle.api.exceptions.handle;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}