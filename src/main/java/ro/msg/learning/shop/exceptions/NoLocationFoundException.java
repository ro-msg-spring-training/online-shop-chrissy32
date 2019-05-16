package ro.msg.learning.shop.exceptions;

public class NoLocationFoundException extends RuntimeException {
    public NoLocationFoundException() {
        super("Unable to find a suitable set of locations!");
    }

}
