package ro.msg.learning.shop.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer ID) {
        super("Could not find product " + ID);
    }
}
