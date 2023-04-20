package veexi.fazfreestyle.api.exceptions.handle;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}