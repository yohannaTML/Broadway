package org.tp.restaurant;

public interface ProductQuantity {
    void validate(String userInput) throws ProductQuantityException;
}
