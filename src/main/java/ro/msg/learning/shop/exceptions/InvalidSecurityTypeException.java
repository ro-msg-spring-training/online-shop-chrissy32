package ro.msg.learning.shop.exceptions;

public class InvalidSecurityTypeException extends RuntimeException {
    public InvalidSecurityTypeException() {
        super("Unable to find such a security type!");
    }
}
