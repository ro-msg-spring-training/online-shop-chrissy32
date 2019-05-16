package ro.msg.learning.shop.exceptions;

public class InvalidStrategyException extends RuntimeException {
    public InvalidStrategyException() {
        super("Unable to find such a strategy!");
    }

}
