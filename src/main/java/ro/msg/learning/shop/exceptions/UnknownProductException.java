package ro.msg.learning.shop.exceptions;

public class UnknownProductException extends RuntimeException {
    public UnknownProductException() {
        super("Unknown product ID(s)!");
    }
}
