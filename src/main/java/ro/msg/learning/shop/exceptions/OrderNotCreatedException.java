package ro.msg.learning.shop.exceptions;

public class OrderNotCreatedException extends Exception {
    public OrderNotCreatedException() {
        super("Unable to create order!");
    }

}
