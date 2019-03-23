package org.tp.restaurant;

public class RowProduct {

    private double price;
    private double quantities;
    private final String commonName;

    public RowProduct(String pName, double pQuantity, double pPrice) {
        commonName = pName;
        quantities = pQuantity;
        price = pPrice;
    }

    public String getCommonName() {
        return commonName;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantities() {
        return quantities;
    }


    public void addProducts(double quantities) {
        if (quantities > 0) {
            this.quantities += quantities;
        }
    }

   /* public void setPrice(double price) {
        this.price = price;
    } */

    public Product consomme() {
        if (quantities > 0) {
            quantities--;
            return new Product(commonName, price);
        }
        return null;
    }

    @Override
    public String toString() {
        return commonName + "\t" + "\t" + price + "€" + "\t" + quantities;
    }
}


