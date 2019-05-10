package ro.msg.learning.shop.exceptions;

public class NoLocationFoundException extends Exception {
    public NoLocationFoundException() {
        super("Unable to find a suitable set of locations!");
    }

}
