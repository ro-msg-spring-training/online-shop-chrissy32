package ro.msg.learning.shop.exceptions;

public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException() {
        super("Unable to create order!");
    }

}
