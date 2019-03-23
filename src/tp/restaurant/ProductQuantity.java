package org.restaurant;

public interface ProductQuantity {
    void validate(String userInput) throws ProductQuantityException;
}
